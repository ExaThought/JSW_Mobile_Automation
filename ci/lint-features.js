#!/usr/bin/env node
/*
 * Lints Cucumber feature files for the mistakes that survive to runtime in this framework.
 *
 * Gherkin is not compiled, so a malformed Examples table or a placeholder with no column fails
 * during a run - after a browser has launched and a user has logged in - rather than at build
 * time. These rules are the ones that have actually cost runs here, not general style.
 *
 *   F1  duplicate scenario name across the repo
 *       FailureDiagnostics names its screenshot and page-source files after the scenario, so two
 *       scenarios sharing a name overwrite each other's evidence.
 *   F2  Examples table has a blank column heading
 *   F3  Examples row has a different cell count from its header
 *   F4  <placeholder> used in a step with no matching Examples column - resolves to the literal
 *       text "<placeholder>" and the step fails on a nonsense value
 *   F5  Examples column never referenced by any step in its outline - dead data
 *   F6  Scenario Outline with no Examples table
 *   F7  tab character (Gherkin is whitespace-significant)
 *   F8  Feature with no tags - cannot be selected by any testng filter, so it never runs
 *
 * Node stdlib only - no npm install, matching ci/summarize-allure.js.
 *
 * Usage:  node ci/lint-features.js
 * Exit:   0 clean, 1 findings
 */

'use strict';

const fs = require('fs');
const path = require('path');

const REPO = path.resolve(__dirname, '..');
const FEATURE_DIR = path.join(REPO, 'src/test/resources/features');

function listFeatures(dir, out = []) {
  if (!fs.existsSync(dir)) return out;
  for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
    const full = path.join(dir, entry.name);
    if (entry.isDirectory()) listFeatures(full, out);
    else if (entry.name.endsWith('.feature')) out.push(full);
  }
  return out;
}

/** Splits "| a | b |" into ["a","b"], preserving empty cells so F2/F3 can see them. */
function tableCells(line) {
  const t = line.trim();
  return t.slice(1, -1).split('|').map(c => c.trim());
}

const findings = [];
const scenarioNames = new Map();   // name -> ["file:line", ...]
let featureCount = 0;
let scenarioCount = 0;

function report(rule, file, line, message) {
  findings.push({ rule, where: `${file}:${line}`, message });
}

for (const file of listFeatures(FEATURE_DIR)) {
  const rel = path.relative(REPO, file).replace(/\\/g, '/');
  const raw = fs.readFileSync(file, 'utf8');
  const lines = raw.split(/\r?\n/);
  featureCount++;

  let featureTagged = false;
  let sawFeature = false;

  // Outline state, flushed when the next scenario or EOF is reached.
  let outline = null;   // { name, line, steps:[], headers:[], headerLine, rowCount }

  const flushOutline = () => {
    if (!outline) return;
    if (outline.headers === null) {
      report('F6', rel, outline.line, `Scenario Outline "${outline.name}" has no Examples table`);
    } else {
      const body = outline.steps.join('\n');
      const used = new Set();
      let m;
      const re = /<([^<>]+)>/g;
      while ((m = re.exec(body)) !== null) used.add(m[1].trim());

      for (const ph of used) {
        if (!outline.headers.includes(ph)) {
          report('F4', rel, outline.line,
            `"${outline.name}" uses <${ph}> but the Examples table has no such column ` +
            `(columns: ${outline.headers.filter(h => h).join(', ') || 'none'})`);
        }
      }
      outline.headers.forEach(h => {
        if (h && !used.has(h)) {
          report('F5', rel, outline.headerLine,
            `"${outline.name}" Examples column "${h}" is never used by any step`);
        }
      });
    }
    outline = null;
  };

  lines.forEach((line, idx) => {
    const n = idx + 1;
    const trimmed = line.trim();

    if (line.includes('\t')) report('F7', rel, n, 'contains a tab character');

    if (/^@/.test(trimmed) && !sawFeature) featureTagged = true;

    if (/^Feature:/.test(trimmed)) {
      sawFeature = true;
      if (!featureTagged) {
        report('F8', rel, n,
          'Feature has no tags - no testng cucumber.filter.tags value can select it, so it never runs');
      }
      return;
    }

    // Commented-out scenarios are intentionally ignored: this repo uses them to park known
    // product defects with an explanation, and linting them would be noise.
    if (trimmed.startsWith('#')) return;

    const scenario = trimmed.match(/^(Scenario Outline|Scenario Template|Scenario):\s*(.*)$/);
    if (scenario) {
      flushOutline();
      scenarioCount++;
      const name = scenario[2].trim();
      if (!scenarioNames.has(name)) scenarioNames.set(name, []);
      scenarioNames.get(name).push(`${rel}:${n}`);

      if (/^Scenario Outline|^Scenario Template/.test(scenario[1])) {
        outline = { name, line: n, steps: [], headers: null, headerLine: n };
      }
      return;
    }

    if (outline && /^(Given|When|Then|And|But)\b/.test(trimmed)) {
      outline.steps.push(trimmed);
      return;
    }

    if (trimmed.startsWith('|')) {
      if (!outline) return;                       // data table on a plain scenario
      const cells = tableCells(line);
      if (outline.headers === null) {
        outline.headers = cells;
        outline.headerLine = n;
        cells.forEach((c, i) => {
          if (c === '') {
            report('F2', rel, n,
              `"${outline.name}" Examples column ${i + 1} has a blank heading - the column is unusable`);
          }
        });
      } else if (cells.length !== outline.headers.length) {
        report('F3', rel, n,
          `"${outline.name}" Examples row has ${cells.length} cells but the header has ` +
          `${outline.headers.length}`);
      }
    }
  });

  flushOutline();
}

for (const [name, places] of scenarioNames) {
  if (places.length > 1) {
    findings.push({
      rule: 'F1',
      where: places[0],
      message: `Scenario name "${name}" is used ${places.length} times ` +
        `(${places.join(', ')}) - FailureDiagnostics writes evidence per scenario name, ` +
        `so these overwrite each other`,
    });
  }
}

/*
 * Blocking vs advisory.
 *
 * Only unambiguous defects fail the build. F1, F5 and F8 are reported but do not block:
 *   F1 - the web and msite suites intentionally mirror each other's scenario names and never run
 *        in the same testng <test>, so most duplicates are by design. Worth seeing, not worth
 *        blocking a merge over.
 *   F5 - an unused Examples column is usually an assertion someone meant to write.
 *   F8 - an untagged feature may be deliberately parked.
 *
 * Pass --strict to fail on warnings too, e.g. for a scheduled hygiene run.
 */
const BLOCKING = new Set(['F2', 'F3', 'F4', 'F6', 'F7']);
const strict = process.argv.includes('--strict');

const errors = findings.filter(f => BLOCKING.has(f.rule));
const warnings = findings.filter(f => !BLOCKING.has(f.rule));

function print(label, list, stream) {
  if (list.length === 0) return;
  const byRule = list.reduce((acc, f) => { (acc[f.rule] = acc[f.rule] || []).push(f); return acc; }, {});
  stream(`\n  ${list.length} ${label}:\n`);
  for (const rule of Object.keys(byRule).sort()) {
    stream(`  [${rule}] ${byRule[rule].length}`);
    byRule[rule].forEach(f => stream(`      ${f.where}\n        ${f.message}`));
    stream('');
  }
}

console.log(`Feature lint: ${featureCount} feature files, ${scenarioCount} scenarios`);
print('warning(s) - not blocking', warnings, msg => console.log(msg));
print('error(s)', errors, msg => console.error(msg));

if (errors.length === 0 && (!strict || warnings.length === 0)) {
  console.log(`  OK - no blocking findings${warnings.length ? ` (${warnings.length} warning(s) above)` : ''}`);
  process.exit(0);
}
process.exit(1);
