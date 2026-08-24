#!/usr/bin/env node
/*
 * Fails the build when a credential is committed to the repository.
 *
 * This exists because a PR review found plaintext JWTs, API keys, a database password and ~20
 * account passwords across global_common.properties, global_qa.properties and
 * global_preprod.properties. Removing them once is not a fix: the same values get pasted back
 * the next time someone debugs an environment locally and commits their working copy. This
 * check is the gate that makes that a red PR instead of a review comment.
 *
 * What counts as a finding:
 *
 *   1. a JWT anywhere in a tracked file (header.payload.signature, or a bare "eyJ..." header)
 *   2. a PEM private key block
 *   3. a secret-named .properties key with a NON-EMPTY default - i.e. ${ENV_VAR:value} where a
 *      secret should read ${ENV_VAR:}
 *   4. a secret-named key assigned a bare literal, with no ${...} indirection at all
 *   5. a long high-entropy string on a line whose key looks like a credential
 *
 * The matched VALUE is never printed. CI logs are readable by anyone with repository access, so
 * echoing a secret to prove it exists would defeat the point - findings report the file, line,
 * key and a masked length instead.
 *
 * Deliberately-fake values are allowed through, so negative tests keep working: see FAKE_VALUES.
 *
 * Node stdlib only - no npm install, matching ci/check-payload-keys.js.
 *
 * Usage:  node ci/check-secrets.js
 * Exit:   0 clean, 1 secret found
 */

'use strict';

const fs = require('fs');
const path = require('path');

const REPO = path.resolve(__dirname, '..');

// Directories that never contain source we control.
const SKIP_DIRS = new Set([
  '.git', 'node_modules', 'target', 'allure-results', 'allure-report',
  'sauronFiles', 'profiles', 'saved-reports', '.idea',
]);

// Only text formats where a credential could plausibly be pasted.
const SCAN_EXT = ['.properties', '.yaml', '.yml', '.xml', '.java', '.json', '.js', '.md', '.feature'];

// This file describes the patterns it hunts for, so it would flag itself.
const SKIP_FILES = new Set([path.join(REPO, 'ci', 'check-secrets.js')]);

// A key name that implies its value is a credential.
const SECRET_KEY = /(password|passwd|pwd|secret|token|apikey|api_key|auth|credential|private_key)/i;

// ...except when the key's LAST segment says it holds something else. "token" appears in
// ccpTokenUrl (a URL path), omsTokenResponseKey (a JSON field name) and tokencheck.user.email
// (an address) - none of which are credentials. Checked against the final dotted/camel segment
// so that XApiKey and oms.user.password still match above.
const NOT_SECRET_KEY =
  /(url|uri|path|endpoint|host|port|email|username|user|responsekey|expireat|createdat|schema|name)$/i;

function looksLikeSecretKey(key) {
  if (!SECRET_KEY.test(key)) return false;
  const lastDotted = key.split('.').pop();
  // Split camelCase too: ccpTokenUrl -> "Url", XApiKey -> "Key".
  const lastCamel = lastDotted.replace(/([a-z0-9])([A-Z])/g, '$1 $2').split(' ').pop();
  return !NOT_SECRET_KEY.test(lastDotted) && !NOT_SECRET_KEY.test(lastCamel);
}

// Values that are obviously not real credentials. Negative tests need a wrong-but-present
// password, and removed keys leave a marker behind, so both have to survive this check.
const FAKE_VALUES = [
  /^deliberately-invalid/i,
  /^<.*>$/,                 // <removed - ...>, <qa-token>, <preprod-token>
  /^\$\{[^}]*\}$/,          // an unresolved placeholder is not a value
  /^(changeme|placeholder|example|dummy|redacted|none|null|true|false)$/i,
];

const JWT_FULL = /eyJ[A-Za-z0-9_-]{6,}\.[A-Za-z0-9_-]{6,}\.[A-Za-z0-9_-]{6,}/;
const JWT_HEAD = /eyJ[A-Za-z0-9_-]{20,}/;
const PEM = /-----BEGIN (RSA |EC |OPENSSH |PGP )?PRIVATE KEY-----/;

// 32+ chars of unbroken high-entropy alphanumerics - the shape of XApiKey / salesOppApiKey.
const LONG_ENTROPY = /[A-Za-z0-9]{32,}/;

/**
 * The files to scan: what git TRACKS, not what is on disk.
 *
 * Only tracked files can leak a secret to the remote, and scanning the working tree instead
 * would fail the build on a developer's own gitignored scratch notes - which is both wrong and
 * the fastest way to get a check like this disabled. Falls back to a filesystem walk when git
 * is unavailable, so the script still does something useful outside a checkout.
 */
function listFiles() {
  try {
    const { execFileSync } = require('child_process');
    const out = execFileSync('git', ['-C', REPO, 'ls-files', '-z'], {
      encoding: 'utf8',
      maxBuffer: 32 * 1024 * 1024,
    });
    return out.split('\0')
      .filter(Boolean)
      .filter(rel => SCAN_EXT.includes(path.extname(rel)))
      .filter(rel => !rel.split('/').some(seg => SKIP_DIRS.has(seg)))
      .map(rel => path.join(REPO, rel))
      .filter(full => fs.existsSync(full));
  } catch {
    console.log('  (git unavailable - falling back to a filesystem walk)');
    return walk(REPO);
  }
}

function walk(dir, out = []) {
  let entries;
  try {
    entries = fs.readdirSync(dir, { withFileTypes: true });
  } catch {
    return out;
  }
  for (const entry of entries) {
    if (SKIP_DIRS.has(entry.name)) continue;
    const full = path.join(dir, entry.name);
    if (entry.isDirectory()) walk(full, out);
    else if (SCAN_EXT.includes(path.extname(entry.name))) out.push(full);
  }
  return out;
}

function isFake(value) {
  const v = value.trim();
  if (v === '') return true;
  return FAKE_VALUES.some(re => re.test(v));
}

/** A finding describes the value without disclosing it. */
function mask(value) {
  const v = value.trim();
  return `${v.length} chars starting "${v.slice(0, 4)}..."`;
}

const FILES = listFiles();

const findings = [];
const seen = new Set();
function report(file, lineNo, what, detail) {
  // One finding per line. A committed JWT trips both the JWT rule and the entropy rule, and
  // reporting it twice makes the output look like twice the problem.
  const at = `${file}:${lineNo}`;
  if (seen.has(at)) return;
  seen.add(at);
  findings.push({
    file: path.relative(REPO, file).replace(/\\/g, '/'),
    line: lineNo,
    what,
    detail,
  });
}

/**
 * The default side of a ${ENV_VAR:default} placeholder, or null when the line is not one.
 *
 * Mirrors ConfigUtil.getSecretValue's parse - split on the FIRST colon only - so a default that
 * itself contains colons (a URL, a JDBC string) is returned whole rather than truncated.
 */
function placeholderDefault(value) {
  const v = value.trim();
  if (!v.startsWith('${') || !v.endsWith('}')) return null;
  const content = v.slice(2, -1);
  const colon = content.indexOf(':');
  return colon === -1 ? '' : content.slice(colon + 1);
}

for (const file of FILES) {
  if (SKIP_FILES.has(file)) continue;

  let text;
  try {
    text = fs.readFileSync(file, 'utf8');
  } catch {
    continue;
  }

  const isProperties = file.endsWith('.properties');
  const lines = text.split(/\r?\n/);

  lines.forEach((line, i) => {
    const lineNo = i + 1;
    const trimmed = line.trim();

    // A commented-out line still leaks its value, so comments are scanned for JWTs and PEM
    // blocks - but not for the key/value rules, which would flag documentation of the rules.
    if (PEM.test(line)) {
      report(file, lineNo, 'PEM private key', 'remove it and rotate the key');
      return;
    }
    const jwt = line.match(JWT_FULL) || line.match(JWT_HEAD);
    if (jwt) {
      report(file, lineNo, 'JWT', `${mask(jwt[0])} - inject it via an environment variable and ROTATE this token`);
      return;
    }

    const isComment = trimmed.startsWith('#') || trimmed.startsWith('//') || trimmed.startsWith('*');
    if (isComment || trimmed === '') return;

    if (!isProperties) return;

    // .properties accepts both = and : as the key/value separator.
    const sep = (() => {
      const eq = line.indexOf('=');
      const co = line.indexOf(':');
      if (eq === -1) return co;
      if (co === -1) return eq;
      return Math.min(eq, co);
    })();
    if (sep <= 0) return;

    const key = line.slice(0, sep).trim();
    const value = line.slice(sep + 1).trim();
    if (!looksLikeSecretKey(key) || value === '') return;

    const dflt = placeholderDefault(value);
    if (dflt === null) {
      // No ${...} at all: a credential-named key holding a bare literal.
      if (!isFake(value)) {
        report(file, lineNo, `bare literal in secret key "${key}"`,
          `${mask(value)} - change to \${ENV_VAR:} and inject the value`);
      }
      return;
    }
    if (!isFake(dflt)) {
      report(file, lineNo, `non-empty default for secret key "${key}"`,
        `${mask(dflt)} - change to \${ENV_VAR:} and inject the value`);
    }
  });

  // A long high-entropy token on a credential-named line, in any scanned format. Runs over the
  // whole file rather than per-line so the .properties rules above get first say.
  if (!isProperties) {
    lines.forEach((line, i) => {
      if (!SECRET_KEY.test(line)) return;
      const m = line.match(LONG_ENTROPY);
      if (!m) return;
      // Ignore hashes/ids that are all one case with no digits, and Java identifiers.
      if (!/[0-9]/.test(m[0]) || !/[a-z]/.test(m[0]) || !/[A-Z0-9]/.test(m[0])) return;
      report(file, i + 1, 'possible hard-coded credential',
        `${mask(m[0])} - if this is a secret, inject it; if not, this check needs an exemption`);
    });
  }
}

const scanned = FILES.length;
console.log(`Secret check: ${scanned} tracked text files scanned`);

if (findings.length === 0) {
  console.log('  OK - no committed credentials found');
  process.exit(0);
}

console.error(`\n  ${findings.length} committed credential(s):\n`);
for (const f of findings) {
  console.error(`  ${f.file}:${f.line}`);
  console.error(`    ${f.what}`);
  console.error(`      ${f.detail}\n`);
}
console.error('  Secrets belong in GitHub Actions secrets or Jenkins credentials, injected as');
console.error('  environment variables. Every key in global_*.properties reads ${ENV_VAR:} with an');
console.error('  empty default for exactly this reason - see README.md "Required secrets".');
console.error('  A value that is genuinely not a secret (a deliberately-wrong test password, for');
console.error('  instance) belongs in FAKE_VALUES in this script, with a comment saying why.\n');
process.exit(1);
