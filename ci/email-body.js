#!/usr/bin/env node
/*
 * Renders the run-summary JSON as an Outlook-safe HTML email body.
 *
 * Reads target/run-summary.json, which ci/summarize-allure.js already produces from
 * allure-results - totals, pass rate, duration and the not-passed scenario list are all computed
 * there, so nothing is recounted here. This script only formats.
 *
 * Layout is a monospace block on purpose. Outlook renders HTML through Word, which ignores
 * flexbox and grid and mangles most CSS layout; a <pre> with aligned labels survives it intact
 * and looks identical in Outlook, Gmail and on a phone. Links are real <a> tags inside the block
 * so they stay clickable.
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
/** Keeps the mail readable when a run goes badly wrong; the count is still reported. */
const MAX_FAILURES_LISTED = Number(arg('max-failures', '15'));

if (!fs.existsSync(SUMMARY)) {
  // Exit 0 with a usable body: a missing summary must not stop the mail from going out, or a
  // broken run reports nothing at all - which is the case where the mail matters most.
  process.stdout.write(
    `<pre style="font-family:Consolas,'Courier New',monospace;font-size:13px">`
    + `⚠️  Run summary not found at ${SUMMARY}.\n`
    + `The suite most likely failed before producing allure-results.\n`
    + (JENKINS_URL ? `\nConsole log: ${JENKINS_URL}console\n` : '')
    + `</pre>`);
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
const mins = Math.floor(durationMs / 60000);
const secs = Math.round((durationMs % 60000) / 1000);
const duration = `${mins}m ${secs}s`;

const clean = passed === total && total > 0;
const headline = clean
  ? `🟢 ${JOB.toUpperCase()} EXECUTION COMPLETED`
  : `🔴 ${JOB.toUpperCase()} EXECUTION COMPLETED WITH FAILURES`;

const esc = (s) =>
  String(s).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');

const RULE = '━'.repeat(30);

/* ---------------------------------------------------------------- trend */
let trendBlock;
const prev = PREVIOUS === null || PREVIOUS === '' ? null : Number(PREVIOUS);
if (prev === null || Number.isNaN(prev)) {
  trendBlock =
    `Previous Pass % : n/a\n` +
    `Current Pass %  : ${passRate}%\n` +
    `Improvement     : n/a (no previous run recorded)`;
} else {
  const delta = Math.round((passRate - prev) * 10) / 10;
  const sign = delta > 0 ? '+' : '';                       // a negative number carries its own '-'
  const arrow = delta > 0 ? ' ⬆️' : delta < 0 ? ' ⬇️' : ' ➡️';
  trendBlock =
    `Previous Pass % : ${prev}%\n` +
    `Current Pass %  : ${passRate}%\n` +
    `Improvement     : ${sign}${delta}%${arrow}`;
}

/* ------------------------------------------------------- failed scenarios */
const notPassed = Array.isArray(data.notPassed) ? data.notPassed : [];
let failureBlock;
if (notPassed.length === 0) {
  failureBlock = 'None — every scenario passed.';
} else {
  const shown = notPassed.slice(0, MAX_FAILURES_LISTED);
  failureBlock = shown
    .map((s, i) => `${String(i + 1).padStart(2, ' ')}. ${esc(s.name || '(unnamed scenario)')}`)
    .join('\n');
  if (notPassed.length > shown.length) {
    // Never truncate silently - a hidden remainder reads as "only 15 failed".
    failureBlock += `\n\n    …and ${notPassed.length - shown.length} more. See the Allure report.`;
  }
}

/* ------------------------------------------------------------ quick links */
const links = [];
if (JENKINS_URL) {
  links.push(`📊 <a href="${esc(JENKINS_URL)}allure/">Allure Report</a>`);
  links.push(`📄 <a href="${esc(JENKINS_URL)}testReport/">TestNG Report</a>`);
  links.push(`🖥 <a href="${esc(JENKINS_URL)}">Jenkins Build</a>`);
}
if (JIRA_URL) {
  links.push(`🐞 <a href="${esc(JIRA_URL)}">JIRA Dashboard</a>`);
}
const linkBlock = links.length ? links.join('\n') : '(no links configured)';

/* ----------------------------------------------------------------- render */
const body = `<pre style="font-family:Consolas,'Courier New',monospace;font-size:13px;line-height:1.5;color:#202124;background:#ffffff;padding:16px;margin:0">
${headline}

${RULE}

📋 EXECUTION SUMMARY

✅ Passed      : ${passed}
❌ Failed      : ${failed}
⚠️ Skipped     : ${skipped}
📊 Pass Rate   : ${passRate}%
🧮 Total       : ${total}

${RULE}

🚀 BUILD DETAILS

Job Name     : ${esc(JOB)}
Build Number : #${esc(BUILD)}
Environment  : ${esc(ENVIRONMENT)}
Branch       : ${esc(data.branch || '-')}
Duration     : ${duration}

${RULE}

❌ FAILED SCENARIOS

${failureBlock}

${RULE}

📈 QUALITY TREND

${trendBlock}

${RULE}

🔗 QUICK LINKS

${linkBlock}

${RULE}

🤖 Generated Automatically by JSW Automation Framework
</pre>`;

process.stdout.write(body);
