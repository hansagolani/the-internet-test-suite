package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.DropdownPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DropdownTest extends BaseTest {

    private DropdownPage dropdownPage;

    @BeforeEach
    void openDropdownPage() {
        open("/dropdown");
        dropdownPage = new DropdownPage(driver);
    }

    @Test
    void defaultSelection_isPlaceholder() {
        assertEquals(
                "Please select an option",
                dropdownPage.getSelectedOptionText(),
                "The placeholder should be selected by default");
    }

    @Test
    void selectOption1ByVisibleText() {
        dropdownPage.selectByVisibleText("Option 1");

        assertEquals(
                "Option 1",
                dropdownPage.getSelectedOptionText(),
                "Option 1 should be selected"
        );
    }

    @Test
    void selectOption2ByValue() {
        dropdownPage.selectByValue("2");

        assertEquals(
                "Option 2",
                dropdownPage.getSelectedOptionText(),
                "Option 2 should be selected"
        );
    }

    @Test
    void switchingOptions_keepsOnlyLatest() {
        dropdownPage.selectByVisibleText("Option 1");
        dropdownPage.selectByVisibleText("Option 2");

        assertEquals(
                "Option 2",
                dropdownPage.getSelectedOptionText(),
                "Option 2 should be the currently selected option"
        );
    }

    @Test
    void placeholderOption_isDisabled() {
        assertFalse(
                dropdownPage.isPlaceholderEnabled(),
                "The placeholder option should be disabled"
        );
    }

    @Test
    void dropdownHasExactlyThreeOptions() {
        assertEquals(
                3,
                dropdownPage.getOptionCount(),
                "The dropdown should contain exactly three options"
        );
        assertEquals(
                List.of("Please select an option", "Option 1", "Option 2"),
                dropdownPage.getOptionTexts(),
                "The dropdown options should appear in the expected order"
        );
    }
}