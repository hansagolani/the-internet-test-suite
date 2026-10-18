# The Internet Test Suite 

A Selenium + JUnit 5 UI test automation suite against a small public demo site - 
https://the-internet.herokuapp.com. 

The project demonstrates my ability to, 
- Automate end-to-end UI scenarios using Selenium WebDriver.
- Organize and execute tests using JUnit 5.
- Apply maintainable test design and reusable browser setup.
- Validate application behavior through meaningful assertions.
- Handle dynamic elements, explicit waits, and browser interactions.

## Status
In progress. 

## Stack
- Java 17
- Selenium WebDriver 4.25
- JUnit 5
- Maven
- Selenium Manager (built into Selenium, fetches the Chrome driver)

## Run
```
mvn test                     # headed
mvn test -Dheadless=true     # headless
```
Requires Java 17+ and Chrome. Selenium Manager fetches the driver.

## Structure
TODO

## Coverage

14 pages under test (planned)

1. Form Authentication 
2. Checkboxes
3. Dropdown
4. Dynamic Loading
5. JavaScript Alerts
6. File Upload
7. Add/Remove Elements
8. Hovers
9. Multiple Windows
10. Broken Images
11. Frames
12. Status Codes
13. Sortable Data Tables
14. Secure File Download