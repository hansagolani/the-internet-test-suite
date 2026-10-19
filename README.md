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
In case of test failure, a screenshot is saved to screenshots/.

## Structure
TODO

## Coverage

14 pages planned. 

| # | Page | Status  | Tests                                                                                             |
|---|------|---------|---------------------------------------------------------------------------------------------------|
| 1 | Form Authentication | Done    | valid login, invalid password, invalid username, logout                                           |
| 2 | Checkboxes | Done    | default state, toggle each box, boxes independent, double toggle                                  |
| 3 | Dropdown | Done    | default placeholder, select by text, select by value, switching, placeholder disabled, option list |
| 4 | Dynamic Loading | Planned |                                                                                                   |
| 5 | JavaScript Alerts | Planned |                                                                                                   |
| 6 | File Upload | Planned |                                                                                                   |
| 7 | Add/Remove Elements | Done    | initial state, add one, add many, delete one, delete all, button labels |
| 8 | Hovers | Planned |                                                                                                   |
| 9 | Multiple Windows | Planned |                                                                                                   |
| 10 | Broken Images | Done | image count, loaded vs broken count, HTTP status vs rendered state |
| 11 | Frames | Planned |                                                                                                   |
| 12 | Status Codes | Planned |                                                                                                   |
| 13 | Sortable Data Tables | Planned |                                                                                                   |
| 14 | Secure File Download | Planned |                                                                                                   |
