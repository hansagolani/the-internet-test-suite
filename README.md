# The Internet Test Suite 

A Selenium + JUnit 5 UI test automation suite against a small public demo site - 
https://the-internet.herokuapp.com. 

The project demonstrates my ability to: 
- Automate end-to-end UI scenarios using Selenium WebDriver.
- Organize and execute tests using JUnit 5.
- Apply maintainable test design and reusable browser setup.
- Validate application behavior through meaningful assertions.
- Handle dynamic elements, explicit waits, and browser interactions.

## Status
Complete: all 14 pages covered, 72 tests passing (headed and headless). 

## Stack
- Java 17
- Selenium WebDriver 4.25
- JUnit 5
- Maven
- Selenium Manager (built into Selenium, fetches the Chrome driver)

## Run
```
mvn clean test                     # headed
mvn -Dheadless=true clean test     # headless
```
Requires Java 17+ and Chrome. Selenium Manager fetches the driver.
In case of test failure, a screenshot is saved to screenshots/.

## Structure
```
   src/test/java/com/portfolio/qa/
   ├── base/      BaseTest (browser setup and teardown, headless toggle,
   │              download folder), ScreenshotOnFailure
   ├── config/    TestConfig (base URL, timeouts, paths)
   ├── pages/     One page object per page: locators and actions
   ├── tests/     One test class per page: calls page methods, holds the assertions
   └── util/      HttpStatus, HTTP helper for status-code checks
   docs/          Test plan, sample defect report
```

## Docs
- [Test plan](docs/test-plan.md)
- [Sample defect report](docs/defect-report.md)

## Coverage
All 14 pages are covered: Form Authentication, Checkboxes, Dropdown, Dynamic
Loading, JavaScript Alerts, File Upload, Add/Remove Elements, Hovers,
Multiple Windows, Broken Images, Frames, Status Codes, Sortable Data Tables
and Secure File Download. Scenarios and expected results for each page are
in the [test plan](docs/test-plan.md#6-test-cases).