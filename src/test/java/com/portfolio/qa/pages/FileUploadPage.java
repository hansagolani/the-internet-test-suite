package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.nio.file.Path;

public class FileUploadPage extends BasePage {

    private static final By FILE_UPLOAD = By.id("file-upload");
    private static final By FILE_SUBMIT = By.id("file-submit");
    private static final By SUCCESS_HEADING = By.cssSelector("#content h3");
    private static final By UPLOADED_FILENAME = By.id("uploaded-files");
    private static final By ERROR_HEADING = By.tagName("h3");
    private static final By ERROR_MESSAGE = By.tagName("p");

    public FileUploadPage(WebDriver driver) {
        super(driver);
    }

    public void uploadFile(Path file) {
        driver.findElement(FILE_UPLOAD)
                .sendKeys(file.toAbsolutePath().toString());
        click(FILE_SUBMIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_HEADING));
    }

    public String getSuccessHeading() {
        return textOf(SUCCESS_HEADING);
    }

    public String getUploadedFilename() {
        return textOf(UPLOADED_FILENAME);
    }

    public void submitWithoutFile() {
        click(FILE_SUBMIT);
    }

    public String getErrorHeading() {
        return textOf(ERROR_HEADING);
    }

    public String getErrorMessage() {
        return textOf(ERROR_MESSAGE);
    }
}