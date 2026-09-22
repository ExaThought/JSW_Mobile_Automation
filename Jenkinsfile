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
            choices: ['testng_bdd.xml', 'testng_all.xml', 'testng_serial.xml',
                      'testng_mobilebdd.xml', 'testng_prod_sanity.xml'],
            description: 'TestNG suite file. Maps to the pom\'s ${suiteXmlFile} property. ' +
                         'testng_all.xml is a MASTER suite that runs testng_bdd.xml and then ' +
                         'testng_serial.xml, each keeping its own thread counts - that is why the ' +
                         'split exists, since data-provider-thread-count is a per-suite attribute ' +
                         'and cannot differ between blocks of one suite. testng_serial.xml is the ' +
                         'cart and SF order features at one scenario at a time. ' +
                         'testng_prod_sanity.xml is the read-only suite selecting ' +
                         '"@productionSanity and @P0", and is required when ENV=prod; it is also ' +
                         'safe to run against qa or preprod.'
        )
        string(
            name: 'GRID_URL',
            defaultValue: '',
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
        ALLURE_TOOL  = 'allure'
    }

    stages {

        stage('Checkout') {
            steps {
                script {
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
                    currentBuild.description = "env=${params.ENV} | ${params.TAGS ?: 'suite defaults'}"
                }
            }
        }

        stage('Test') {
            environment {
                NEXUS = credentials('nexus-member')
            }
            steps {


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
                        echo 'PRODUCTION RUN - read-only suite selecting "@productionSanity and @P0" against www.jswonemsme.com'
                    }
                }

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
                    # ci/cap-parallelism.js reads the <suite> open tag only, and follows
                    # <suite-files> when the selected suite is a master such as testng_all.xml -
                    # that file declares no counts of its own, so reading its tag alone would
                    # report a demand of 1 and cap nothing while its children asked for 4.
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
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    script {
                        def tagArg = params.TAGS?.trim() ? "-Dcucumber.filter.tags=\"${params.TAGS.trim()}\"" : ''

                        def mvnCmd = """
                            mvn -B -ntp clean test \
                                -s settings.xml \
                                -Denv=${params.ENV} \
                                -DsuiteXmlFile=${params.SUITE} \
                                -Dnexus-username="\${NEXUS_USR}" \
                                -Dnexus-password="\${NEXUS_PSW}" \
                                ${tagArg}
                        """

                        if (params.ENV == 'prod') {
                            withCredentials([
                                string(credentialsId: 'msme-prod-monitor-password', variable: 'PROD_MONITOR_PASSWORD')
                            ]) {
                                sh mvnCmd
                            }
                        } else if (params.USE_JENKINS_CREDENTIALS) {
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
                            echo "run-summary.json totals had ${n.length} fields, expected 6 - falling back to passRate only"
                            passRate = sh(
                                returnStdout: true,
                                script: """node -e "console.log(require('./${env.SUMMARY_BASE}.json').totals.passRate)" """
                            ).trim()
                        }
                    }

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
            script {
                def relabelled = false
                try {
                    def allureBin = ''
                    try {
                        allureBin = tool(name: env.ALLURE_TOOL,
                                         type: 'ru.yandex.qatools.allure.jenkins.tools.AllureCommandlineInstallation') + '/bin'
                    } catch (ignored) {
                        echo "No Allure Commandline tool named '${env.ALLURE_TOOL}' " +
                             '(Manage Jenkins -> Tools); looking for allure on PATH instead.'
                    }

                    withEnv(allureBin ? ["PATH+ALLURE=${allureBin}"] : []) {
                        sh '''
                            set -e
                            command -v allure >/dev/null 2>&1 || {
                                echo "allure CLI not found - set ALLURE_TOOL to the name under Manage Jenkins -> Tools"
                                exit 1
                            }
                            if [ -d allure-report/history ]; then
                                cp -r allure-report/history allure-results/
                                echo "Carried the previous build's Allure history forward for the Trend panel"
                            fi
                            allure generate allure-results --clean -o allure-report
                        '''
                    }
                    sh 'node ci/relabel-allure.js allure-report'
                    publishHTML([
                        reportDir: 'allure-report',
                        reportFiles: 'index.html',
                        reportName: 'Allure Report',
                        keepAll: true,
                        alwaysLinkToLastBuild: true,
                        allowMissing: false
                    ])
                    relabelled = true
                } catch (err) {
                    echo "Could not publish the relabelled Allure report (${err.message}); " +
                         'falling back to the Allure plugin, which will say "test cases".'
                }

                if (!relabelled) {
                    allure([
                        results: [[path: 'allure-results']],
                        reportBuildPolicy: 'ALWAYS'
                    ])
                }
            }

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

        }

        cleanup {
            // Chrome profiles and crash dumps, which .gitignore already excludes from the repo
            sh 'rm -rf profiles/ sauronFiles/ || true'
        }
    }
}
