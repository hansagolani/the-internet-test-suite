package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.DynamicLoadingPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DynamicLoadingTest extends BaseTest {

    private DynamicLoadingPage openPage(String path) {
        open(path);
        return new DynamicLoadingPage(driver);
    }

    @Test
    void example1_afterStart_showsHelloWorld() {
        DynamicLoadingPage page = openPage("/dynamic_loading/1");
        page.clickStart();
        assertEquals("Hello World!", page.waitForHelloWorld());
    }

    @Test
    void example2_afterStart_showsHelloWorld() {
        DynamicLoadingPage page = openPage("/dynamic_loading/2");
        page.clickStart();
        assertEquals("Hello World!", page.waitForHelloWorld());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/dynamic_loading/1", "/dynamic_loading/2"})
    void beforeStart_helloWorldNotVisible(String path) {
        DynamicLoadingPage page = openPage(path);
        assertFalse(page.isHelloWorldVisible(), "Hello World should not be visible before clicking Start");
    }

    @ParameterizedTest
    @ValueSource(strings = {"/dynamic_loading/1", "/dynamic_loading/2"})
    void startButton_disappearsAfterClick(String path) {
        DynamicLoadingPage page = openPage(path);
        page.clickStart();
        page.waitForStartButtonToDisappear();
        assertFalse(page.isStartButtonVisible(), "Start button should disappear after clicking");
    }
}