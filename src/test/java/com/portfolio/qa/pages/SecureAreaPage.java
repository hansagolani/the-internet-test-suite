package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePage {

    private static final By FLASH = By.id("flash");
    private static final By LOGOUT = By.cssSelector("a.button[href='/logout']");

    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    public String flashMessage() {
        return textOf(FLASH);
    }

    public LoginPage logout() {
        click(LOGOUT);
        return new LoginPage(driver);
    }
}
