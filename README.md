# UTAF-Automation

Prerequestites
1. Get credentials to access JSW nexus
2. Create directory name `download_dir` in project directory.

## Configuration and secrets

Every value lives in `src/test/resources/global_<env>.properties` as `${ENV_VAR:default}`, and
`ConfigUtil.getSecretValue` resolves the first non-empty of:

1. `-DENV_VAR=value` on the Maven command line
2. `ENV_VAR` in the environment — GitHub Actions secrets, Jenkins credentials bindings
3. the committed default

So a plain `mvn test` works with no setup, and any value can be overridden per-run:

```bash
mvn test -DsuiteXmlFile=src/test/resources/testng_bdd.xml -DQA_PROFILE_PASSWORD='...'
```

A key with no default and nothing supplied fails through `ConfigUtil.resolveOrThrow` /
`UserConfig.requireGroupValue`, naming both the property and the variable to set.

> ### ⚠️ Credentials are currently committed
>
> The defaults in `global_common.properties`, `global_qa.properties` and
> `global_preprod.properties` include real JWTs, API keys, a database password and the test-account
> passwords — `ci/check-secrets.js` counts 36. **A PR review flagged this as a blocking security
> issue.** They are still here because they are what makes the suite run: nothing else supplies
> them yet, and blanking them broke every login-dependent scenario.
>
> To close it properly:
>
> 1. give the Jenkins job credentials bindings for the variables below (job configuration, not
>    this repo)
> 2. blank the defaults to `${ENV_VAR:}`
> 3. re-enable the `No committed secrets` step in `.github/workflows/pr-checks.yaml`
>
> Anything ever committed here stays reachable in git history, in every clone and every fork —
> deleting it from the working tree is not remediation. **Rotate these credentials regardless of
> when step 2 happens.** The highest-risk one is `appTokenUrl` in `global_common.properties`: a
> signed JWT for the **production** host `jswoneconstruct.in`, which nothing in the codebase
> reads.

The variables below override the committed defaults. They are what Jenkins should bind.

### Build

| Variable | Needed for | Notes |
|---|---|---|
| `NEXUS_USERNAME` / `NEXUS_PASSWORD` | Resolving the Sauron artifacts | Private Nexus at `master.jswone.in`. Without these the PR "Compile" step warns and passes; the static checks still gate. |

### Service credentials

| Variable | Needed for | Notes |
|---|---|---|
| `X_API_KEY` | Central-cart client (`LoginApiImpl`) | |
| `SALES_OPP_API_KEY` | Salesforce → CT sales-opp sync | Replaces `access_token` on every call through `salesOppUrl`, so set it only when `SALES_OPP_BASE_URL` is a host of its own. |
| `CCP_EXPIRED_TOKEN` | `@customerProfileApi` | A deliberately **expired** CCP JWT — the scenario asserts a 401. |
| `OMS_USER_EMAIL` / `OMS_USER_PASSWORD` | OMS / ERP | **Preferred.** `Token.getOmsToken` logs in and caches a fresh token, so nothing long-lived is stored. |
| `OMS_TOKEN` | OMS / ERP | Alternative to the pair above: a static bearer token. Leave unset if `oms.user.*` are supplied. |
| `OMS_LOGIN` | ERP hand-off URL | Optional. Built from `omsBaseUrl + omsErpRedirectPath + getOmsToken()` when unset, which avoids writing the token down. |
| `OMS_DB_PASSWORD` | DB-backed assertions | With `OMS_DB_URL`, `OMS_DB_SCHEMA`, `OMS_DB_USER`. |
| `OMS_INTERNAL_API_KEY` | `@ledger` due payments (`customer-due-payments-list`) | **No committed default** - must be supplied. Jenkins id `msme-oms-internal-api-key` (one for both qa and preprod). |
| `LEDGER_SERVICE_CLIENT_ID` / `LEDGER_SERVICE_AUTHORIZATION` | `@ledger` usable ledger balance (`fetch-ledger-transaction`) | **No committed default** - must be supplied. Jenkins ids `msme-ledger-service-client-id` / `msme-ledger-service-authorization` (one each for both qa and preprod). |

A local `@ledger` run supplies those three - as environment variables in the IDE run
configuration, or on the command line (single-quote the Authorization value, it contains `$`):

```bash
mvn test -Denv=qa -DsuiteXmlFile=src/test/resources/testng_bdd.xml \
  -DOMS_INTERNAL_API_KEY='...' -DLEDGER_SERVICE_CLIENT_ID='...' -DLEDGER_SERVICE_AUTHORIZATION='...'
```

### Test-account passwords

One pair per `test.group`, because groups run in parallel and must not share a login. Both the
address and the password currently carry a committed default — see the warning above.

`QA_*` for `-Denv=qa`, `PREPROD_*` for `-Denv=preprod`:

```
{QA,PREPROD}_LOGIN_PASSWORD        {QA,PREPROD}_HOMEPAGE_PASSWORD
{QA,PREPROD}_CART_PASSWORD         {QA,PREPROD}_PDPPLP_PASSWORD
{QA,PREPROD}_GUEST_PASSWORD        {QA,PREPROD}_PROFILE_PASSWORD
{QA,PREPROD}_TOKENCHECK_PASSWORD   {QA,PREPROD}_ORDERS_PASSWORD
{QA,PREPROD}_SECONDARY_PASSWORD    {QA,PREPROD}_MSITE_PASSWORD
{QA,PREPROD}_LEDGER_PASSWORD
```

Plus `LOGIN_ACCOUNT_PASSWORD` for the `account.login.*` alias used by the login and
forgot-password suites. `LOGIN_ACCOUNT_WRONG_PASSWORD` is **not** a secret — it is deliberately
invalid, proving a bad password is rejected, and keeps a committed placeholder default.

### If a secret was ever committed

Deleting it from the working tree does **not** remove it from git history — it stays reachable in
every earlier commit and in any clone or fork. Rotating the credential is the only real
remediation. Treat a committed secret as compromised.

## PR checks

`.github/workflows/pr-checks.yaml` gates every PR. All are Node stdlib or pip, and the job
finishes in about a minute:

| Check | Script | Catches |
|---|---|---|
| Compile | `mvn -s settings.xml compile` | Java breakage. Warns and passes without `NEXUS_*`. |
| Locator keys resolve | `ci/check-locator-keys.js` | A step class reading a locator key that no YAML defines. |
| Payload keys resolve | `ci/check-payload-keys.js` | An `orderType` → data-yaml → `api/request/*.json` chain with a broken link, per environment. |
| YAML valid | `yamllint -c .yamllint.yaml` | Malformed locator/data/session YAML. |
| Gherkin lint | `ci/lint-features.js` | Feature text with no matching step definition. |

These exist because this framework resolves locator keys, payload keys and step text at
**runtime** — a rename compiles cleanly and fails mid-suite with a message naming neither the key
nor the file. Run them locally before pushing:

```bash
node ci/check-locator-keys.js && node ci/check-payload-keys.js && node ci/lint-features.js
```

`ci/check-secrets.js` is a sixth check that is written but **not wired into the workflow** — it
reports the 36 credentials currently committed, so enabling it would fail every PR. Turn it on in
the same change that blanks the defaults. Run it by hand any time:

```bash
node ci/check-secrets.js
```

## How to execute tests

Ways to execute
1. Maven with testng xml file 
`mvn test -DsuiteXmlFile=<testng xml file>`
2. Maven execute via JSW Nexus credentials
`mvn test -DsuiteXmlFile=<testng xml file> -s settings.xml -Dnexus-username=<username> -Dnexus-password=<password>`
3. To run locally right-click on the testng xml file. To run a specific test tag it and specify the `cucumber.filter.tags` testng parameter value to the tag.
Ex: If a test is tagged as `@smoke` specify the value as `@smoke` for the testng parameter. To ignore a test use the tag `@ignore` as part of the testng parameter. Please refer cucumber docs or use LLM-chat for more complex filtering.

## How to script
- Add scenarios in feature files
- Specify the step defs i.e. gherkin steps in package named `src/main/java/com.qapitol.jsw.webbdd.stepdefinition`

### Data
1. Specify the global property values in `global_qa.properties` file. To access these values in code use `Config.getConfigProperty` or `Config.getIntConfigProperty` etc
2. You can specify the test data for the `qa` env in the `src/test/resources/data/qa/` folder. If you want to specify the data for all the testcases put them in `default-data.yaml` file. If you need them specific to a test case then provide it via the test id in the yaml file under the `testdata` folder.

Ex: The values in the yaml file under the `testdata` folder could be as follows,

- testCaseId: "id-001"
  username: "aditya"
- testCaseId: "id-002"
  username: "venkat"

If you want to specify the payload file i.e the json file from the `src/test/resources/api/request` folder then specify as below

3. To store or retrieve generated data from the application use ContextData.add

Store data:
String
```java
ContextData.addData("gstin","Hello world");
```
Store Complex POJO:
```java
setPojoToContextData("shipmentInvoice", shipmentInvoiceResp);
```
Retrieve data:
```java
ContextData.getDataFromKey(key);
```
Retrieve Complex POJO:
```java
setPojoToContextData("updateShipmentResp", updateShipmentResp);
```

Note: Above functions are thread safe and can be used when running tests in parallel.

```yaml
CREATE_ORDER_PAYLOAD: "create_order_payload"
CONFIRM_ORDER_PAYLOAD: "confirm_order"
CREATE_DO_PAYLOAD: "create_do"
CREATE_SHIPMENT: "create_shipment"
UPDATE_SHIPMENT: "update_shipment"
```
The value represents the file name.

### Locaters

Specify the locators specific to the page where it would be needed. If you need the locator made available to all the pages then specify them in the `basesteps.yaml` file.

Ex: `windowKeys` & `windowValues` are keys which can be accessed from code via function `getLocater("windowKeys")`

```yaml
elements:
  windowKeys:
    en_US:
      web: xpath=.//div[@class='record-label-name']
  windowValues:
    en_US:
      web: xpath=.//div[contains(@class,'record-summary-data')]
```

To reuse locator specify the wildcard and add one extra inverted quote if present all over the locator.

Here `'{0}'` represents the wildcard character. To use this in code for example you can call `waitAndClickOn("searchCheckbox", "Credit limit reference id")` the wild card character will be replaced by the value `Credit limit reference id`.
```yaml
searchCheckbox:
    en_US:
        web: //div[@class=''zc-cb-checkbox''][label[contains(text(),''{0}'')]]//input
```

### To create new Step defs

After creating a new class extend it with `BaseSteps` class

### API

1. To create a new API request POJO place a sample in a JSON file naming it using the eventual POJO name in `src/test/resources/api_responses` folder. The generated POJO classes would be available in the `src/target/generated-sources` folder.

Command to generate is `mvn clean compile`.

Note: If it fails in creating try to simplify it by removing the key and their value/values and put them in a seperate file where the name of the file is the same as the key name.

2. Once the POJOS are generated specify [OrderApi.java](src/main/java/com/qapitol/jsw/apitest/apis/OrderApi.java)the API path and other relevant things like content type etc in an interface class.

Ex: Here it tells that all the API's requests are of JSON. The `getJOMSOrder` provides a response in the `x-ndjson` format.
The API `createOrderViaSLOAPI` takes the `CreateOrder` objext as JSON body and provides response as `CreateOrderResp` POJO. 
If path param needs to be provided then add the wildcard as shown in the path and specify the annotation `@PathParam("orderNumber")` whose value is the wildcard entry.

```java
@Consumes(APPLICATION_JSON)
public interface OrderApi {
    @POST
    @Path("/data-sync/import/sales-opp")
    @Produces(APPLICATION_JSON)
    CreateOrderResp createOrderViaSLOAPI(CreateOrder createOrder);

    @GET
    @Path("oms/order/v2/number/{orderNumber}")
    @Produces("application/x-ndjson")
    GetOMSOrderResp getJOMSOrder(@PathParam("orderNumber") String orderNumber);
}
```

3. The credentials i.e. the tokens can be specified in the `LoginApiImpl` class. 

Other functionalities:
- If you want to read raw API response set the return value of the API in the interface class to the `Response` class from JAX-RS and use the function `getResponseAsString(response)` to read the response body as string.

### Migration methods

1. Use `migrateToTestNgAssertEquals` to move away from Junit assertEquals to TestNg.