#!/usr/bin/env node
/*
 * Renders the run-summary JSON as an Outlook-safe HTML email body.
 *
 * Reads target/run-summary.json, which ci/summarize-allure.js already produces from
 * allure-results - totals, pass rate, duration and the not-passed scenario list are all computed
 * there, so nothing is recounted here. This script only formats.
 *
 * Layout is TABLE-BASED, and that is the whole point. Outlook renders HTML through Microsoft
 * Word, which ignores flexbox, grid, float and margin - and, as build #239 proved, collapses the
 * newlines inside a <pre> so the entire report arrives as one run-on paragraph. Nested tables
 * with inline styles are the one layout primitive Word handles faithfully, and they render the
 * same in Gmail, Apple Mail and on a phone. See the render section for the specific rules.
 *
 * Trend: the previous run's pass rate cannot be read from allure-results, so the caller passes it
 * in with --previous-pass-rate. The pipeline carries it forward in currentBuild.description,
 * which needs no plugin and survives workspace cleanup.
 *
 * Node stdlib only - no npm install, matching the other ci/ scripts.
 *
 * Usage:
 *   node ci/email-body.js \
 *     --summary target/run-summary.json \
 *     --job "$JOB_NAME" --build "$BUILD_NUMBER" --env qa \
 *     --jenkins-url "$BUILD_URL" \
 *     --previous-pass-rate 92.0 \
 *     --jira-url https://jswone.atlassian.net/... \
 *     > target/email-body.html
 */

'use strict';

const fs = require('fs');

function arg(name, fallback = null) {
  const i = process.argv.indexOf(`--${name}`);
  return i > -1 && process.argv[i + 1] ? process.argv[i + 1] : fallback;
}

const SUMMARY = arg('summary', 'target/run-summary.json');
const JOB = arg('job', 'Automation');
const BUILD = arg('build', '-');
const ENVIRONMENT = arg('env', 'unknown');
const JENKINS_URL = arg('jenkins-url', '');
const JIRA_URL = arg('jira-url', '');
const PREVIOUS = arg('previous-pass-rate');
/**
 * The PREVIOUS run's summary, used to split failures into new / recurring / fixed.
 *
 * The pipeline copies target/run-summary.json to this path at the very start of a build, before
 * anything deletes it - the Jenkins workspace persists between builds, so last run's file is
 * still sitting there. That avoids needing the Copy Artifact plugin or an authenticated call to
 * the Jenkins API just to read our own previous output.
 *
 * Absent on a first build, or after a workspace wipe. Handled as "no comparison available".
 */
const PREVIOUS_SUMMARY = arg('previous-summary', '.previous-run-summary.json');
/** Keeps the mail readable when a run goes badly wrong; the count is still reported. */
const MAX_FAILURES_LISTED = Number(arg('max-failures', '15'));

if (!fs.existsSync(SUMMARY)) {
  // Exit 0 with a usable body: a missing summary must not stop the mail from going out, or a
  // broken run reports nothing at all - which is the case where the mail matters most.
  // Table, not <pre>, for the same reason as the main body below: Outlook renders through Word,
  // which collapses newlines inside <pre> into one run-on paragraph.
  const f = "-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,sans-serif";
  process.stdout.write(
    `<table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0"`
    + ` style="background:#f1f3f4;padding:24px 0"><tr><td align="center">`
    + `<table role="presentation" width="640" cellpadding="0" cellspacing="0" border="0"`
    + ` style="width:640px;max-width:640px;background:#ffffff;border:1px solid #e0e0e0;border-collapse:collapse">`
    + `<tr><td style="background:#f29900;padding:18px 20px;font-family:${f};font-size:16px;`
    + `font-weight:bold;color:#ffffff">⚠️ No run summary produced</td></tr>`
    + `<tr><td style="padding:20px;font-family:${f};font-size:14px;color:#202124;line-height:1.6">`
    + `The suite most likely failed before producing allure-results, so there are no counts to report.`
    + `<br><br><span style="color:#5f6368">Expected at: ${SUMMARY}</span>`
    + (JENKINS_URL
        ? `<br><br>🖥 <a href="${JENKINS_URL}console" style="color:#1a73e8;text-decoration:none">Console log</a>`
        : '')
    + `</td></tr></table></td></tr></table>`);
  process.exit(0);
}

const data = JSON.parse(fs.readFileSync(SUMMARY, 'utf8'));
const t = data.totals || {};

const passed = t.passed || 0;
const failed = (t.failed || 0) + (t.broken || 0);   // broken is a failure to a reader
const skipped = t.skipped || 0;
const total = t.total || 0;
const passRate = typeof t.passRate === 'number' ? t.passRate : 0;

const durationMs = data.durationMs || 0;
const fmtDuration = (ms) => `${Math.floor(ms / 60000)}m ${Math.round((ms % 60000) / 1000)}s`;
const duration = fmtDuration(durationMs);

const esc = (s) =>
  String(s).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');

/* ------------------------------------------------------- previous run
 *
 * Loaded here rather than further down because BOTH the quality trend and the new-vs-recurring
 * comparison read it. See PREVIOUS_SUMMARY above for how the pipeline puts it there.
 *
 * A corrupt or half-written file must not stop the mail going out - every consumer below treats
 * null as "no comparison available".
 */
let previousData = null;
try {
  if (fs.existsSync(PREVIOUS_SUMMARY)) {
    previousData = JSON.parse(fs.readFileSync(PREVIOUS_SUMMARY, 'utf8'));
  }
} catch (e) {
  previousData = null;
}

/* ---------------------------------------------------------------- trend
 *
 * Reads the previous SUMMARY where it exists, so the trend covers pass rate, scenario counts and
 * duration rather than pass rate alone. --previous-pass-rate remains as a fallback: it comes from
 * currentBuild.description, which survives a workspace wipe when the summary file does not.
 *
 * Duration matters as much as pass rate here. A suite quietly growing from 90 to 140 minutes is
 * a productivity problem that no pass/fail number reports.
 */
const prevTotals = (previousData && previousData.totals) || null;
const descriptionRate = PREVIOUS === null || PREVIOUS === '' ? null : Number(PREVIOUS);

let previousRate = null;
if (prevTotals && typeof prevTotals.passRate === 'number') {
  previousRate = prevTotals.passRate;
} else if (descriptionRate !== null && !Number.isNaN(descriptionRate)) {
  previousRate = descriptionRate;
}

/** "before → after (+delta)" for one metric. betterWhen says which direction is good. */
function delta(before, after, betterWhen, format) {
  const fmt = format || ((v) => String(v));
  if (before === null || before === undefined) {
    return { text: fmt(after), detail: 'no previous run to compare', color: null };
  }
  const rounded = Math.round((after - before) * 10) / 10;
  const good = betterWhen === 'lower' ? rounded < 0 : rounded > 0;
  const arrow = rounded === 0 ? '➡️' : (rounded > 0 ? '⬆️' : '⬇️');
  // Sign then magnitude, so a duration formatter never has to render "-7m -29s".
  const sign = rounded > 0 ? '+' : '-';
  return {
    text: `${fmt(before)} → ${fmt(after)}`,
    detail: rounded === 0 ? `no change ${arrow}` : `${sign}${fmt(Math.abs(rounded))} ${arrow}`,
    color: rounded === 0 ? null : (good ? '#0f9d58' : '#d93025'),
  };
}

const trend = {
  passRate: delta(previousRate, passRate, 'higher', (v) => `${v}%`),
  passed: prevTotals ? delta(prevTotals.passed || 0, passed, 'higher') : null,
  // broken folded into failed on both sides, exactly as the summary panel does it
  failed: prevTotals
    ? delta((prevTotals.failed || 0) + (prevTotals.broken || 0), failed, 'lower')
    : null,
  duration: (previousData && previousData.durationMs)
    ? delta(previousData.durationMs, durationMs, 'lower', fmtDuration)
    : null,
  previousBuild: previousData ? (previousData.build || '-') : null,
};

/* ------------------------------------------------------- failed scenarios */
const notPassed = Array.isArray(data.notPassed) ? data.notPassed : [];

/* ----------------------------------------------------------------- render
 *
 * TABLES, not <pre>. Outlook renders HTML through Microsoft Word, which collapses the newlines
 * inside a <pre> - the whole report arrived as one run-on paragraph. Word does handle nested
 * tables with inline styles reliably, and so do Gmail, Apple Mail and every mobile client, so
 * every row below is a <tr> rather than a line of text.
 *
 * Rules this layout sticks to, all of them Word limitations:
 *   - inline styles only; Word drops <style> blocks and class selectors
 *   - no flexbox, grid, float or margin - padding on <td> instead
 *   - explicit cellpadding/cellspacing/border on every table
 *   - fixed max-width via a wrapper table, not max-width CSS
 *   - no <pre> and no white-space:pre
 */

const FONT = "-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,sans-serif";
const MONO = "Consolas,'Courier New',monospace";
const BORDER = '#e0e0e0';
const MUTED = '#5f6368';
const INK = '#202124';

/** A section heading bar. */
function section(title) {
  return `<tr><td style="padding:20px 20px 8px 20px;font-family:${FONT};font-size:13px;font-weight:bold;`
    + `color:${INK};border-bottom:2px solid ${BORDER};letter-spacing:0.3px">${title}</td></tr>`;
}

/** One label/value line, rendered as a nested 2-column table so the columns actually align. */
function row(label, value, valueColor) {
  return `<tr><td style="padding:0 20px">`
    + `<table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0">`
    + `<tr>`
    + `<td style="padding:5px 0;font-family:${FONT};font-size:14px;color:${MUTED};width:170px">${label}</td>`
    + `<td style="padding:5px 0;font-family:${MONO};font-size:14px;font-weight:bold;`
    + `color:${valueColor || INK}">${value}</td>`
    + `</tr></table></td></tr>`;
}

/** Free text inside a section, e.g. the "all passed" line. */
function note(text) {
  return `<tr><td style="padding:8px 20px;font-family:${FONT};font-size:14px;color:${MUTED}">${text}</td></tr>`;
}

const clean_ = passed === total && total > 0;
const headerBg = clean_ ? '#0f9d58' : '#d93025';

/* ------------------------------------------------- new vs recurring vs fixed
 *
 * The single most decision-useful line in a test mail: 30 failures that are all known debt is a
 * backlog, 3 that are new is a regression someone landed today. A raw failure count cannot tell
 * the two apart, so build #239's "30 failed" said nothing about whether anything got worse.
 *
 * Identity is feature + name, not name alone: ci/lint-features.js already reports scenario names
 * duplicated across feature files (the web and msite suites mirror each other deliberately), so
 * matching on name would pair up unrelated scenarios.
 */
const scenarioKey = (s) => `${s.feature || ''} ${s.name || ''}`;

// previousData is loaded once, up with the trend - both sections read the same file.
let change = null;
if (previousData && Array.isArray(previousData.notPassed)) {
  const previouslyFailing = new Set(previousData.notPassed.map(scenarioKey));
  const currentlyFailing = new Set(notPassed.map(scenarioKey));

  const newFailures = notPassed.filter((s) => !previouslyFailing.has(scenarioKey(s)));
  const recurring = notPassed.filter((s) => previouslyFailing.has(scenarioKey(s)));
  const fixed = previousData.notPassed.filter((s) => !currentlyFailing.has(scenarioKey(s)));

  change = {
    newKeys: new Set(newFailures.map(scenarioKey)),
    newCount: newFailures.length,
    recurringCount: recurring.length,
    fixedCount: fixed.length,
    previousBuild: previousData.build || '-',
    fixed,
  };
}

/** One line of text, collapsed and clipped, for a failure reason. */
function short(message, limit) {
  const t = String(message == null ? '' : message).replace(/\s+/g, ' ').trim();
  if (!t) return '';
  return t.length > limit ? t.slice(0, limit - 1) + '…' : t;
}

/* --------------------------------------------------- per-feature breakdown
 *
 * run-summary.json has carried features[] all along and the mail never used it. A bare
 * "30 failed" says nothing about WHERE: on build #239 those thirty were almost entirely one
 * suite, which is a single investigation rather than thirty. This table is what makes that
 * visible without opening Allure.
 *
 * Only the failing features are listed. A run with fifty green features would otherwise bury
 * the two that need attention, so the clean ones are collapsed into a count.
 */
const MAX_FEATURES_LISTED = 12;
const featureStats = (Array.isArray(data.features) ? data.features : []).map((f) => ({
  name: f.feature || '(unnamed feature)',
  total: f.total || 0,
  passed: f.passed || 0,
  // broken counts as failed here, exactly as it does in the totals above - to whoever reads
  // this, a scenario that errored is a scenario that did not pass.
  failed: (f.failed || 0) + (f.broken || 0),
}));
const failingFeatures = featureStats
  .filter((f) => f.failed > 0)
  .sort((a, b) => b.failed - a.failed || b.total - a.total);
const cleanFeatureCount = featureStats.length - failingFeatures.length;

let featureRows;
if (featureStats.length === 0) {
  featureRows = note('No per-feature data in this run summary.');
} else if (failingFeatures.length === 0) {
  featureRows = note(`All <b>${featureStats.length}</b> features passed.`);
} else {
  const cell = (content, align, color, weight) =>
    `<td align="${align}" valign="top" style="padding:6px 8px;font-family:${align === 'left' ? FONT : MONO};`
    + `font-size:13px;color:${color};font-weight:${weight || 'normal'};`
    + `border-bottom:1px solid #f1f3f4">${content}</td>`;

  const shown = failingFeatures.slice(0, MAX_FEATURES_LISTED);
  featureRows =
    `<tr><td style="padding:4px 20px 0 20px">`
    + `<table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0"`
    + ` style="border-collapse:collapse">`
    + `<tr>`
    + `<td align="left"  style="padding:4px 8px;font-family:${FONT};font-size:11px;color:${MUTED};border-bottom:1px solid ${BORDER}">FEATURE</td>`
    + `<td align="right" style="padding:4px 8px;font-family:${FONT};font-size:11px;color:${MUTED};border-bottom:1px solid ${BORDER};width:50px">TOTAL</td>`
    + `<td align="right" style="padding:4px 8px;font-family:${FONT};font-size:11px;color:${MUTED};border-bottom:1px solid ${BORDER};width:60px">PASSED</td>`
    + `<td align="right" style="padding:4px 8px;font-family:${FONT};font-size:11px;color:${MUTED};border-bottom:1px solid ${BORDER};width:55px">FAILED</td>`
    + `</tr>`
    + shown.map((f) =>
        `<tr>`
        + cell(esc(f.name), 'left', INK)
        + cell(f.total, 'right', MUTED)
        + cell(f.passed, 'right', f.passed ? '#0f9d58' : MUTED)
        + cell(f.failed, 'right', '#d93025', 'bold')
        + `</tr>`).join('')
    + `</table></td></tr>`;

  if (failingFeatures.length > shown.length) {
    featureRows += note(`…and <b>${failingFeatures.length - shown.length}</b> more feature(s) with failures.`);
  }
  if (cleanFeatureCount > 0) {
    featureRows += note(`✅ <b>${cleanFeatureCount}</b> feature(s) passed completely and are not listed.`);
  }
}

/* ------------------------------------------------------------ trend rows
 * Each metric on one line as "before → after" with the delta beside it, so the direction is
 * readable without doing the arithmetic. Rows whose data is unavailable are simply omitted
 * rather than rendered as "n/a", which would be four lines of nothing on a first build.
 */
function trendRow(label, d) {
  if (!d) return '';
  return `<tr><td style="padding:0 20px">`
    + `<table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0"><tr>`
    + `<td style="padding:5px 0;font-family:${FONT};font-size:14px;color:${MUTED};width:120px">${label}</td>`
    + `<td style="padding:5px 0;font-family:${MONO};font-size:14px;color:${INK};width:170px">${d.text}</td>`
    + `<td style="padding:5px 0;font-family:${MONO};font-size:14px;font-weight:bold;`
    + `color:${d.color || MUTED}">${d.detail}</td>`
    + `</tr></table></td></tr>`;
}

let trendRows =
    trendRow('Pass rate', trend.passRate)
  + trendRow('Passed', trend.passed)
  + trendRow('Failed', trend.failed)
  + trendRow('Duration', trend.duration);

if (trend.previousBuild) {
  // The build number is stated once, in whichever section renders it first.
  trendRows += note(`Compared against build <b>#${esc(trend.previousBuild)}</b>.`);
} else if (previousRate === null) {
  trendRows = note('No previous run recorded yet — the trend appears from the next build onwards.');
} else {
  trendRows += note('Pass rate carried from the previous build; fuller comparison from the next run.');
}

/* -------------------------------------------------- change since last run */
let changeRows;
if (!change) {
  changeRows = note('No previous run summary in the workspace, so new and recurring failures '
    + 'cannot be separated for this build. Available from the next run onwards.');
} else if (change.newCount === 0 && change.fixedCount === 0 && change.recurringCount === 0) {
  changeRows = note(`No change against build #${esc(change.previousBuild)} — nothing failing then or now.`);
} else {
  changeRows =
      row('🆕 New failures', change.newCount, change.newCount > 0 ? '#d93025' : INK)
    + row('🔁 Still failing', change.recurringCount, change.recurringCount > 0 ? '#f29900' : INK)
    + row('✅ Newly fixed', change.fixedCount, change.fixedCount > 0 ? '#0f9d58' : INK);

  // The fixed list is short and worth naming: it is the only place the mail reports progress.
  if (change.fixedCount > 0) {
    changeRows += change.fixed.slice(0, 5).map((s) =>
      `<tr><td style="padding:2px 20px;font-family:${FONT};font-size:12px;color:#0f9d58">`
      + `&nbsp;&nbsp;✅ ${esc(s.name || '(unnamed scenario)')}</td></tr>`).join('');
    if (change.fixedCount > 5) {
      changeRows += note(`&nbsp;&nbsp;…and ${change.fixedCount - 5} more now passing.`);
    }
  }
}

/* ------------------------------------------------------- failed scenarios
 *
 * Grouped by feature and carrying the failure REASON. A flat list of thirty names says only
 * that thirty things broke; twelve rows under one feature all reporting the same message say
 * that one fix probably clears them, which is the difference between triage and a to-do list.
 *
 * A NEW badge marks anything that was not failing in the previous run - those are the ones that
 * changed today, and they are what a reader should look at first.
 */
let failureRows;
if (notPassed.length === 0) {
  failureRows = note('None — every scenario passed.');
} else {
  const groups = new Map();
  for (const s of notPassed) {
    const key = s.feature || '(unknown feature)';
    if (!groups.has(key)) groups.set(key, []);
    groups.get(key).push(s);
  }
  // Biggest cluster first: that is where the single most valuable fix usually is.
  const ordered = [...groups.entries()].sort((a, b) => b[1].length - a[1].length);

  let listed = 0;
  const blocks = [];
  for (const [feature, scenarios] of ordered) {
    if (listed >= MAX_FAILURES_LISTED) break;

    // New failures first inside each feature: if the list gets clipped, the ones that changed
    // today survive the cut rather than the long-standing ones.
    if (change) {
      scenarios.sort((a, b) =>
        (change.newKeys.has(scenarioKey(b)) ? 1 : 0) - (change.newKeys.has(scenarioKey(a)) ? 1 : 0));
    }

    const room = MAX_FAILURES_LISTED - listed;
    const take = scenarios.slice(0, room);
    listed += take.length;

    blocks.push(
      `<tr><td style="padding:10px 20px 2px 20px;font-family:${FONT};font-size:13px;font-weight:bold;color:${INK}">`
      + `${esc(feature)} <span style="color:#d93025;font-weight:normal">(${scenarios.length} failed)</span></td></tr>`
      + take.map((s) => {
          const reason = short(s.message || s.failedStep, 150);
          const isNew = change && change.newKeys.has(scenarioKey(s));
          const badge = isNew
            ? ` <span style="background:#d93025;color:#ffffff;font-family:${FONT};font-size:10px;`
              + `font-weight:bold;padding:1px 5px;border-radius:2px">NEW</span>`
            : '';
          return `<tr><td style="padding:0 20px">`
            + `<table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0"><tr>`
            + `<td valign="top" style="padding:3px 0;font-family:${MONO};font-size:13px;color:#d93025;width:18px">•</td>`
            + `<td valign="top" style="padding:3px 0;font-family:${FONT};font-size:13px;color:${INK}">`
            + `${esc(s.name || '(unnamed scenario)')}${badge}`
            + (reason
                ? `<div style="font-family:${MONO};font-size:12px;color:${MUTED};padding-top:2px">${esc(reason)}</div>`
                : '')
            + `</td></tr></table></td></tr>`;
        }).join('')
      + (scenarios.length > take.length
          ? note(`&nbsp;&nbsp;…and ${scenarios.length - take.length} more in this feature.`)
          : ''));
  }

  failureRows = blocks.join('');

  // Never truncate silently - a hidden remainder reads as "only 15 failed".
  if (listed < notPassed.length) {
    failureRows += note(`…<b>${notPassed.length - listed}</b> further failure(s) not listed. See the Allure report.`);
  }
}

/* Quick links - one per row. Word ignores inline-block, so a horizontal strip would collapse. */
let linkRows = '';
if (JENKINS_URL) {
  const link = (icon, href, text) =>
    `<tr><td style="padding:4px 20px;font-family:${FONT};font-size:14px">`
    + `${icon} <a href="${esc(href)}" style="color:#1a73e8;text-decoration:none">${text}</a></td></tr>`;
  linkRows += link('📊', `${JENKINS_URL}allure/`, 'Allure Report');
  linkRows += link('📄', `${JENKINS_URL}testReport/`, 'TestNG Report');
  linkRows += link('🖥', JENKINS_URL, 'Jenkins Build');
  if (JIRA_URL) linkRows += link('🐞', JIRA_URL, 'JIRA Dashboard');
} else {
  linkRows = note('(no links configured)');
}

const body =
`<table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0"
        style="background:#f1f3f4;padding:24px 0">
<tr><td align="center">

<table role="presentation" width="640" cellpadding="0" cellspacing="0" border="0"
       style="width:640px;max-width:640px;background:#ffffff;border:1px solid ${BORDER};border-collapse:collapse">

  <tr><td style="background:${headerBg};padding:18px 20px;font-family:${FONT};font-size:16px;
                 font-weight:bold;color:#ffffff">
    ${clean_ ? '🟢' : '🔴'} ${esc(JOB)} — ${clean_ ? 'ALL TESTS PASSED' : 'COMPLETED WITH FAILURES'}
  </td></tr>

  <tr><td style="padding:14px 20px;background:#fafafa;border-bottom:1px solid ${BORDER};
                 font-family:${MONO};font-size:13px;color:${MUTED}">
    Build <b style="color:${INK}">#${esc(BUILD)}</b> &nbsp;·&nbsp;
    Env <b style="color:${INK}">${esc(ENVIRONMENT)}</b> &nbsp;·&nbsp;
    Branch <b style="color:${INK}">${esc(data.branch || '-')}</b> &nbsp;·&nbsp;
    Duration <b style="color:${INK}">${duration}</b>
  </td></tr>

  ${section('📋 EXECUTION SUMMARY')}
  ${row('✅ Passed', passed, '#0f9d58')}
  ${row('❌ Failed', failed, failed > 0 ? '#d93025' : INK)}
  ${row('⚠️ Skipped', skipped)}
  ${row('📊 Pass Rate', passRate + '%', passRate >= 90 ? '#0f9d58' : passRate >= 70 ? '#f29900' : '#d93025')}
  ${row('🧮 Total', total)}

  ${section('📈 TREND vs PREVIOUS RUN')}
  ${trendRows}

  ${section('🔄 CHANGE SINCE LAST RUN')}
  ${changeRows}

  ${section('🧭 WHERE THE FAILURES ARE')}
  <tr><td style="height:6px;line-height:6px">&nbsp;</td></tr>
  ${featureRows}
  <tr><td style="height:10px;line-height:10px">&nbsp;</td></tr>

  ${section('❌ FAILED SCENARIOS')}
  <tr><td style="height:2px;line-height:2px">&nbsp;</td></tr>
  ${failureRows}
  <tr><td style="height:10px;line-height:10px">&nbsp;</td></tr>

  ${section('🔗 QUICK LINKS')}
  <tr><td style="height:6px;line-height:6px">&nbsp;</td></tr>
  ${linkRows}

  <tr><td style="padding:18px 20px;margin-top:8px;background:#fafafa;border-top:1px solid ${BORDER};
                 font-family:${FONT};font-size:12px;color:${MUTED}">
    🤖 Generated automatically by the JSW Automation Framework
  </td></tr>

</table>

</td></tr></table>`;

process.stdout.write(body);
