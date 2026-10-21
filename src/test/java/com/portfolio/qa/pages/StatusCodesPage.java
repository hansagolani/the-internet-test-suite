package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class StatusCodesPage extends BasePage {

    private static final By STATUS_CODE_LINKS =
            By.cssSelector("#content ul li a");
    private static final By CONTENT = By.id("content");

    public StatusCodesPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getStatusCodeLinks() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                STATUS_CODE_LINKS, 3));

        return driver.findElements(STATUS_CODE_LINKS)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public void clickStatusCode(String code) {
        click(By.linkText(code));
    }

    public String getContentText() {
        return textOf(CONTENT);
    }
}