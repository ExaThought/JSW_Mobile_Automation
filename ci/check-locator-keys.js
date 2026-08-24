#!/usr/bin/env node
/*
 * Verifies that every locator key a step definition names actually exists in its locator yaml.
 *
 * Why this check exists: Sauron resolves a locator key to an xpath at RUNTIME. A key that has no
 * yaml entry resolves to null, and the step fails much later as a NullPointerException or a
 * "Unable to locate element: null" - far from the file that is actually wrong. Nothing in the
 * build catches it today, so a renamed or deleted yaml key ships silently.
 *
 * How a key is recognised: a `static final String` constant whose value is an identifier
 * (letters and digits, no slashes or brackets) is treated as a locator key. Constants holding raw
 * XPaths - which start with //, (, or xpath= - are skipped, as are UPPER_SNAKE values, which are
 * test-data keys rather than locator keys and are covered by check-payload-keys.js.
 *
 * Which yaml a class maps to:
 *   1. an explicit `new LocatorReader("locators/<name>.yaml")` in the class, when present
 *   2. otherwise the Sauron convention - ClassName -> locators/classname.yaml
 * A class with neither is skipped rather than reported: plenty of step classes hold no locators.
 *
 * Node stdlib only - no npm install, matching ci/summarize-allure.js.
 *
 * Usage:  node ci/check-locator-keys.js
 * Exit:   0 clean, 1 unresolved keys found
 */

'use strict';

const fs = require('fs');
const path = require('path');

const REPO = path.resolve(__dirname, '..');
const STEP_DIR = path.join(REPO, 'src/main/java/com/qapitol/jsw/stepdefinition');
const LOCATOR_ROOT = path.join(REPO, 'src/test/resources');

/** Recursively lists files under dir matching the extension. */
function listFiles(dir, ext, out = []) {
  if (!fs.existsSync(dir)) return out;
  for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
    const full = path.join(dir, entry.name);
    if (entry.isDirectory()) listFiles(full, ext, out);
    else if (entry.name.endsWith(ext)) out.push(full);
  }
  return out;
}

/**
 * Collects the top-level keys under `elements:` in a locator yaml.
 *
 * Deliberately not a general YAML parser - these files have one fixed shape
 * (elements: > key: > en_US: > web:), and the repo has no YAML dependency available.
 * Structural validity is yamllint's job in the PR workflow; this only needs the key names.
 */
function readLocatorKeys(yamlPath) {
  const keys = new Set();
  const lines = fs.readFileSync(yamlPath, 'utf8').split(/\r?\n/);
  let inElements = false;
  for (const line of lines) {
    if (/^elements:\s*$/.test(line)) { inElements = true; continue; }
    if (!inElements) continue;
    if (/^\S/.test(line)) { inElements = false; continue; }   // dedented out of elements
    const m = line.match(/^ {2}([A-Za-z][A-Za-z0-9_]*)\s*:\s*$/);
    if (m) keys.add(m[1]);
  }
  return keys;
}

/** Resolves the locator yaml a step class uses, or null when it declares none. */
function locatorFileFor(javaPath, source) {
  const explicit = source.match(/new\s+LocatorReader\(\s*"([^"]+)"\s*\)/);
  if (explicit) {
    const p = path.join(LOCATOR_ROOT, explicit[1]);
    return { rel: explicit[1], abs: p, explicit: true };
  }
  const className = path.basename(javaPath, '.java');
  const rel = 'locators/' + className.toLowerCase() + '.yaml';
  const abs = path.join(LOCATOR_ROOT, rel);
  return fs.existsSync(abs) ? { rel, abs, explicit: false } : null;
}

/** Constant name -> value, for identifier-valued String constants only. */
function candidateKeys(source) {
  const found = new Map();
  const re = /static\s+final\s+String\s+([A-Za-z_][A-Za-z0-9_]*)\s*=\s*"([^"]*)"/g;
  let m;
  while ((m = re.exec(source)) !== null) {
    const [, constName, value] = m;
    if (!/^[a-zA-Z][a-zA-Z0-9]*$/.test(value)) continue;   // xpath, path, or empty
    if (/^[A-Z0-9_]+$/.test(value)) continue;              // test-data key, not a locator key
    found.set(constName, value);
  }
  return found;
}

const problems = [];
let classesChecked = 0;
let keysChecked = 0;

for (const javaPath of listFiles(STEP_DIR, '.java')) {
  const source = fs.readFileSync(javaPath, 'utf8');
  const keys = candidateKeys(source);
  if (keys.size === 0) continue;

  const locatorFile = locatorFileFor(javaPath, source);
  if (!locatorFile) continue;                              // class holds no locators

  const rel = path.relative(REPO, javaPath).replace(/\\/g, '/');

  if (!fs.existsSync(locatorFile.abs)) {
    if (locatorFile.explicit) {
      problems.push(`${rel}\n    declares LocatorReader("${locatorFile.rel}") but that file does not exist`);
    }
    continue;
  }

  classesChecked++;
  const defined = readLocatorKeys(locatorFile.abs);
  const missing = [];
  for (const [constName, key] of keys) {
    keysChecked++;
    if (!defined.has(key)) missing.push(`${constName} = "${key}"`);
  }
  if (missing.length) {
    problems.push(`${rel}\n    locator file: ${locatorFile.rel}\n` +
      missing.map(x => `      missing key: ${x}`).join('\n'));
  }
}

console.log(`Locator key check: ${classesChecked} step classes, ${keysChecked} keys`);
if (problems.length === 0) {
  console.log('  OK - every locator key resolves');
  process.exit(0);
}
console.error(`\n  ${problems.length} step class(es) reference locator keys that do not exist:\n`);
problems.forEach(p => console.error('  ' + p + '\n'));
process.exit(1);
