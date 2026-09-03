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
                         'testng_prod_sanity.xml is the read-only @productionSanity suite and is ' +
                         'required when ENV=prod; it is also safe to run against qa or preprod.'
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
                    currentBuild.description = params.TAGS ?: 'suite defaults'
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
                sh '''
                    if [ -f target/run-summary.json ]; then
                        cp target/run-summary.json .previous-run-summary.json
                        echo "Preserved the previous run summary for the new-vs-recurring comparison"
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
                 *   2. the @productionSanity tag filter in testng_prod_sanity.xml
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
                                  "scenarios by the @productionSanity tag; an ad-hoc filter here " +
                                  "could select order-creation scenarios.")
                        }
                        echo "PRODUCTION RUN - read-only @productionSanity suite against www.jswonemsme.com"
                    }
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
                    def passRate = ''
                    if (fileExists("${env.SUMMARY_BASE}.json")) {
                        passRate = sh(
                            returnStdout: true,
                            script: """node -e "console.log(require('./${env.SUMMARY_BASE}.json').totals.passRate)" """
                        ).trim()
                    }

                    // Only the immediately preceding build - "improvement from last run" means
                    // exactly that. String ops rather than a regex Matcher, which is not
                    // serializable across pipeline steps.
                    def previousPassRate = ''
                    def previousDescription = currentBuild.previousBuild?.description ?: ''
                    if (previousDescription.contains('passRate=')) {
                        previousPassRate = previousDescription
                            .substring(previousDescription.indexOf('passRate=') + 'passRate='.length())
                            .split(/[^0-9.]/)[0]
                    }

                    // --jira-url is deliberately not passed; the body carries no ticket link.
                    // --previous-summary is the file preserved at the top of the Test stage.
                    sh """
                        node ci/email-body.js \
                            --summary ${env.SUMMARY_BASE}.json \
                            --previous-summary .previous-run-summary.json \
                            --job "${env.JOB_NAME}" \
                            --build ${env.BUILD_NUMBER} \
                            --env ${params.ENV} \
                            --jenkins-url ${env.BUILD_URL} \
                            ${previousPassRate ? "--previous-pass-rate ${previousPassRate}" : ''} \
                            > target/email-body.html
                    """

                    // Appended, not assigned: Checkout already put the tag filter here and that
                    // is what makes the build list readable.
                    if (passRate) {
                        currentBuild.description = "${currentBuild.description ?: ''} | passRate=${passRate}"
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
