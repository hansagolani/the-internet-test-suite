package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FramesPage extends BasePage {

    private static final By TOP_FRAME = By.name("frame-top");
    private static final By BOTTOM_FRAME = By.name("frame-bottom");

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    public String readTopFrame(String frameName) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(TOP_FRAME));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name(frameName)));

            return driver.findElement(By.tagName("body")).getText().trim();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public String readBottomFrame() {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(BOTTOM_FRAME));
            return driver.findElement(By.tagName("body")).getText().trim();
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}
