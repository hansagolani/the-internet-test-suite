package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.LoginPage;
import com.portfolio.qa.pages.SecureAreaPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Form Authentication - /login
class LoginTest extends BaseTest {

    @Test
    void validLogin_landsOnSecureArea() {
        open("/login");
        SecureAreaPage secure = new LoginPage(driver).loginAs("tomsmith", "SuperSecretPassword!");

        assertTrue(secure.flashMessage().contains("You logged into a secure area!"));
        assertTrue(driver.getCurrentUrl().endsWith("/secure"));
    }

    @Test
    void invalidPassword_showsError() {
        open("/login");
        LoginPage login = new LoginPage(driver).submit("tomsmith", "wrong");

        assertTrue(login.flashMessage().contains("Your password is invalid!"));
    }

    @Test
    void invalidUsername_showsError() {
        open("/login");
        LoginPage login = new LoginPage(driver).submit("nobody", "SuperSecretPassword!");

        assertTrue(login.flashMessage().contains("Your username is invalid!"));
    }

    @Test
    void logout_returnsToLoginPage() {
        open("/login");
        SecureAreaPage secure = new LoginPage(driver).loginAs("tomsmith", "SuperSecretPassword!");

        LoginPage login = secure.logout();

        assertTrue(login.flashMessage().contains("You logged out of the secure area!"));
        assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

}
