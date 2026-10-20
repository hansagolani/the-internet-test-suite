package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.MultipleWindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultipleWindowsTest extends BaseTest {

    private MultipleWindowsPage windowsPage;

    @BeforeEach
    void openMultipleWindowsPage() {
        open("/windows");
        windowsPage = new MultipleWindowsPage(driver);
        windowsPage.saveOriginalWindowHandle();
    }

    @Test
    void clickHere_opensSecondWindow() {
        windowsPage.clickHere();
        windowsPage.waitForSecondWindow();
        assertEquals(2, windowsPage.windowCount());
    }

    @Test
    void newWindow_hasExpectedTitleAndHeading() {
        windowsPage.clickHere();
        windowsPage.waitForSecondWindow();
        windowsPage.switchToNewWindow();
        assertEquals("New Window", windowsPage.title());
        assertEquals("New Window", windowsPage.heading());
    }

    @Test
    void switchBackToOriginal_works() {
        windowsPage.clickHere();
        windowsPage.waitForSecondWindow();
        windowsPage.switchToNewWindow();
        windowsPage.switchToOriginalWindow();

        assertEquals("The Internet", windowsPage.title());
    }

    @Test
    void closingNewWindow_leavesOriginalOpen() {
        windowsPage.clickHere();
        windowsPage.waitForSecondWindow();
        windowsPage.switchToNewWindow();
        windowsPage.closeCurrentWindow();
        windowsPage.switchToOriginalWindow();

        assertTrue(windowsPage.isOriginalWindowOpen());
        assertEquals(1, windowsPage.windowCount());
        assertEquals("The Internet", windowsPage.title());
    }
}