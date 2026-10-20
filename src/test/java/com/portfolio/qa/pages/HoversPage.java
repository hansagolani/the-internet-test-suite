package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class HoversPage extends BasePage {

    private static final By FIGURES = By.cssSelector(".figure");
    private static final By CAPTION = By.cssSelector(".figcaption");
    private static final By PROFILE_LINK = By.cssSelector("a");

    public HoversPage(WebDriver driver) {
        super(driver);
    }

    public void hoverOverUser(int userIndex) {
        new Actions(driver).moveToElement(getFigure(userIndex)).perform();
        wait.until(d -> getCaption(userIndex).isDisplayed());
    }

    public boolean isCaptionVisible(int userIndex) {
        return getCaption(userIndex).isDisplayed();
    }

    public String captionText(int userIndex) {
        return getCaption(userIndex).getText();
    }

    public String profileUrl(int userIndex) {
        return getCaption(userIndex).findElement(PROFILE_LINK).getAttribute("href");
    }

    public int visibleCaptionCount() {
        return (int) figures().stream()
                .filter(figure -> figure.findElement(CAPTION).isDisplayed())
                .count();
    }

    public int figureCount() {
        return figures().size();
    }

    private WebElement getFigure(int userIndex) {
        List<WebElement> figures = figures();
        if (userIndex < 1 || userIndex > figures.size()) {
            throw new IllegalArgumentException("Invalid user index: " + userIndex);
        }
        return figures.get(userIndex - 1);
    }

    private WebElement getCaption(int userIndex) {
        return getFigure(userIndex).findElement(CAPTION);
    }

    private List<WebElement> figures() {
        return driver.findElements(FIGURES);
    }
}