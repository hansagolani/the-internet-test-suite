package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CheckboxesPage extends BasePage {

    private static final By CHECKBOXES = By.cssSelector("#checkboxes input[type='checkbox']");

    public CheckboxesPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(CHECKBOXES, 1));
    }

    public boolean isChecked(int index) {
        return getCheckbox(index).isSelected();
    }

    public void toggle(int index) {
        getCheckbox(index).click();
    }

    private WebElement getCheckbox(int index) {
        List<WebElement> elements = driver.findElements(CHECKBOXES);

        if (index < 0 || index >= elements.size()) {
            throw new IllegalArgumentException("Invalid checkbox index: " + index);
        }

        return elements.get(index);
    }
}

