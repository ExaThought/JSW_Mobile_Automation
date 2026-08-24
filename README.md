# UTAF-Automation

Prerequestites
1. Get credentials to access JSW nexus
2. Create directory name `download_dir` in project directory.

## Required secrets

**No credential belongs in this repository.** Every secret in `src/test/resources/global_*.properties`
ships as `${ENV_VAR:}` with an empty default and is injected at run time. `ci/check-secrets.js`
runs on every PR and fails the build if a real value reappears in a tracked file, so this is
enforced rather than merely requested.

Each can be supplied **either** way — a JVM system property wins over an environment variable,
which wins over the (empty) default:

```bash
# environment variable - what CI should use
export QA_CART_PASSWORD='...'
mvn test -DsuiteXmlFile=src/test/resources/testng_bdd.xml

# or on the command line - handy locally, and for a Jenkins job you cannot yet edit
mvn test -DsuiteXmlFile=src/test/resources/testng_bdd.xml -DQA_CART_PASSWORD='...'
```

A missing one fails immediately through `ConfigUtil.resolveOrThrow` / `UserConfig.requireGroupValue`,
and the message names the variable to set. Without that guard an unset password resolves to the
empty string, the suite posts a blank credential, and the run reports HTTP 401 — which looks like
a bad account rather than absent configuration.

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

### Test-account passwords

One pair per `test.group`, because groups run in parallel and must not share a login. The
`<GROUP>.user.email` addresses are committed (they are not secrets); the passwords are not.

`QA_*` for `-Denv=qa`, `PREPROD_*` for `-Denv=preprod`:

```
{QA,PREPROD}_LOGIN_PASSWORD        {QA,PREPROD}_HOMEPAGE_PASSWORD
{QA,PREPROD}_CART_PASSWORD         {QA,PREPROD}_PDPPLP_PASSWORD
{QA,PREPROD}_GUEST_PASSWORD        {QA,PREPROD}_PROFILE_PASSWORD
{QA,PREPROD}_TOKENCHECK_PASSWORD   {QA,PREPROD}_ORDERS_PASSWORD
{QA,PREPROD}_SECONDARY_PASSWORD    {QA,PREPROD}_MSITE_PASSWORD
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
| No committed secrets | `ci/check-secrets.js` | JWTs, PEM keys, and credential-named keys holding a real value. |

The first five exist because this framework resolves locator keys, payload keys and step text at
**runtime** — a rename compiles cleanly and fails mid-suite with a message naming neither the key
nor the file. Run them locally before pushing:

```bash
node ci/check-locator-keys.js && node ci/check-payload-keys.js && node ci/lint-features.js && node ci/check-secrets.js
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