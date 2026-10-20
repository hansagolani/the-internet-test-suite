package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.JavaScriptAlertsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaScriptAlertsTest extends BaseTest {

    private JavaScriptAlertsPage alertsPage;

    @BeforeEach
    void openJavaScriptAlertsPage() {
        open("/javascript_alerts");
        alertsPage = new JavaScriptAlertsPage(driver);
    }

    @Test
    void alert_accept_showsSuccessMessage() {
        alertsPage.clickAlert();
        assertEquals("I am a JS Alert", alertsPage.waitForAlertText());
        alertsPage.acceptAlert();
        assertEquals("You successfully clicked an alert", alertsPage.resultText());
    }

    @Test
    void confirm_accept_showsOk() {
        alertsPage.clickConfirm();
        alertsPage.acceptAlert();
        assertEquals("You clicked: Ok", alertsPage.resultText());
    }

    @Test
    void confirm_dismiss_showsCancel() {
        alertsPage.clickConfirm();
        alertsPage.dismissAlert();
        assertEquals("You clicked: Cancel", alertsPage.resultText());
    }

    @Test
    void prompt_withText_showsEnteredText() {
        alertsPage.clickPrompt();
        alertsPage.enterPromptText("Portfolio Test");
        assertEquals("You entered: Portfolio Test", alertsPage.resultText());
    }

    @Test
    void prompt_dismiss_showsNull() {
        alertsPage.clickPrompt();
        alertsPage.dismissAlert();
        assertEquals("You entered: null", alertsPage.resultText());
    }
}