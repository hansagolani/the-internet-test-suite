package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class MultipleWindowsPage extends BasePage {

    private static final By CLICK_HERE = By.linkText("Click Here");
    private static final By HEADING = By.tagName("h3");

    private String originalWindow;

    public MultipleWindowsPage(WebDriver driver) {
        super(driver);
    }

    public void saveOriginalWindowHandle() {
        originalWindow = driver.getWindowHandle();
    }

    public void clickHere() {
        click(CLICK_HERE);
    }

    public void waitForSecondWindow() {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    public int windowCount() {
        return driver.getWindowHandles().size();
    }

    public void switchToNewWindow() {
        Set<String> handles = driver.getWindowHandles();

        for (String handle : handles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                return;
            }
        }

        throw new IllegalStateException("New window was not found");
    }

    public String title() {
        return driver.getTitle();
    }

    public String heading() {
        return textOf(HEADING);
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    public void switchToOriginalWindow() {
        driver.switchTo().window(originalWindow);
    }

    public boolean isOriginalWindowOpen() {
        return driver.getWindowHandles().contains(originalWindow);
    }
}