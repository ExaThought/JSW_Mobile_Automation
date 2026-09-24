#!/usr/bin/env node
/*
 * Relabels "test case" as "scenario" in a generated Allure report.
 *
 * Every Allure "test case" is one Cucumber scenario - Allure's own total already excludes retry
 * attempts, which it stores as hidden records - so only the noun is wrong, never the count. But
 * the wording is compiled into the report's JS bundle: Allure 2.43 bundles all sixteen locales
 * with i18next and exposes no translations file, no allure.properties key, and no window hook to
 * override at runtime. Patching the emitted bundle is the only seam.
 *
 * Five English strings carry the label. Three are i18next keys, whose NAMES are stable across
 * Allure versions even though the surrounding minified identifiers are not, and two are chart
 * tooltips built by string interpolation:
 *
 *   testResults:`test case`                                   summary card, singular base key
 *   testResults_one:`test case`                               summary card, n == 1
 *   testResults_other:`test cases`                            summary card, n != 1  <- the 134
 *   text:`${e.length} test cases`                             trend chart tooltip
 *   text:`${e} ${t.toLowerCase()} test cases ${n.toLowerCase()}`   severity chart tooltip
 *
 * Only the English bundle is touched. The other locales are left alone: a Spanish reader asking
 * for "caso de prueba" is not helped by a half-translated report.
 *
 * Idempotent - re-running on an already-patched report is a no-op and exits 0.
 *
 * Exits 1 if a bundle was found but nothing matched, which is how an Allure upgrade that renames
 * these literals announces itself instead of silently reverting the wording.
 *
 * Node stdlib only - no npm install on the agent.
 *
 * Usage:
 *   node ci/relabel-allure.js [reportDir]      # default: allure-report
 */

const fs = require('fs');
const path = require('path');

const REPORT_DIR = process.argv[2] || 'allure-report';

/* Ordered longest-first so "test cases" is consumed before "test case" could match inside it. */
const REPLACEMENTS = [
  ['testResults_other:`test cases`', 'testResults_other:`scenarios`'],
  ['testResults_one:`test case`', 'testResults_one:`scenario`'],
  ['testResults:`test case`', 'testResults:`scenario`'],
  ['`${e.length} test cases`', '`${e.length} scenarios`'],
  ['`${e} ${t.toLowerCase()} test cases ${n.toLowerCase()}`', '`${e} ${t.toLowerCase()} scenarios ${n.toLowerCase()}`'],
];

/* Marks a bundle as already done, so a re-run can tell "nothing to do" apart from "Allure changed
 * its wording and this script has gone stale" - the two look identical by match count alone. */
const DONE_MARKER = 'testResults_other:`scenarios`';

if (!fs.existsSync(REPORT_DIR)) {
  console.error(`[relabel-allure] report directory not found: ${REPORT_DIR}`);
  process.exit(2);
}

/* The bundle filename carries a content hash (assets/index-BraRl9ss.js), so it is found by
 * walking rather than named. Every .js under the report is a candidate; in practice one matches. */
function jsFiles(dir, acc = []) {
  for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
    const full = path.join(dir, entry.name);
    if (entry.isDirectory()) jsFiles(full, acc);
    else if (entry.name.endsWith('.js')) acc.push(full);
  }
  return acc;
}

const candidates = jsFiles(REPORT_DIR);
if (candidates.length === 0) {
  console.error(`[relabel-allure] no .js bundle under ${REPORT_DIR} - is this a generated Allure report?`);
  process.exit(2);
}

let patchedFiles = 0;
let alreadyDone = 0;
let totalReplacements = 0;

for (const file of candidates) {
  const before = fs.readFileSync(file, 'utf8');

  if (before.includes(DONE_MARKER)) {
    alreadyDone += 1;
    continue;
  }

  let after = before;
  let hits = 0;
  for (const [from, to] of REPLACEMENTS) {
    /* split/join rather than a regex: these literals contain $, {, } and backticks, all of which
     * would need escaping, and a mis-escaped pattern silently matches nothing. */
    const parts = after.split(from);
    if (parts.length > 1) {
      hits += parts.length - 1;
      after = parts.join(to);
    }
  }

  if (hits === 0) continue;

  fs.writeFileSync(file, after, 'utf8');
  patchedFiles += 1;
  totalReplacements += hits;
  console.log(`[relabel-allure] ${path.relative(REPORT_DIR, file)}: ${hits} replacement(s)`);
}

if (patchedFiles === 0 && alreadyDone > 0) {
  console.log('[relabel-allure] already relabelled - nothing to do');
  process.exit(0);
}

if (patchedFiles === 0) {
  console.error(
    `[relabel-allure] found ${candidates.length} bundle(s) under ${REPORT_DIR} but none contained the ` +
    'expected labels.\n' +
    '[relabel-allure] Allure has probably changed its wording. Re-derive the literals with:\n' +
    `[relabel-allure]   grep -o '[A-Za-z_$]\\{0,24\\}:\`[^\`]\\{0,40\\}[Tt]est [Cc]ase[s]\\?[^\`]\\{0,20\\}\`' ${REPORT_DIR}/assets/index-*.js | sort -u\n` +
    '[relabel-allure] and update REPLACEMENTS in this file.'
  );
  process.exit(1);
}

console.log(`[relabel-allure] relabelled "test case" as "scenario" - ${totalReplacements} replacement(s) across ${patchedFiles} file(s)`);
