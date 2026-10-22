# Test Plan

**Project:** The Internet Test Suite
**Application under test:** https://the-internet.herokuapp.com (public demo site for practising test automation)
**Author:** Hansa Golani
**Last updated:** October 2026

## 1. Purpose
Describe what is tested, how, and the known limits. Setup and run
instructions are in the README.

## 2. Scope

**In scope**
- The 14 pages listed in section 6: Form Authentication, Checkboxes, Dropdown, Dynamic Loading,
  JavaScript Alerts, File Upload, Add/Remove Elements, Hovers, Multiple
  Windows, Broken Images, Frames, Status Codes, Sortable Data Tables, Secure
  File Download.
- Functional behavior of each page: valid and invalid input, state changes,
  and what the user sees afterwards.
- HTTP-level checks where the browser hides the detail (status codes, broken
  image sources, unauthenticated access to a protected page).

**Out of scope**
- Browsers other than Chrome, and mobile or responsive layouts.
- Performance, load, security and accessibility testing.
- The other pages on the site that are not listed above.
- Continuous integration. Tests are run manually.

## 3. Approach
- **Page object pattern:** each page has a class holding its locators and
  actions. Test classes call those methods and hold the assertions, so a page
  change is fixed in one place.
- **Waits:** explicit waits (`WebDriverWait`, 10 seconds by default, set in
  `TestConfig`). No `Thread.sleep`.
- **Isolation:** every test starts a fresh browser and closes it afterwards, so
  tests do not depend on each other or on run order.
- **Failure evidence:** a screenshot is saved to `screenshots/` when a test
  fails.
- **HTTP checks:** a small helper (`HttpStatus`, built on Java's `HttpClient`)
  reads status codes directly. It does not follow redirects, so a 301 is seen
  as a 301.
- **Parameterized tests:** used where the same check repeats with different
  data (Dynamic Loading examples, Hovers users, Status Codes, file types).

## 4. Environment
| Item | Value |
|------|-------|
| OS | Windows |
| Browser | Chrome (tested on 155.0.8059.40) |
| Java | 17 |
| Selenium / JUnit | 4.25.0 / 5.10.3 |
| Build tool | Maven |

**How to run**
```
mvn test                     # browser window visible
mvn test -Dheadless=true     # no browser window
```

**Test data:** the demo site's public credentials (`tomsmith` /
`SuperSecretPassword!` for login, `admin` / `admin` for the secure download
page). Files used for upload are created as temporary files by the tests.

## 5. Pass / fail criteria
- A test passes when every assertion in it holds.
- The suite is considered healthy when all tests pass on repeated runs, both
  headless and with a visible browser.
- A failing test is investigated first as a possible flaky test (timing,
  shared site data) before it is raised as a defect.

## 6. Test cases

| ID | Page (URL) | Scenarios covered | Key expected results |
|----|------------|-------------------|----------------------|
| TC-01 | Form Authentication (`/login`) | valid login, wrong password, wrong username, logout | success banner and `/secure` after valid login; matching error banner for bad input; "logged out" banner after logout |
| TC-02 | Checkboxes (`/checkboxes`) | default state, toggle each box, boxes independent, double toggle | box 1 starts unchecked, box 2 checked; toggling one box does not change the other; two toggles return to the start state |
| TC-03 | Dropdown (`/dropdown`) | default placeholder, select by text, select by value, switch options, placeholder disabled, option list | placeholder selected by default and not selectable; last selection wins; exactly three options in order |
| TC-04 | Dynamic Loading (`/dynamic_loading/1`, `/2`) | both examples, hidden before start, start button disappears | "Hello World!" appears after loading in both examples; not visible before Start; Start button disappears |
| TC-05 | JavaScript Alerts (`/javascript_alerts`) | alert accept, confirm accept and dismiss, prompt with text, prompt dismissed | result text matches each action ("You clicked: Ok", "You entered: ...", "You entered: null") |
| TC-06 | File Upload (`/upload`) | upload text and CSV files, submit without a file | success heading and uploaded file name shown; empty submit returns a server error page (see DEF-001) |
| TC-07 | Add/Remove Elements (`/add_remove_elements/`) | initial state, add one, add many, delete one, delete all, button labels | button count follows every add and delete; every added button is labelled "Delete" |
| TC-08 | Hovers (`/hovers`) | captions hidden by default, caption per user, only the hovered caption shows, profile link | caption "name: userN" appears only for the hovered user; link points to `/users/N` |
| TC-09 | Multiple Windows (`/windows`) | opens second window, new window title and heading, switch back, close new window | two windows after the click; new window titled "New Window"; original window still usable after closing the new one |
| TC-10 | Broken Images (`/broken_images`) | image count, loaded versus broken count, HTTP status versus rendered state | three images, one loads and two are broken; the broken ones return 404 and the working one returns 200 |
| TC-11 | Frames (`/nested_frames`) | left, middle, right and bottom frames, switching between frames | each frame shows its own label (LEFT, MIDDLE, RIGHT, BOTTOM); frames can be read one after another |
| TC-12 | Status Codes (`/status_codes`) | HTTP status for 200, 301, 404, 500; links listed; status message pages | server returns the code in the URL; page lists four links; each page shows "This page returned a N status code." |
| TC-13 | Sortable Data Tables (`/tables`) | row and column count, last name ascending and descending, email, due amount | columns are sorted after clicking a header; Due is compared as a number, not as text |
| TC-14 | Secure File Download (`/download_secure`) | page behind basic auth, request without credentials, file download | page loads with credentials and lists files; 401 without credentials; the clicked file is downloaded and not empty |

## 7. Risks and limitations
- **Shared public site.** The site is used by many people and can change or be
  slow without notice. For example, the list of downloadable files is not fixed,
  so the download test uses whichever file is listed first.
- **Demo behavior.** Some pages are broken on purpose (Broken Images, and the
  empty upload). Those tests check the known behavior and are named to say so.
- **Network dependence.** Tests need internet access and a reachable site.
- **Single browser.** Results say nothing about Firefox, Safari or Edge.
- **Desktop interference.** With a visible browser, other windows or mouse
  movement on the same machine can disturb tests such as hover. Headless runs
  are not affected.

## 8. Defects found
| ID | Page | Summary | Severity |
|----|------|---------|----------|
| DEF-001 | File Upload | submitting without a file returns a server error page instead of a validation message | Low |

Details: [defect-report.md](defect-report.md)