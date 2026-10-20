package com.portfolio.qa.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JavaScriptAlertsPage extends BasePage {

    private static final By JS_ALERT = By.xpath("//button[text()='Click for JS Alert']");
    private static final By JS_CONFIRM = By.xpath("//button[text()='Click for JS Confirm']");
    private static final By JS_PROMPT = By.xpath("//button[text()='Click for JS Prompt']");
    private static final By RESULT = By.id("result");

    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    public void clickAlert() {
        click(JS_ALERT);
    }

    public void clickConfirm() {
        click(JS_CONFIRM);
    }

    public void clickPrompt() {
        click(JS_PROMPT);
    }

    public String waitForAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    public void enterPromptText(String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
    }

    public String resultText() {
        return textOf(RESULT);
    }
}
