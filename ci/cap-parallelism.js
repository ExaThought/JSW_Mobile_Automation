'use strict';
/*
 * Caps a TestNG suite's parallelism to what a LOCAL Selenium grid on this agent can actually serve.
 *
 *   node ci/cap-parallelism.js <suite-file> <cpu-count>
 *
 * WHY THIS IS A SCRIPT AND NOT grep/sed IN THE JENKINSFILE
 * The previous inline version was `grep -oP 'thread-count="\K[0-9]+' "$SUITE_FILE" | head -1`, and
 * it had two defects:
 *
 *   1. It matched data-provider-thread-count too, because `thread-count="` is a substring of it.
 *   2. It matched the FIRST occurrence anywhere in the file - including inside XML comments. The
 *      comment block at the top of testng_bdd.xml documents both axes and writes them out
 *      literally, so the clamp was reading prose rather than the suite's attributes.
 *
 * Both are silent: the clamp reports a number, it just is not the suite's. This reads the <suite>
 * open tag only, which is the sole place either attribute has any effect.
 *
 * WHAT IT CAPS
 * Concurrent browser sessions = thread-count (blocks at once) x data-provider-thread-count
 * (scenarios at once inside a block). Selenium sizes a local node's Chrome slots from the CPU
 * count, so demand above that means sessions that never start. Jenkins build #244: 1 x 5 = 5
 * sessions demanded, "Detected 2 available processors" -> "Adding Chrome ... 2 times", and the
 * queuing loss that followed cost 44 scenarios.
 *
 * data-provider-thread-count is lowered FIRST. It is the axis that shares one CCP account across
 * concurrent scenarios (see item 3 in testng_bdd.xml), so reducing it removes risk as well as
 * demand, whereas each <test> block has an account of its own.
 *
 * It only ever LOWERS. Raising past the authored value would break the standing rule that two
 * blocks running at the same time may not share an account.
 */

const fs = require('fs');

const [, , suiteFile, cpuArg] = process.argv;
if (!suiteFile) {
  console.error('usage: node ci/cap-parallelism.js <suite-file> <cpu-count>');
  process.exit(2);
}

// `--demand` prints the concurrent-session count and changes nothing. The remote-grid pre-flight
// uses it so its "grid advertises N slots but the suite wants M" warning is derived from the suite
// rather than a hardcoded number that goes stale the moment either axis is edited.
const demandOnly = cpuArg === '--demand';
const cpus = demandOnly ? 0 : Math.max(1, parseInt(cpuArg, 10) || 1);
const src = fs.readFileSync(suiteFile, 'utf8');

// The <suite ...> open tag, and nothing else. Attributes outside it are documentation.
const tagMatch = /<suite\b[^>]*>/.exec(src);
if (!tagMatch) {
  console.error(`cap-parallelism: no <suite> tag in ${suiteFile} - leaving it alone.`);
  process.exit(0);
}
const tag = tagMatch[0];

// Negative lookbehind so `thread-count` cannot match `data-provider-thread-count`.
const readAttr = (name, text) => {
  const re = new RegExp(`(?<![-\\w])${name}="(\\d+)"`);
  const m = re.exec(text);
  return m ? parseInt(m[1], 10) : null;
};

const authoredBlocks = readAttr('thread-count', tag) ?? 1;
const authoredScenarios = readAttr('data-provider-thread-count', tag) ?? 1;
const demand = authoredBlocks * authoredScenarios;

if (demandOnly) {
  // Bare number on stdout, nothing else - it is consumed by $(...) in the pipeline.
  process.stdout.write(String(demand));
  process.exit(0);
}

console.log(`cap-parallelism: ${suiteFile}`);
console.log(`  agent CPUs                     : ${cpus}`);
console.log(`  thread-count (blocks)          : ${authoredBlocks}`);
console.log(`  data-provider-thread-count     : ${authoredScenarios}`);
console.log(`  concurrent sessions demanded    : ${demand}`);

if (demand <= cpus) {
  console.log(`  OK - ${demand} session(s) for ${cpus} CPU(s); every session gets a slot.`);
  process.exit(0);
}

// Lower the scenario axis first; only touch the block axis if it alone exceeds the CPUs.
let blocks = authoredBlocks;
let scenarios = authoredScenarios;
if (blocks > cpus) {
  blocks = cpus;
  scenarios = 1;
} else {
  scenarios = Math.max(1, Math.floor(cpus / blocks));
}
// Never raise.
blocks = Math.min(blocks, authoredBlocks);
scenarios = Math.min(scenarios, authoredScenarios);

let newTag = tag;
if (scenarios !== authoredScenarios) {
  newTag = newTag.replace(
    /(?<![-\w])data-provider-thread-count="\d+"/,
    `data-provider-thread-count="${scenarios}"`
  );
}
if (blocks !== authoredBlocks) {
  newTag = newTag.replace(
    /(?<![-\w])thread-count="\d+"/,
    `thread-count="${blocks}"`
  );
}

if (newTag === tag) {
  console.log('  nothing to change (already at the floor of 1 x 1).');
  process.exit(0);
}

// Replace the tag by index, so identical text elsewhere in the file cannot be hit.
const out = src.slice(0, tagMatch.index) + newTag + src.slice(tagMatch.index + tag.length);
fs.writeFileSync(suiteFile, out);

console.log(`  CAPPED for this build only: ${authoredBlocks}x${authoredScenarios} -> ${blocks}x${scenarios}`
  + ` (${blocks * scenarios} session(s) for ${cpus} CPU(s))`);
console.log('  This is a workaround, not the fix. Point GRID_URL at a host with enough cores');
console.log('  (docker-compose.grid.yml) to run the authored parallelism.');
