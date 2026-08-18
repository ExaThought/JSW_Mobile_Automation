#!/usr/bin/env node
/*
 * Builds a self-contained run summary from allure-results/.
 *
 * Two outputs:
 *   <out>.html  - print-friendly, no external assets, so Chrome --headless --print-to-pdf
 *                 renders it faithfully and it survives being pasted into an email body.
 *   <out>.json  - the same numbers as data, for the Jenkins email subject and any later
 *                 Teams/Slack card. Avoids re-parsing or scraping the HTML.
 *
 * Node stdlib only - no npm install on the agent.
 *
 * Usage:
 *   node ci/summarize-allure.js --results allure-results --out target/run-summary \
 *        --env qa --branch feature/x --build 42 --url http://jenkins/job/x/42/
 *
 * Optional --since <epochMillis> keeps only results that started at or after that instant.
 * Jenkins does not need it (the pom clears allure-results each run) but it makes local runs
 * usable when the directory still holds older results.
 */

const fs = require('fs');
const path = require('path');

function arg(name, fallback = null) {
  const i = process.argv.indexOf(`--${name}`);
  return i !== -1 && process.argv[i + 1] ? process.argv[i + 1] : fallback;
}

const RESULTS = arg('results', 'allure-results');
const OUT = arg('out', 'target/run-summary');
const ENV = arg('env', 'unknown');
const BRANCH = arg('branch', 'unknown');
const BUILD = arg('build', '-');
const URL = arg('url', '');
const SINCE = arg('since') ? Number(arg('since')) : null;

if (!fs.existsSync(RESULTS)) {
  console.error(`[summarize-allure] results directory not found: ${RESULTS}`);
  process.exit(2);
}

// ---------------------------------------------------------------- collect
const results = [];
for (const f of fs.readdirSync(RESULTS)) {
  if (!f.endsWith('-result.json')) continue;
  let d;
  try {
    d = JSON.parse(fs.readFileSync(path.join(RESULTS, f), 'utf8'));
  } catch {
    continue; // a partially written file mid-run is not worth failing the build over
  }
  if (SINCE && Number(d.start || 0) < SINCE) continue;
  const label = (n) => (d.labels || []).find((l) => l.name === n)?.value || null;
  results.push({
    name: d.name || '(unnamed)',
    status: (d.status || 'unknown').toLowerCase(),
    message: (d.statusDetails && d.statusDetails.message || '').split('\n')[0].slice(0, 300),
    feature: label('feature') || '(no feature)',
    uri: (label('gherkin_uri') || '').split('/').pop(),
    start: Number(d.start || 0),
    stop: Number(d.stop || 0),
    steps: (d.steps || []).length,
    failedStep: (d.steps || []).find((s) => s.status === 'failed' || s.status === 'broken')?.name || null,
  });
}

/* Allure writes one result per scenario ATTEMPT, so a retried scenario appears more than once.
 * Counting raw files would inflate totals and double-report the same failure. Collapse on
 * feature+name and keep the best outcome, which is what a human means by "did it pass". */
const RANK = { passed: 0, skipped: 1, unknown: 2, broken: 3, failed: 4 };
const byKey = new Map();
for (const r of results) {
  const key = `${r.feature}||${r.name}`;
  const prev = byKey.get(key);
  if (!prev) {
    byKey.set(key, { ...r, attempts: 1 });
    continue;
  }
  prev.attempts += 1;
  if ((RANK[r.status] ?? 2) < (RANK[prev.status] ?? 2)) {
    Object.assign(prev, r, { attempts: prev.attempts });
  }
}
const scenarios = [...byKey.values()].sort(
  (a, b) => a.feature.localeCompare(b.feature) || a.name.localeCompare(b.name)
);

const count = (s) => scenarios.filter((x) => x.status === s).length;
const totals = {
  total: scenarios.length,
  passed: count('passed'),
  failed: count('failed'),
  broken: count('broken'),
  skipped: count('skipped'),
  retried: scenarios.filter((s) => s.attempts > 1).length,
};
totals.passRate = totals.total ? Math.round((totals.passed / totals.total) * 1000) / 10 : 0;

const starts = scenarios.map((s) => s.start).filter(Boolean);
const stops = scenarios.map((s) => s.stop).filter(Boolean);
const durationMs = starts.length && stops.length ? Math.max(...stops) - Math.min(...starts) : 0;
const mins = Math.floor(durationMs / 60000);
const secs = Math.round((durationMs % 60000) / 1000);

// per-feature rollup
const features = {};
for (const s of scenarios) {
  const f = (features[s.feature] ||= { feature: s.feature, uri: s.uri, total: 0, passed: 0, failed: 0, broken: 0, skipped: 0 });
  f.total += 1;
  if (f[s.status] !== undefined) f[s.status] += 1;
}
const featureRows = Object.values(features).sort((a, b) => b.total - a.total);
const notPassed = scenarios.filter((s) => s.status === 'failed' || s.status === 'broken');

// ---------------------------------------------------------------- render
const esc = (s) =>
  String(s).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');

const verdict = totals.failed + totals.broken === 0 ? 'PASSED' : 'ATTENTION NEEDED';
const verdictColour = totals.failed + totals.broken === 0 ? '#1a7f37' : '#b3261e';

const html = `<!doctype html>
<html><head><meta charset="utf-8"><title>Test run summary - ${esc(ENV)} #${esc(BUILD)}</title>
<style>
  @page { size: A4; margin: 14mm; }
  * { box-sizing: border-box; }
  body { font: 13px/1.5 -apple-system,Segoe UI,Roboto,Helvetica,Arial,sans-serif; color:#1c1c1c; margin:0; padding:20px; background:#fff; }
  h1 { font-size:19px; margin:0 0 2px; }
  h2 { font-size:14px; margin:26px 0 8px; padding-bottom:5px; border-bottom:1px solid #d8d8d8; }
  .sub { color:#5c5c5c; font-size:12px; margin-bottom:18px; }
  .verdict { display:inline-block; padding:3px 10px; border-radius:3px; color:#fff; font-weight:600;
             font-size:12px; letter-spacing:.4px; background:${verdictColour}; }
  .cards { display:flex; flex-wrap:wrap; gap:10px; margin:14px 0 4px; }
  .card { border:1px solid #e0e0e0; border-radius:5px; padding:9px 14px; min-width:88px; }
  .card .n { font-size:21px; font-weight:600; line-height:1.1; }
  .card .l { font-size:10px; text-transform:uppercase; letter-spacing:.6px; color:#666; }
  table { border-collapse:collapse; width:100%; font-size:12px; }
  th,td { border:1px solid #e2e2e2; padding:6px 9px; text-align:left; vertical-align:top; }
  th { background:#f5f5f5; font-weight:600; }
  td.n, th.n { text-align:right; width:60px; }
  tr { page-break-inside:avoid; }
  .pass { color:#1a7f37; font-weight:600; }
  .fail { color:#b3261e; font-weight:600; }
  .brok { color:#a8620a; font-weight:600; }
  .skip { color:#777; }
  .msg { color:#5c5c5c; font-family:ui-monospace,Menlo,Consolas,monospace; font-size:11px; word-break:break-word; }
  .empty { color:#1a7f37; padding:10px 0; }
  footer { margin-top:26px; padding-top:8px; border-top:1px solid #e2e2e2; color:#777; font-size:11px; }
</style></head><body>
<h1>JSW One MSME &mdash; automation run summary</h1>
<div class="sub">
  <span class="verdict">${verdict}</span>&nbsp;
  environment <strong>${esc(ENV)}</strong> &middot; branch <strong>${esc(BRANCH)}</strong>
  &middot; build <strong>#${esc(BUILD)}</strong> &middot; duration ${mins}m ${secs}s
</div>

<div class="cards">
  <div class="card"><div class="n">${totals.total}</div><div class="l">Scenarios</div></div>
  <div class="card"><div class="n pass">${totals.passed}</div><div class="l">Passed</div></div>
  <div class="card"><div class="n fail">${totals.failed}</div><div class="l">Failed</div></div>
  <div class="card"><div class="n brok">${totals.broken}</div><div class="l">Broken</div></div>
  <div class="card"><div class="n skip">${totals.skipped}</div><div class="l">Skipped</div></div>
  <div class="card"><div class="n">${totals.passRate}%</div><div class="l">Pass rate</div></div>
</div>
${totals.retried ? `<p class="msg">${totals.retried} scenario(s) ran more than once; the best outcome per scenario is reported.</p>` : ''}

<h2>By feature</h2>
<table><thead><tr><th>Feature</th><th>File</th><th class="n">Total</th><th class="n">Passed</th><th class="n">Failed</th><th class="n">Broken</th><th class="n">Skipped</th></tr></thead><tbody>
${featureRows.map((f) => `<tr><td>${esc(f.feature)}</td><td class="msg">${esc(f.uri || '-')}</td><td class="n">${f.total}</td><td class="n pass">${f.passed}</td><td class="n ${f.failed ? 'fail' : ''}">${f.failed}</td><td class="n ${f.broken ? 'brok' : ''}">${f.broken}</td><td class="n skip">${f.skipped}</td></tr>`).join('\n')}
</tbody></table>

<h2>Not passed (${notPassed.length})</h2>
${notPassed.length === 0
    ? '<p class="empty">Every scenario passed.</p>'
    : `<table><thead><tr><th>Scenario</th><th>Status</th><th>Failing step / reason</th></tr></thead><tbody>
${notPassed.map((s) => `<tr><td>${esc(s.name)}<div class="msg">${esc(s.feature)}</div></td><td class="${s.status === 'failed' ? 'fail' : 'brok'}">${esc(s.status)}</td><td><div>${esc(s.failedStep || '-')}</div><div class="msg">${esc(s.message || '')}</div></td></tr>`).join('\n')}
</tbody></table>`}

<footer>Generated from allure-results. ${URL ? `Full interactive report: <a href="${esc(URL)}allure/">${esc(URL)}allure/</a>` : 'Full Allure report is attached to the Jenkins build.'}</footer>
</body></html>
`;

fs.mkdirSync(path.dirname(OUT), { recursive: true });
fs.writeFileSync(`${OUT}.html`, html, 'utf8');
fs.writeFileSync(
  `${OUT}.json`,
  JSON.stringify({ env: ENV, branch: BRANCH, build: BUILD, verdict, durationMs, totals, features: featureRows, notPassed }, null, 2),
  'utf8'
);

console.log(`[summarize-allure] ${totals.total} scenarios | ${totals.passed} passed, ${totals.failed} failed, ${totals.broken} broken, ${totals.skipped} skipped (${totals.passRate}%)`);
console.log(`[summarize-allure] wrote ${OUT}.html and ${OUT}.json`);
