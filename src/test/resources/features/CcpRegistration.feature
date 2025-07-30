@registration @regression
Feature: Test Ccp registration

  @sanity
  Scenario Outline:Check positive registration and validation
    When user open the website and open registration
    And user enter "<userName>" "<phoneNumber>" "<gst>" "<email>"
    And user set the "<password>" "<gst>"

    Examples:
    |userName|phoneNumber|email|gst|password|
    |Jayadev Gowda|8296229209|jayadev@gmail.com|29ABLFS5016N1ZK|Jsw@2025|


    @sanity
  Scenario:Check negative registration and validation
    When user open the website and open registration
    And user enter phoneNumber shorter than "76857656" and longer than "86787654567876" then 10 digit
    And user enter invalid email "jayadevgmail.com"
    And user enter invalid UserName "&%&%^JayadevGowda123"
    And user do not give data for mandatory field "JayadevGowda" "8297229856" "jayadev.yl@jsw.in" "29ABLFS5016N1ZK"