#!/usr/bin/env node
/*
 * Verifies the chain that turns a step definition's orderType into an API payload file:
 *
 *     step definition:  orderType = "CT_SLC_SLO_CREDIT"
 *          resolves in: src/test/resources/data/<env>/default-data.yaml
 *                   to: "ctSlcSloDistributedCreditReqPayload"
 *              loading: src/test/resources/api/request/<that>.json
 *
 * Every link is checked, for EVERY environment, because each is a runtime lookup with no
 * compile-time guard:
 *
 *   - a key with no entry resolves to null, and the run dies as
 *     "FileNotFoundException: ...\api\request\null.json", which names neither the missing key
 *     nor the file that should have defined it
 *   - a key defined in one environment but not the other fails only when that environment runs,
 *     so pre-prod can sit broken while QA stays green
 *   - a mapped file that has been renamed or deleted fails the same way
 *
 * All three have happened in this repo.
 *
 * Node stdlib only - no npm install, matching ci/summarize-allure.js.
 *
 * Usage:  node ci/check-payload-keys.js
 * Exit:   0 clean, 1 broken mapping found
 */

'use strict';

const fs = require('fs');
const path = require('path');

const REPO = path.resolve(__dirname, '..');
const JAVA_DIR = path.join(REPO, 'src/main/java/com/qapitol/jsw');
const DATA_DIR = path.join(REPO, 'src/test/resources/data');
const PAYLOAD_DIR = path.join(REPO, 'src/test/resources/api/request');

function listFiles(dir, ext, out = []) {
  if (!fs.existsSync(dir)) return out;
  for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
    const full = path.join(dir, entry.name);
    if (entry.isDirectory()) listFiles(full, ext, out);
    else if (entry.name.endsWith(ext)) out.push(full);
  }
  return out;
}

/** Environments are whatever directories exist under data/ - no hardcoded list to drift. */
function environments() {
  if (!fs.existsSync(DATA_DIR)) return [];
  return fs.readdirSync(DATA_DIR, { withFileTypes: true })
    .filter(e => e.isDirectory())
    .map(e => e.name)
    .sort();
}

/**
 * Flat `KEY: "value"` pairs from a default-data.yaml.
 * Shape-specific on purpose; yamllint validates the syntax in the PR workflow.
 */
function readDataMap(file) {
  const map = new Map();
  if (!fs.existsSync(file)) return map;
  for (const line of fs.readFileSync(file, 'utf8').split(/\r?\n/)) {
    const m = line.match(/^([A-Za-z_][A-Za-z0-9_]*)\s*:\s*"?([^"#]*?)"?\s*$/);
    if (m && m[2] !== '') map.set(m[1], m[2]);
  }
  return map;
}

/**
 * Payload keys referenced from Java.
 *
 * Matched at the two call sites that exist rather than by scanning for UPPER_SNAKE strings
 * generally, which would sweep up unrelated constants.
 */
function referencedKeys() {
  const refs = new Map();   // key -> Set of "file:line"
  const patterns = [
    /orderType\s*=\s*"([A-Z][A-Z0-9_]*)"/g,
    /Data\.get\(\)\.get\(\s*"([A-Z][A-Z0-9_]*)"\s*\)/g,
  ];
  for (const file of listFiles(JAVA_DIR, '.java')) {
    const rel = path.relative(REPO, file).replace(/\\/g, '/');
    const lines = fs.readFileSync(file, 'utf8').split(/\r?\n/);
    lines.forEach((line, i) => {
      for (const re of patterns) {
        re.lastIndex = 0;
        let m;
        while ((m = re.exec(line)) !== null) {
          if (!refs.has(m[1])) refs.set(m[1], new Set());
          refs.get(m[1]).add(`${rel}:${i + 1}`);
        }
      }
    });
  }
  return refs;
}

const envs = environments();
const refs = referencedKeys();
const problems = [];

if (envs.length === 0) {
  console.error('No environment directories under src/test/resources/data - nothing to check.');
  process.exit(1);
}

const dataMaps = new Map(envs.map(env => [env, readDataMap(path.join(DATA_DIR, env, 'default-data.yaml'))]));

for (const [key, sites] of [...refs].sort()) {
  for (const env of envs) {
    const mapped = dataMaps.get(env).get(key);
    const where = [...sites].join(', ');

    if (!mapped) {
      problems.push(`${key}\n    used at: ${where}\n` +
        `      [${env}] no entry in data/${env}/default-data.yaml -> resolves to null.json`);
      continue;
    }
    const payload = path.join(PAYLOAD_DIR, mapped + '.json');
    if (!fs.existsSync(payload)) {
      problems.push(`${key}\n    used at: ${where}\n` +
        `      [${env}] maps to "${mapped}" but api/request/${mapped}.json does not exist`);
    }
  }
}

// Keys defined in one environment and not another are a latent break in the environment that is
// missing it, even when nothing references them yet.
const allDefined = new Set();
dataMaps.forEach(map => map.forEach((_, k) => { if (/^CT_|_REQ$|PAYLOAD$/.test(k)) allDefined.add(k); }));
for (const key of [...allDefined].sort()) {
  const missingIn = envs.filter(env => !dataMaps.get(env).has(key));
  if (missingIn.length && missingIn.length < envs.length) {
    problems.push(`${key}\n    defined in: ${envs.filter(e => !missingIn.includes(e)).join(', ')}\n` +
      `      missing in: ${missingIn.join(', ')} - that environment will fail if this key is used`);
  }
}

console.log(`Payload key check: ${refs.size} keys referenced, environments [${envs.join(', ')}]`);
if (problems.length === 0) {
  console.log('  OK - every payload key resolves to an existing file in every environment');
  process.exit(0);
}
console.error(`\n  ${problems.length} broken mapping(s):\n`);
problems.forEach(p => console.error('  ' + p + '\n'));
process.exit(1);
