package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicLoadingPage extends BasePage {

    private static final By START_BUTTON = By.cssSelector("#start button");
    private static final By FINISHED_TEXT = By.id("finish");

    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }

    public void clickStart() {
        click(START_BUTTON);
    }

    public boolean isHelloWorldVisible() {
        return !driver.findElements(FINISHED_TEXT).isEmpty()
                && driver.findElement(FINISHED_TEXT).isDisplayed()
                && driver.findElement(FINISHED_TEXT).getText().equals("Hello World!");
    }

    public String waitForHelloWorld() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(FINISHED_TEXT)).getText();
    }

    public boolean isStartButtonVisible() {
        return driver.findElements(START_BUTTON).stream()
                .anyMatch(element -> element.isDisplayed());
    }

    public void waitForStartButtonToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(START_BUTTON));
    }
}
