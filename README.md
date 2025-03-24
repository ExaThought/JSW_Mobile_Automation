# UTAF-Automation

Prerequestites
1. Get credentials to access JSW nexus
2. Create directory name `download_dir` in project directory.

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
