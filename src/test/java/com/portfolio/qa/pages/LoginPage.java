package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By USERNAME = By.id("username");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By FLASH = By.id("flash");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public SecureAreaPage loginAs(String username, String password) {
        submit(username, password);
        return new SecureAreaPage(driver);
    }

    // for failed logins - stays on the login page
    public LoginPage submit(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        click(LOGIN_BUTTON);
        return this;
    }

    public String flashMessage() {
        return textOf(FLASH);
    }
}
