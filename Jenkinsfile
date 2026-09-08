/*
 * JSW One MSME - UI automation pipeline (Linux agent).
 *
 * Adds on top of the existing "pick a branch and run" job:
 *   - an ENV parameter, wired to the framework's own -Denv mechanism (EnvConfig.resolve)
 *   - an Allure HTML report published on the build page
 *   - a one-page run summary in HTML and PDF, emailed to Outlook
 *
 * Requires on the agent: JDK 11, Maven, Chrome/Chromium (already needed by the tests),
 * and Node (stdlib only - no npm install) for ci/summarize-allure.js.
 * Requires plugins: Allure, Email Extension, HTML Publisher.
 */

pipeline {
    agent any

    /* Configuring a tool under Manage Jenkins -> Tools does NOT put it on PATH; a pipeline has to
     * request it. Without this block the agent reported "mvn: not found" and "node: not found"
     * (exit 127) even though both installations existed, because `sh` runs a non-login,
     * non-interactive shell that never sources the Jenkins user's profile.
     *
     * The names below must match the Tools page EXACTLY, case included - a mismatch fails the
     * build with "No tool named ... found" before any stage runs:
     *   jdk11         JDK installation, JDK 11
     *   maven-3.9.0   Maven installation, auto-installed from Apache
     *   NodeJS        NodeJS installation, auto-installed from nodejs.org (20.x)
     *
     * jdk sets JAVA_HOME, which Maven requires. It must be a full JDK and not a JRE: the pom
     * compiles with source/target 11, so javac has to be present.
     *
     * Node 20 matters: ci/summarize-allure.js uses optional chaining and nullish coalescing,
     * so anything older than Node 14 dies with a SyntaxError rather than a readable message.
     */
    tools {
        jdk 'jdk11'
        maven 'maven-3.9.0'
        nodejs 'NodeJS'
    }

    parameters {
        choice(
            name: 'ENV',
            // 'prod' is offered now that all four prerequisites exist: EnvConfig accepts it,
            // global_prod.properties, data/prod/ and testng_prod_sanity.xml are all present.
            //
            // qa stays FIRST so it remains the default for triggered and scheduled builds -
            // selecting production has to be a deliberate act, never something a cron inherits.
            //
            // Choosing prod is safe by construction: the guard at the top of the Test stage
            // refuses ENV=prod with any suite other than testng_prod_sanity.xml, and refuses a
            // TAGS filter outright, so the ~60 order-creating scenarios cannot be reached.
            choices: ['qa', 'preprod', 'prod'],
            description: 'Target environment. Passed as -Denv and resolved by EnvConfig; ' +
                         'selects global_&lt;env&gt;.properties and the data/&lt;env&gt;/ test-data directory. ' +
                         'prod runs READ-ONLY against www.jswonemsme.com and is only permitted with ' +
                         'SUITE=testng_prod_sanity.xml and an empty TAGS.'
        )
        string(
            name: 'BRANCH_NAME',
            defaultValue: 'develop',
            description: 'Git branch to check out and run. develop is the main branch of this repo.'
        )
        string(
            name: 'TAGS',
            defaultValue: '',
            description: 'Optional Cucumber tag filter, e.g. "@footer or @regression". ' +
                         'Leave blank to use whatever testng_bdd.xml already defines. ' +
                         'See the note in the Test stage before relying on this.'
        )
        choice(
            name: 'SUITE',
            // testng_prod_sanity.xml is listed LAST and is the only suite ENV=prod accepts.
            // It must be COMMITTED for this choice to work: Jenkins checks out from the remote,
            // so an untracked file is simply absent from the workspace and the run fails on a
            // missing suite rather than on anything informative.
            choices: ['testng_bdd.xml', 'testng_mobilebdd.xml', 'testng_prod_sanity.xml'],
            description: 'TestNG suite file. Maps to the pom\'s ${suiteXmlFile} property. ' +
                         'testng_prod_sanity.xml is the read-only suite selecting ' +
                         '"@productionSanity and @P0", and is required when ENV=prod; it is also ' +
                         'safe to run against qa or preprod.'
        )
        string(
            name: 'GRID_URL',
            defaultValue: '',
            /* No session count quoted here on purpose. Jenkins renders parameter descriptions
             * before any step runs, so this text cannot be computed from the suite - and the last
             * hardcoded number ("5") silently went stale the moment either parallelism axis was
             * edited, which is the same drift that broke the CPU clamp. The authoritative figure is
             * thread-count x data-provider-thread-count on the <suite> tag; the Test stage
             * prints it via ci/cap-parallelism.js. */
            description: 'Remote Selenium Grid, e.g. http://selenium-grid.internal:4444. Leave ' +
                         'blank to use the grid Sauron starts on the agent, which sizes its ' +
                         'session slots from the CPU count. If the suite asks for more concurrent ' +
                         'sessions than the agent has cores, ci/cap-parallelism.js lowers the ' +
                         'suite for that build, so an undersized agent runs slower instead of ' +
                         'failing - the console prints the demand and any cap it applied. Set this ' +
                         'to run the authored parallelism instead. See docker-compose.grid.yml.'
        )
        string(
            name: 'RECIPIENTS',
            defaultValue: 'ritika.jaggi@jsw.in,v_akash.garg@jsw.in, piyush.dwivedi@jsw.in',
            description: 'Comma-separated Outlook recipients for the summary email.'
        )
        booleanParam(
            name: 'USE_JENKINS_CREDENTIALS',
            defaultValue: false,
            description: 'Bind per-environment Jenkins credentials over the committed ' +
                         'global_&lt;env&gt;.properties defaults. Leave OFF until every id listed in ' +
                         'the Test stage exists for BOTH environments - withCredentials aborts ' +
                         'the stage when an id is missing, which produces an UNSTABLE build with ' +
                         'zero tests executed and looks exactly like a test failure.'
        )
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '30', artifactNumToKeepStr: '15'))
        timeout(time: 3, unit: 'HOURS')
    }

    environment {
        SUMMARY_BASE = 'target/run-summary'
        MAVEN_OPTS   = '-Xmx2g'
    }

    stages {

        stage('Checkout') {
            steps {
                script {
                    /* This is a SECOND checkout. Jenkins has already cloned the repo once to read
                     * this Jenkinsfile; this one re-checks-out at ${BRANCH_NAME}, so the pipeline
                     * definition can stay on develop while a feature branch is tested.
                     *
                     * The credential is INHERITED from the job's own SCM config rather than named
                     * here. A hardcoded id fails in a way the log barely explains: when the id does
                     * not exist, the git plugin logs one "CredentialId ... could not be found"
                     * warning and then runs git UNAUTHENTICATED, so GitHub replies "Password
                     * authentication is not supported for Git operations" and it reads like a bad
                     * token rather than a missing credential. Credential ids are usually generated
                     * UUIDs, so the odds of a guessed name matching are nil.
                     *
                     * scm only exists in a "Pipeline script from SCM" job, hence the try/catch:
                     * an inline-script job falls back to GIT_CREDENTIALS_ID, or to no credential
                     * at all for a public repo.
                     */
                    def gitCredentialsId = env.GIT_CREDENTIALS_ID
                    if (!gitCredentialsId) {
                        try {
                            gitCredentialsId = scm.userRemoteConfigs[0].credentialsId
                        } catch (ignored) {
                            gitCredentialsId = ''
                        }
                    }
                    echo "Checkout stage using git credential id: ${gitCredentialsId ?: '(none)'}"

                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: "*/${params.BRANCH_NAME}"]],
                        userRemoteConfigs: [[
                            url: 'https://github.com/JSWOne/msme-fe-utaf-automation.git',
                            credentialsId: gitCredentialsId
                        ]]
                    ])

                    currentBuild.displayName = "#${env.BUILD_NUMBER} ${params.ENV} ${params.BRANCH_NAME}"
                    /* env= is machine-read, not decoration: the mail stage walks back through
                     * previous builds looking for this token so the trend only ever compares a run
                     * against another run on the SAME environment. Keep it first and keep the
                     * "env=<value>" spelling. */
                    currentBuild.description = "env=${params.ENV} | ${params.TAGS ?: 'suite defaults'}"
                }
            }
        }

        stage('Test') {
            steps {
                /* allure-results must start empty. The summariser collapses duplicate
                 * scenario names across attempts, but results left over from a previous
                 * build would still be counted as part of this run. */
                /* Preserve the PREVIOUS run's summary before anything deletes it, so the mail can
                 * split failures into new / recurring / fixed.
                 *
                 * The Jenkins workspace persists between builds, so last build's
                 * target/run-summary.json is still on disk right now - the rm below and
                 * `mvn clean` are what remove it. Copying it out of target/ first is why this
                 * needs neither the Copy Artifact plugin nor an authenticated call to the Jenkins
                 * API to read our own previous output.
                 *
                 * Absent on a first build or after a workspace wipe; ci/email-body.js degrades to
                 * "comparison not available" rather than failing. */
                /* FILED UNDER THE ENV THAT WROTE IT, not the env being built now.
                 *
                 * One job runs qa, pre-prod and prod out of the same workspace, so the summary
                 * still on disk belongs to whichever env ran last. A single
                 * .previous-run-summary.json therefore handed a prod build the qa run to compare
                 * against - a 7-scenario read-only suite against a 200-scenario regression, which
                 * labels every prod failure NEW and reports every qa scenario as fixed.
                 *
                 * The env is read out of the file itself (summarize-allure.js writes it) rather
                 * than taken from params.ENV, which would mislabel a leftover qa summary as prod.
                 * sed, not node: this runs before the tool check, and the mail stage is where a
                 * missing node is diagnosed. */
                sh '''
                    if [ -f target/run-summary.json ]; then
                        LAST_ENV=$(sed -n 's/.*"env"[[:space:]]*:[[:space:]]*"\\([^"]*\\)".*/\\1/p' \
                                   target/run-summary.json | head -1)
                        if [ -n "$LAST_ENV" ]; then
                            cp target/run-summary.json ".previous-run-summary-$LAST_ENV.json"
                            echo "Preserved the previous $LAST_ENV run summary as .previous-run-summary-$LAST_ENV.json"
                        else
                            echo "Leftover run-summary.json records no env - not preserved, as it cannot be attributed to one"
                        fi
                    else
                        echo "No previous run summary in this workspace - first build here, or it was cleaned"
                    fi
                '''

                sh 'rm -rf allure-results target/run-summary.* target/email-body.html && mkdir -p allure-results'

                /* ---------------------------------------------------------------------
                 * Kill Selenium leftovers BEFORE the JVM starts.
                 *
                 * Sauron randomises the hub's HTTP port per run, but Grid's ZeroMQ event bus is
                 * FIXED on 4442/4443. A hub or node left behind by a crashed build still holds
                 * those ports and still advertises on that bus, so the new hub either cannot bind
                 * at all or - worse - accepts the stale node's registration and routes sessions to
                 * a process that is already dead.
                 *
                 * Both failure shapes were observed:
                 *   hub cannot bind  -> SauronWebListener.onStartTest fails with "Timed out waiting
                 *                       for 127.0.0.1 to initialize", no session is ever created,
                 *                       and EVERY scenario then dies on
                 *                       "IllegalStateException: Driver not initialized".
                 *   routed to a dead node -> "session not created: Chrome instance exited".
                 *
                 * Stale NODES matter as much as stale hubs, which is why both are killed. Every
                 * command is `|| true`: a clean agent has nothing to kill and that is not an error.
                 * ------------------------------------------------------------------- */
                sh '''
                    echo "--- Selenium leftovers before cleanup ---"
                    pgrep -af selenium-server || echo "  none"
                    (command -v lsof >/dev/null && lsof -i:4442 -i:4443) || true

                    pkill -9 -f selenium-server        || true
                    pkill -9 -f chromedriver           || true
                    pkill -9 -f "chrome.*--test-type"  || true
                    (command -v lsof >/dev/null && lsof -ti:4442 -ti:4443 | xargs -r kill -9) || true
                    sleep 2

                    echo "--- after cleanup (4442/4443 must be free) ---"
                    if command -v lsof >/dev/null && lsof -i:4442 -i:4443; then
                        echo "WARNING: 4442/4443 are STILL held. The hub will fail to start and every"
                        echo "scenario will report 'Driver not initialized'. The owning process is"
                        echo "listed above and is not owned by this build."
                    else
                        echo "  4442/4443 free"
                    fi
                '''

                /* ---------------------------------------------------------------------
                 * PRODUCTION GUARD - deliberately OUTSIDE catchError, so it aborts the
                 * build rather than degrading it to UNSTABLE and carrying on.
                 *
                 * Three independent barriers stop an order-creation scenario reaching
                 * production; this is the first and the only one a human sees before the
                 * JVM starts:
                 *   1. this guard              - ENV=prod accepts one suite and no ad-hoc tags
                 *   2. the "@productionSanity and @P0" filter in testng_prod_sanity.xml
                 *   3. global_prod.properties  - salesOppUrl / OMS / DB deliberately unset
                 *
                 * TAGS is refused on prod because it would override the suite's own filter
                 * and could select SFSlc/SFSlo, which place real, invoiced orders.
                 * --------------------------------------------------------------------- */
                script {
                    if (params.ENV == 'prod') {
                        if (params.SUITE != 'testng_prod_sanity.xml') {
                            error("ENV=prod is only allowed with SUITE=testng_prod_sanity.xml. " +
                                  "'${params.SUITE}' contains scenarios that create real orders, " +
                                  "carts and customer accounts.")
                        }
                        if (params.TAGS?.trim()) {
                            error("TAGS must be empty when ENV=prod. The production suite selects " +
                                  "scenarios by \"@productionSanity and @P0\"; an ad-hoc filter here " +
                                  "could select order-creation scenarios.")
                        }
                        // Single-quoted: nothing here interpolates, so the tag expression can be
                        // quoted inline without escaping.
                        echo 'PRODUCTION RUN - read-only suite selecting "@productionSanity and @P0" against www.jswonemsme.com'
                    }
                }

                /* ---------------------------------------------------------------------
                 * Browser pre-flight. Deliberately OUTSIDE the catchError below, so a missing
                 * or unusable browser fails the build here with a readable message instead of
                 * producing several hundred identical scenario failures.
                 *
                 * Why this is needed: sessions.yaml is `local: true`, so Sauron starts a grid
                 * on this agent and drives a real Chrome. The agent is `agent any` and nothing
                 * pins Chrome to it. When Chrome is absent, every session fails to start and
                 * every scenario reports Sauron's screenshot-listener artifact - "Driver not
                 * initialized. Is @WebTest/@MobileTest missing on class/method?" - which names
                 * neither Chrome nor the agent. The only other complaint the pipeline made was
                 * a warning in the reporting stage, long after the suite had already gone red.
                 *
                 * The leftovers below matter for the same reason: Selenium Grid's ZeroMQ event
                 * bus is FIXED on 4442/4443, so a selenium-server orphaned by an earlier build
                 * keeps advertising there, registers with this run's hub, and gets sessions
                 * routed to it - a dead node. `cleanup` removes profiles/ and sauronFiles/ at
                 * the END of a build, which does nothing for a build that was killed or for a
                 * process that outlived its workspace. Clear both before starting.
                 * --------------------------------------------------------------------- */
                // SUITE is needed as well as GRID_URL: the local-grid branch reads the selected
                // suite's authored thread-count so it can cap it to the agent's CPU count.
                withEnv(["GRID_URL=${params.GRID_URL}", "SUITE=${params.SUITE}"]) {
                sh '''
                    set -e

                    if [ -n "$GRID_URL" ]; then
                        echo "Remote grid: $GRID_URL"
                        STATUS=$(curl -sS --max-time 15 "$GRID_URL/status" || true)
                        if [ -z "$STATUS" ]; then
                            echo "FATAL: no answer from $GRID_URL/status - grid unreachable from agent $(hostname)."
                            exit 1
                        fi
                        echo "$STATUS" | head -c 2000; echo
                        echo "$STATUS" | grep -q '"ready": *true' || {
                            echo "FATAL: grid at $GRID_URL reports ready=false - no node has registered yet."
                            exit 1
                        }
                        # Slots must cover the suite's thread-count or the surplus sessions fail
                        # exactly as they do on a 2-CPU local grid. selenium/standalone-chrome
                        # needs SE_NODE_MAX_SESSIONS *and* SE_NODE_OVERRIDE_MAX_SESSIONS=true;
                        # without the override it derives slots from CPU count all over again.
                        SLOTS=$(echo "$STATUS" | grep -o '"maxSessions": *[0-9]*' | grep -o '[0-9]*' | paste -sd+ - | bc 2>/dev/null || echo 0)
                        echo "Grid session slots: $SLOTS"
                        # Derived from the suite, not hardcoded: this used to compare against a
                        # literal 5, which was thread-count's value at the time and went stale as
                        # soon as either axis changed. Demand is thread-count x
                        # data-provider-thread-count.
                        WANT=$(node ci/cap-parallelism.js "src/test/resources/${SUITE}" --demand)
                        if [ "${SLOTS:-0}" -lt "${WANT:-1}" ]; then
                            echo "WARNING: grid advertises $SLOTS slot(s) but the suite wants $WANT concurrent session(s)."
                            echo "  Set SE_NODE_MAX_SESSIONS=$WANT AND SE_NODE_OVERRIDE_MAX_SESSIONS=true on the node,"
                            echo "  and give the grid host >=$WANT cores - slots without cores just turn session"
                            echo "  failures into step timeouts."
                        fi
                        # ---------------------------------------------------------------
                        # Point the suite AT the grid. Pre-flighting it was never enough:
                        # sessions.yaml declares `local: true`, so Sauron ignored GRID_URL and
                        # started its own 2-slot grid on this agent anyway - the job validated a
                        # grid it then did not use.
                        #
                        # The keys below are confirmed against the Sauron jar, not guessed:
                        #   javap -p com.qapitol.sauron.common.pojos.SessionConfigData
                        #     -> local, protocol, host, port
                        #   javap -p -c com.qapitol.sauron.web.sessions.WebTestSession
                        #     -> createDriverUrl() reads getProtocol()/getHost()/getPort()
                        #        and calls getURLFromData(protocol, host, port)
                        #        (defaults: localhost:4444)
                        #
                        # Rewritten in the workspace, never committed: the repo default stays
                        # `local: true` so a developer clone still runs against its own browser.
                        # ---------------------------------------------------------------
                        PROTO="${GRID_URL%%://*}"
                        REST="${GRID_URL#*://}"; REST="${REST%%/*}"
                        GHOST="${REST%%:*}"
                        GPORT="${REST##*:}"
                        [ "$GPORT" = "$GHOST" ] && GPORT=4444

                        cat > src/test/resources/sessions.yaml <<YAML
# GENERATED BY THE JENKINS JOB - do not commit.
# Remote grid selected by the GRID_URL build parameter ($GRID_URL).
# The committed version of this file declares local: true for developer machines.
- name: "ccp"
  local: false
  protocol: "$PROTO"
  host: "$GHOST"
  port: $GPORT
  platform: "chrome"
  browserRunHeadless: true
YAML
                        echo "--- sessions.yaml rewritten for the remote grid ---"
                        cat src/test/resources/sessions.yaml

                        rm -rf profiles/ sauronFiles/ || true
                        exit 0
                    fi

                    echo "No GRID_URL - falling back to the local grid Sauron starts on this agent."

                    # ---------------------------------------------------------------
                    # Match the suite's parallelism to what a LOCAL grid can serve.
                    #
                    # Selenium sizes a node's session slots from the CPU count. On the 2-CPU agent
                    # that is 2 concurrent sessions, while the suite demands thread-count x
                    # data-provider-thread-count - so any surplus session never starts, and because
                    # Sauron's ScreenshotBddListener then throws on the missing driver, the scenario
                    # reports "Driver not initialized. Is @WebTest/@MobileTest missing on
                    # class/method?" and names neither the browser nor the real cause.
                    #
                    # No figure is quoted here; cap-parallelism.js prints the demand and any cap it
                    # applied. Hardcoding it is what let the old clamp go stale unnoticed.
                    #
                    # Capping is a workaround, not the fix: it trades parallelism for a run that
                    # completes. The fix is GRID_URL pointing at a host with at least as many cores
                    # as the suite demands sessions.
                    #
                    # Only ever LOWERS. Raising past the authored value would break the rule
                    # documented in global_qa.properties - two blocks that run at the same time may
                    # not share a CCP account, because logout is server-side.
                    #
                    # BOTH AXES, and via a script rather than grep/sed. Concurrent sessions are
                    # thread-count x data-provider-thread-count, and the old inline version read
                    # only the first `thread-count="N"` anywhere in the file. That matched
                    # data-provider-thread-count (substring) and matched inside XML comments, so
                    # after testng_bdd.xml documented both axes in prose the clamp was reading the
                    # documentation. Build #244 is what that cost: 1 x 5 = 5 sessions demanded on a
                    # 2-CPU agent, unclamped, and the clamp printed "every thread gets a slot".
                    # ci/cap-parallelism.js reads the <suite> open tag only.
                    # ---------------------------------------------------------------
                    CPUS=$(nproc 2>/dev/null || getconf _NPROCESSORS_ONLN 2>/dev/null || echo 2)
                    SUITE_FILE="src/test/resources/${SUITE}"
                    node ci/cap-parallelism.js "$SUITE_FILE" "$CPUS"

                    # Force headless regardless of what is committed.
                    #
                    # A Jenkins agent has no display, so a headed Chrome does not start and every
                    # scenario fails at session start - reported as "Driver not initialized. Is
                    # @WebTest/@MobileTest missing on class/method?", which names neither the
                    # browser nor the display. Developers legitimately set browserRunHeadless:
                    # false to watch a local run, and that value reaching CI must not be able to
                    # break the build. The GRID_URL branch above already writes its own
                    # sessions.yaml with headless true; this is the same guarantee for the
                    # local-grid branch.
                    if grep -q 'browserRunHeadless: *false' src/test/resources/sessions.yaml; then
                        sed -i 's/browserRunHeadless: *false/browserRunHeadless: true/' \
                            src/test/resources/sessions.yaml
                        echo "sessions.yaml had browserRunHeadless: false - forced to true for this agent (no display)."
                    fi

                    # sessions.yaml must be a YAML LIST. A bare mapping parses as a Map, Sauron
                    # loads no sessions, and Grid.getTestSession() returns null - at which point
                    # AbstractPage's locale supplier NPEs inside getLocator, so every scenario dies
                    # with a bare NullPointerException pointing at a locator. Cheap to check, and
                    # it turns a 20-minute mystery into one line.
                    if ! grep -q '^- name:' src/test/resources/sessions.yaml; then
                        echo "FATAL: src/test/resources/sessions.yaml is not a YAML list."
                        echo "  Expected a '- name: \\"ccp\\"' entry with its keys indented beneath it."
                        echo "  As written, Sauron loads zero sessions and every scenario fails with"
                        echo "  a bare NullPointerException from getLocator."
                        exit 1
                    fi

                    CHROME=""
                    for c in google-chrome google-chrome-stable chromium chromium-browser; do
                        command -v "$c" >/dev/null 2>&1 && CHROME="$c" && break
                    done
                    if [ -z "$CHROME" ]; then
                        echo "FATAL: no Chrome/Chromium on PATH on agent $(hostname)."
                        echo "  sessions.yaml declares a LOCAL grid, so the tests drive a browser"
                        echo "  installed on this agent. Without it every scenario fails at session"
                        echo "  start and the report blames a missing @WebTest annotation instead."
                        echo "  Install Chrome on the agent, or pin this job to one that has it."
                        exit 1
                    fi
                    echo "Browser: $CHROME - $("$CHROME" --version 2>&1)"

                    # Concurrent sessions the suite will ask for vs. what this agent can serve.
                    # Selenium Grid sizes a local node's session slots from the CPU count, so an
                    # agent with fewer cores than the suite's thread-count cannot start every
                    # session and the surplus fails - which looks exactly like "no browser".
                    CPUS=$(nproc 2>/dev/null || echo "?")
                    echo "Agent CPUs: $CPUS (suite thread-count is set in the TestNG suite file)"

                    # Orphaned grids from an earlier build, and the ports they squat on.
                    if pgrep -fa "selenium-server" >/dev/null 2>&1; then
                        echo "WARNING: selenium-server processes predate this build - terminating:"
                        pgrep -fa "selenium-server" || true
                        pkill -f "selenium-server" || true
                        sleep 2
                    fi
                    (ss -lntp 2>/dev/null || netstat -lntp 2>/dev/null || true) \
                        | grep -E ":(4442|4443)\\b" \
                        && echo "WARNING: 4442/4443 still bound - a stale Grid event bus can hijack this run's sessions" \
                        || echo "Grid event bus ports 4442/4443 are free"

                    rm -rf profiles/ sauronFiles/ || true
                '''
                }

                /* catchError, not a bare failure: a red suite must still produce the report and
                 * the email. The build is marked UNSTABLE and the summary carries the detail. */
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    script {
                        // NOTE: -Denv and -DsuiteXmlFile are both verified overrides
                        // (EnvConfig.resolve reads the "env" system property first;
                        // suiteXmlFile is a pom property).
                        //
                        // -Dcucumber.filter.tags is NOT yet verified against this runner -
                        // Sauron passes Cucumber options as TestNG suite parameters, and
                        // which source wins has not been confirmed. Run once with TAGS set
                        // and check the scenario count before depending on it; if it is
                        // ignored, keep tag selection in testng_bdd.xml instead.
                        def tagArg = params.TAGS?.trim() ? "-Dcucumber.filter.tags=\"${params.TAGS.trim()}\"" : ''

                        // Nexus hosts the Sauron artifacts, so the build cannot resolve without
                        // it. Shell default expansion means this works whether or not the agent
                        // exports the variables, and lets them be overridden without a credential
                        // id having to exist.
                        def mvnCmd = """
                            mvn -B -ntp clean test \
                                -s settings.xml \
                                -Denv=${params.ENV} \
                                -DsuiteXmlFile=${params.SUITE} \
                                -Dnexus-username=\${NEXUS_USERNAME:-nexus} \
                                -Dnexus-password=\${NEXUS_PASSWORD:-nexus} \
                                ${tagArg}
                        """

                        if (params.ENV == 'prod') {
                            /* ONE credential, not two.
                             *
                             * The production account is testnivea@yopmail.com and it is COMMITTED in
                             * global_prod.properties - an address is not a secret, and having it in
                             * the file pins the suite to the right account under review. Only the
                             * PASSWORD is secret, and it ships as ${PROD_MONITOR_PASSWORD:} with an
                             * empty default.
                             *
                             * Binding msme-prod-monitor-user as well used to be required here, which
                             * meant TWO credentials had to exist or withCredentials aborted the stage -
                             * producing an UNSTABLE build with zero tests that looks exactly like a
                             * test failure. Only the password is bound now. Override the address for a
                             * one-off run with -DPROD_MONITOR_USER=... instead.
                             *
                             * The variable name matches the ${...} placeholder in
                             * global_prod.properties exactly, so withCredentials alone is enough - no
                             * withEnv, and the secret is never interpolated into a Groovy string.
                             *
                             * The account behind it must be READ-ONLY BY POLICY: used for monitoring,
                             * never for placing orders.
                             *
                             * Locally there is no Jenkins, so run-prod-sanity.sh / .ps1 reads
                             * .env.prod (gitignored) and passes the same name as -D. ConfigUtil
                             * resolves -D first, then the environment, then the empty default - so a
                             * developer's local value can never leak into a CI run.
                             */
                            withCredentials([
                                string(credentialsId: 'msme-prod-monitor-password', variable: 'PROD_MONITOR_PASSWORD')
                            ]) {
                                sh mvnCmd
                            }
                        } else if (params.USE_JENKINS_CREDENTIALS) {
                            // ConfigUtil.getSecretValue resolves ${VAR:default} by reading the OS
                            // environment variable named INSIDE the placeholder - not the property
                            // key. The variable names below must therefore match the placeholder,
                            // which is why they are OMS_DB_PASSWORD and X_API_KEY rather than the
                            // property names OMS_PWD and XApiKey. Binding the property names
                            // instead is silently ignored and the committed default wins.
                            //
                            // Only env-scoped ids belong here. A Jenkins credential holds one
                            // value, so an unscoped id would override the property for BOTH
                            // environments and defeat -Denv entirely.
                            withCredentials([
                                string(credentialsId: "msme-oms-db-password-${params.ENV}", variable: 'OMS_DB_PASSWORD'),
                                string(credentialsId: "msme-xapikey-${params.ENV}",         variable: 'X_API_KEY')
                            ]) {
                                sh mvnCmd
                            }
                        } else {
                            // Defaults come from global_<env>.properties, which are already
                            // correct per environment.
                            sh mvnCmd
                        }
                    }
                }
            }
        }

        stage('Summarise') {
            steps {
                script {
                    sh """
                        node ci/summarize-allure.js \
                            --results allure-results \
                            --out ${env.SUMMARY_BASE} \
                            --env ${params.ENV} \
                            --branch ${params.BRANCH_NAME} \
                            --build ${env.BUILD_NUMBER} \
                            --url ${env.BUILD_URL}
                    """

                    /* Chrome is already installed for the tests, so it doubles as the PDF
                     * renderer - no wkhtmltopdf needed, and unlike wkhtmltopdf it renders
                     * modern CSS (flexbox) correctly. */
                    sh '''
                        CHROME=""
                        for c in google-chrome google-chrome-stable chromium chromium-browser; do
                            command -v "$c" >/dev/null 2>&1 && CHROME="$c" && break
                        done
                        if [ -z "$CHROME" ]; then
                            echo "WARNING: no Chrome/Chromium on PATH - skipping PDF, HTML summary still produced"
                            exit 0
                        fi
                        "$CHROME" --headless=new --disable-gpu --no-sandbox \
                            --print-to-pdf-no-header \
                            --print-to-pdf="$PWD/target/run-summary.pdf" \
                            "file://$PWD/target/run-summary.html"
                        ls -lh target/run-summary.pdf
                    '''

                    /* ---------------------------------------------------------------------
                     * Mail body.
                     *
                     * run-summary.html is NOT reused for this. It is laid out for Chrome's PDF
                     * renderer, and Outlook renders HTML through Word - which ignores flexbox
                     * and grid and mangles the result. ci/email-body.js emits a monospace <pre>
                     * block instead, which survives Outlook, Gmail and a phone unchanged.
                     *
                     * The trend line needs the PREVIOUS run's pass rate, and that cannot be
                     * derived from allure-results. It is carried in currentBuild.description,
                     * which needs no plugin and survives workspace cleanup.
                     * --------------------------------------------------------------------- */
                    /* One node call for every number the build list needs, pipe-joined - the same
                     * technique the mail stage below uses, rather than six process spawns to
                     * assemble one line of text.
                     *
                     * BROKEN IS REPORTED SEPARATELY ON PURPOSE. Allure's `failed` means an
                     * assertion returned the wrong answer; `broken` means the scenario threw. This
                     * suite produces far more broken than failed - build #243 was 36 broken - so a
                     * description showing only "passed / failed" reads as a near-clean run when it
                     * was not. summarize-allure.js takes the same view: its verdict is
                     * `failed + broken === 0`, not `failed === 0`.
                     *
                     * `total` is the DEDUPLICATED scenario count: a scenario that was retried is
                     * counted once, at its best outcome, and skipped scenarios are included. So
                     * total is "scenarios in the run", and passRate is passed/total, which means
                     * skips count against it. */
                    def totalsLine = ''
                    def passRate = ''
                    if (fileExists("${env.SUMMARY_BASE}.json")) {
                        def raw = sh(
                            returnStdout: true,
                            script: """node -e "const t=require('./${env.SUMMARY_BASE}.json').totals;console.log([t.total,t.passed,t.failed,t.broken,t.skipped,t.passRate].join('|'))" """
                        ).trim()
                        def n = raw.split(/\|/)
                        if (n.length == 6) {
                            totalsLine = "${n[0]} total, ${n[1]} passed, ${n[2]} failed, ${n[3]} broken, ${n[4]} skipped"
                            passRate = n[5]
                        } else {
                            /* Shape drift in our own summariser. Fall back to the pass rate alone so
                             * the same-env trend below keeps working - losing it would silently
                             * break the "improvement from last run" line in the mail. */
                            echo "run-summary.json totals had ${n.length} fields, expected 6 - falling back to passRate only"
                            passRate = sh(
                                returnStdout: true,
                                script: """node -e "console.log(require('./${env.SUMMARY_BASE}.json').totals.passRate)" """
                            ).trim()
                        }
                    }

                    /* The most recent build ON THIS ENV, which is not always the preceding build.
                     *
                     * This used to read currentBuild.previousBuild only. In a job that runs qa,
                     * pre-prod and prod, that makes "improvement from last run" a comparison
                     * between two different suites on two different environments: a prod sanity
                     * build following a qa regression reported a pass-rate swing of tens of points
                     * that was nothing but the change of suite.
                     *
                     * So walk back to the newest build carrying env=<this env>, and hand its env to
                     * the mail alongside the rate so the two cannot be separated later. The bound
                     * keeps this cheap on a job with thousands of builds; if no same-env build is
                     * found within it, the trend is simply absent, which is honest.
                     *
                     * String ops rather than a regex Matcher, which is not serializable across
                     * pipeline steps. */
                    def previousPassRate = ''
                    def previousRateEnv = ''
                    def probe = currentBuild.previousBuild
                    int hops = 0
                    while (probe != null && hops < 40) {
                        String d = probe.description ?: ''
                        String probeEnv = ''
                        if (d.contains('env=')) {
                            probeEnv = d.substring(d.indexOf('env=') + 'env='.length())
                                .split(/[^A-Za-z0-9_-]/)[0]
                        }
                        if (probeEnv == params.ENV && d.contains('passRate=')) {
                            previousPassRate = d
                                .substring(d.indexOf('passRate=') + 'passRate='.length())
                                .split(/[^0-9.]/)[0]
                            previousRateEnv = probeEnv
                            echo "Trend baseline: build #${probe.number} (env=${probeEnv}, passRate=${previousPassRate})"
                            break
                        }
                        probe = probe.previousBuild
                        hops++
                    }
                    if (!previousPassRate) {
                        echo "No previous ${params.ENV} build found within ${hops} builds - the mail will say the trend is not available"
                    }

                    /* --jira-url is deliberately not passed; the body carries no ticket link.
                     *
                     * --previous-summary names the ENV-SCOPED file preserved at the top of the Test
                     * stage, so a prod build can only ever be compared with the last prod run.
                     * --previous-env travels with --previous-pass-rate: the rate is a bare number,
                     * and email-body.js drops it if the env does not match rather than trusting the
                     * caller. Both guards are in place because the mail is the artefact people act
                     * on, and a cross-env trend reads as a regression that never happened. */
                    sh """
                        node ci/email-body.js \
                            --summary ${env.SUMMARY_BASE}.json \
                            --previous-summary .previous-run-summary-${params.ENV}.json \
                            --job "${env.JOB_NAME}" \
                            --build ${env.BUILD_NUMBER} \
                            --env ${params.ENV} \
                            --jenkins-url ${env.BUILD_URL} \
                            ${previousPassRate ? "--previous-pass-rate ${previousPassRate}" : ''} \
                            ${previousRateEnv ? "--previous-env ${previousRateEnv}" : ''} \
                            > target/email-body.html
                    """

                    /* Appended, not assigned: Checkout already put env= and the tag filter here and
                     * that is what makes the build list readable.
                     *
                     * passRate= stays LAST. The walker above reads it with
                     * indexOf('passRate=') + substring, and although it then splits on the first
                     * non-numeric character, keeping it last means the counts can never be
                     * mistaken for the rate. Do not insert anything after it, and do not let any
                     * added text contain the literal "env=" - the same walker takes the first
                     * "env=" it finds as the build's environment. */
                    if (passRate) {
                        currentBuild.description = "${currentBuild.description ?: ''}"
                                + (totalsLine ? " | ${totalsLine}" : '')
                                + " | passRate=${passRate}"
                    }
                }
            }
        }
    }

    post {
        always {
            // Rich interactive report, served at ${BUILD_URL}allure
            allure([
                results: [[path: 'allure-results']],
                reportBuildPolicy: 'ALWAYS'
            ])

            // The one-pager, browsable from the build page
            publishHTML([
                reportDir: 'target',
                reportFiles: 'run-summary.html',
                reportName: 'Run Summary',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: true
            ])

            archiveArtifacts(
                artifacts: 'target/run-summary.html, target/run-summary.pdf, target/run-summary.json, target/email-body.html, target/surefire-reports/**',
                allowEmptyArchive: true,
                fingerprint: false
            )

            script {
                def subject = "[${params.ENV.toUpperCase()}] UI automation #${env.BUILD_NUMBER} - ${currentBuild.currentResult}"
                def body = "<p>Summary could not be generated - see the console log.</p>"

                if (fileExists("${env.SUMMARY_BASE}.json")) {
                    // readJSON needs pipeline-utility-steps; parse via the script's own output
                    // instead so this pipeline depends only on the three named plugins.
                    def line = sh(
                        returnStdout: true,
                        script: """node -e "const d=require('./${env.SUMMARY_BASE}.json');console.log([d.verdict,d.totals.total,d.totals.passed,d.totals.failed,d.totals.broken,d.totals.skipped,d.totals.passRate].join('|'))" """
                    ).trim()
                    def p = line.split(/\|/)
                    subject = "[${params.ENV.toUpperCase()}] UI automation #${env.BUILD_NUMBER} - ${p[0]} " +
                              "(${p[2]}/${p[1]} passed, ${p[3]} failed, ${p[4]} broken, ${p[6]}%)"
                }

                // email-body.html first: it is the Outlook-safe monospace layout with the
                // pass/fail counts, duration and the trend against the previous run.
                // run-summary.html is the fallback - it is built for the PDF and Word mangles
                // its layout, but a mangled summary still beats no summary if the Summarise
                // stage was cut short.
                if (fileExists('target/email-body.html')) {
                    body = readFile(file: 'target/email-body.html', encoding: 'UTF-8')
                } else if (fileExists("${env.SUMMARY_BASE}.html")) {
                    body = readFile(file: "${env.SUMMARY_BASE}.html", encoding: 'UTF-8')
                }

                emailext(
                    to: params.RECIPIENTS,
                    subject: subject,
                    body: body,
                    mimeType: 'text/html',
                    attachmentsPattern: 'target/run-summary.pdf, target/run-summary.html',
                    attachLog: false,
                    compressLog: false
                )
            }

            /* ---------------------------------------------------------------
             * MS Teams - intentionally not wired up yet.
             *
             * When you add it, use a Power Automate "Workflows" incoming-webhook
             * trigger on the channel, NOT the legacy Office 365 Connector: Microsoft
             * has retired connector webhooks, so the old Jenkins Office 365 Connector
             * plugin route is a dead end.
             *
             * The Workflows trigger accepts an Adaptive Card, and target/run-summary.json
             * already holds every number the card needs, so this becomes roughly:
             *
             *   withCredentials([string(credentialsId: 'teams-workflow-url', variable: 'TEAMS_URL')]) {
             *       sh 'node ci/teams-card.js target/run-summary.json | ' +
             *          'curl -s -X POST -H "Content-Type: application/json" -d @- "$TEAMS_URL"'
             *   }
             *
             * Nothing above needs to change to accommodate it.
             * --------------------------------------------------------------- */
        }

        cleanup {
            // Chrome profiles and crash dumps, which .gitignore already excludes from the repo
            sh 'rm -rf profiles/ sauronFiles/ || true'
        }
    }
}
