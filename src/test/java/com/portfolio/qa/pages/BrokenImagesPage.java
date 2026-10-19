package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrokenImagesPage extends BasePage {

    private static final By IMAGES = By.cssSelector(".example img");

    public BrokenImagesPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        wait.until(d -> images().stream().allMatch(image ->
                (Boolean) ((JavascriptExecutor) d).executeScript(
                        "return arguments[0].complete", image
                )
        ));
    }

    public int imageCount() {
        return images().size();
    }

    public boolean isLoaded(int index) {
        Boolean isLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && arguments[0].naturalWidth > 0;", getImage(index));
        return isLoaded;
    }

    public int loadedCount() {
        int count = 0;
        for (int i = 0; i < imageCount(); i++) {
            if (isLoaded(i)) {
                count++;
            }
        }
        return count;
    }

    public int brokenCount() {
        int count = 0;
        for (int i = 0; i < imageCount(); i++) {
            if (!isLoaded(i)) {
                count++;
            }
        }
        return count;
    }

    public String imageSrc(int index) {
        return getImage(index).getAttribute("src");
    }

    private List<WebElement> images() {
        return driver.findElements(IMAGES);
    }

    private WebElement getImage(int index) {
        List<WebElement> elements = images();
        if (index < 0 || index >= elements.size()) {
            throw new IllegalArgumentException("Invalid image index: " + index);
        }
        return elements.get(index);
    }
}