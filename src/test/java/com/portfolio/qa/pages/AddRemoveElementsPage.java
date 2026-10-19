package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddRemoveElementsPage extends BasePage {

    private static final By ADD_BUTTON = By.cssSelector("button[onclick='addElement()']");

    private static final By DELETE_BUTTONS = By.cssSelector("#elements button.added-manually");

    public AddRemoveElementsPage(WebDriver driver) {
        super(driver);
    }

    public void addElement() {
        click(ADD_BUTTON);
    }

    public void addElements(int n) {
        for (int i = 0; i < n; i++) {
            addElement();
        }
    }

    public void clickDelete(int index) {
        List<WebElement> buttons = getDeleteButtons();

        if (index < 0 || index >= buttons.size()) {
            throw new IllegalArgumentException(
                    "Invalid Delete button index: " + index);
        }
        buttons.get(index).click();
    }

    public void deleteAll() {
        int remaining = getDeleteButtonCount();
        for (int i = 0; i < remaining; i++) {
            clickDelete(0);
        }
    }

    public int getDeleteButtonCount() {
        return driver.findElements(DELETE_BUTTONS).size();
    }

    public List<String> getDeleteButtonTexts() {
        return getDeleteButtons()
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    private List<WebElement> getDeleteButtons() {
        return driver.findElements(DELETE_BUTTONS);
    }
}
