#!/usr/bin/env node
/**
 * Prints the tail of the Jenkins build description, for the mail stage to append.
 *
 *   node ci/build-description.js --summary target/run-summary.json \
 *                                --previous .previous-run-summary-qa.json
 *
 *   -> 24 tests, 4 failed (2 new) | 38m | passRate=83.3
 *
 * A build description is read in a list of a hundred builds, so this answers only the questions
 * that decide whether to open one: how big was the run, how much broke, how much of that is NEW,
 * how long it took, and the rate. Everything else belongs in the report.
 *
 * "new" is the count that is not in the previous SAME-ENV run - the pipeline preserves that file
 * per env. Without it, four failures look identical whether they are yesterday's four or four
 * fresh ones. The key matches ci/email-body.js exactly so the description and the mail cannot
 * disagree about what counts as new.
 *
 * passRate stays last: the mail stage's trend walker reads previous descriptions with
 * indexOf('passRate=') and takes the digits that follow.
 */
const fs = require('fs');

const arg = (name, fallback = null) => {
  const i = process.argv.indexOf('--' + name);
  return i > -1 && process.argv[i + 1] ? process.argv[i + 1] : fallback;
};

const summaryPath = arg('summary');
const previousPath = arg('previous');

if (!summaryPath || !fs.existsSync(summaryPath)) {
  process.exit(0); // nothing to say; the Checkout-stage description stands on its own
}

const read = p => {
  try {
    return p && fs.existsSync(p) ? JSON.parse(fs.readFileSync(p, 'utf8')) : null;
  } catch (e) {
    return null;
  }
};

const summary = read(summaryPath);
if (!summary || !summary.totals) process.exit(0);

const totals = summary.totals;
const notPassed = Array.isArray(summary.notPassed) ? summary.notPassed : [];

// failed = anything that did not pass. Broken is folded in because the distinction matters in the
// report, not in a one-line list entry.
const failed = (totals.failed || 0) + (totals.broken || 0);

// Same key as ci/email-body.js.
const scenarioKey = s => `${s.feature || ''} ${s.name || ''}`;

let newPart = '';
const previous = read(previousPath);
if (failed > 0 && previous && Array.isArray(previous.notPassed)) {
  const wasFailing = new Set(previous.notPassed.map(scenarioKey));
  const fresh = notPassed.filter(s => !wasFailing.has(scenarioKey(s))).length;
  newPart = ` (${fresh} new)`;
}

const parts = [];
parts.push(failed > 0
  ? `${totals.total} tests, ${failed} failed${newPart}`
  : `${totals.total} tests, all passed`);

const ms = summary.durationMs || 0;
if (ms > 0) {
  const totalMinutes = Math.round(ms / 60000);
  const hours = Math.floor(totalMinutes / 60);
  const minutes = totalMinutes % 60;
  parts.push(hours > 0 ? `${hours}h ${minutes}m` : `${Math.max(totalMinutes, 1)}m`);
}

if (totals.passRate !== undefined && totals.passRate !== null) {
  parts.push(`passRate=${totals.passRate}`);
}

process.stdout.write(parts.join(' | '));
