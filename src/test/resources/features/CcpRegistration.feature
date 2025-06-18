@registration @regression
Feature: Test Ccp registration

  @sanity
  Scenario Outline:Check registration positive and validation
    When user open the website and open registration
    And user enter "<userName>" "<phoneNumber>" "<gst>" "<email>"
    And user set the "<password>" "<gst>"

    Examples:
    |userName|phoneNumber|email|gst|password|
    |Jayadev Gowda|8296229209|jayadev@gmail.com|29ABLFS5016N1ZK|Jsw@2025|
