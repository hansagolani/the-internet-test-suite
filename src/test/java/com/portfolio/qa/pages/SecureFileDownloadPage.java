package com.portfolio.qa.pages;

import com.portfolio.qa.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SecureFileDownloadPage extends BasePage {

    private static final By HEADING = By.cssSelector("#content h3");
    private static final By FILE_LINKS = By.cssSelector("#content a");

    public SecureFileDownloadPage(WebDriver driver) {
        super(driver);
    }

    public void openWithCredentials() {
        driver.get(TestConfig.BASE_URL.replace("https://", "https://admin:admin@") + "/download_secure");
        wait.until(ExpectedConditions.visibilityOfElementLocated(HEADING));
    }

    public String getHeading() {
        return textOf(HEADING);
    }

    public List<WebElement> getFileLinks() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(FILE_LINKS, 0));
        return driver.findElements(FILE_LINKS);
    }

    public String clickFirstFile() {
        List<WebElement> links = getFileLinks();
        String filename = links.get(0).getText().trim();
        links.get(0).click();
        return filename;
    }

    public int fileCount() {
        return driver.findElements(FILE_LINKS).size();
    }
}