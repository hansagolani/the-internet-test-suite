package com.portfolio.qa.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DropdownPage extends BasePage {

    private static final By DROPDOWN = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    private Select getDropdown() {
        return new Select(waitVisible(DROPDOWN));
    }

    public String getSelectedOptionText() {
        return getDropdown().getFirstSelectedOption().getText();
    }

    public void selectByVisibleText(String text) {
        getDropdown().selectByVisibleText(text);
    }

    public void selectByValue(String value) {
        getDropdown().selectByValue(value);
    }

    public boolean isPlaceholderEnabled() {
        // The placeholder is the first option on this page
        return getDropdown()
                .getOptions()
                .get(0)
                .isEnabled();
    }

    public List<String> getOptionTexts() {
        return getDropdown()
                .getOptions()
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public int getOptionCount() {
        return getDropdown().getOptions().size();
    }
}