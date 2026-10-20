package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.CheckboxesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CheckboxesTest extends BaseTest {

    private CheckboxesPage checkboxesPage;

    @BeforeEach
    void openCheckboxesPage() {
        open("/checkboxes");
        checkboxesPage = new CheckboxesPage(driver);
        checkboxesPage.waitUntilLoaded();
    }

    @Test
    void defaultState_box1Unchecked_box2Checked() {
        assertFalse(
                checkboxesPage.isChecked(0),
                "Checkbox 1 should initially be unchecked"
        );
        assertTrue(
                checkboxesPage.isChecked(1),
                "Checkbox 2 should initially be checked"
        );
    }

    @Test
    void toggleBox1_checksIt() {
        checkboxesPage.toggle(0);
        assertTrue(
                checkboxesPage.isChecked(0),
                "Checkbox 1 should be checked after toggling"
        );
    }

    @Test
    void toggleBox2_unchecksIt() {
        checkboxesPage.toggle(1);
        assertFalse(
                checkboxesPage.isChecked(1),
                "Checkbox 2 should be unchecked after toggling"
        );
    }

    @Test
    void toggleBox1_doesNotChangeBox2() {
        checkboxesPage.toggle(0);
        assertTrue(
                checkboxesPage.isChecked(0),
                "Checkbox 1 should be checked after toggling"
        );
        assertTrue(
                checkboxesPage.isChecked(1),
                "Checkbox 2 should remain checked when checkbox 1 is toggled"
        );
    }

    @Test
    void toggleBox2_doesNotChangeBox1() {
        checkboxesPage.toggle(1);
        assertFalse(
                checkboxesPage.isChecked(1),
                "Checkbox 2 should be unchecked after toggling"
        );
        assertFalse(
                checkboxesPage.isChecked(0),
                "Checkbox 1 should remain unchecked when checkbox 2 is toggled"
        );
    }

    @Test
    void toggleBox1Twice_returnsToOriginalState() {
        // Capture the initial state
        boolean initialState = checkboxesPage.isChecked(0);

        // Toggle twice
        checkboxesPage.toggle(0);
        checkboxesPage.toggle(0);

        assertEquals(
                initialState,
                checkboxesPage.isChecked(0),
                "Checkbox 1 should return to its original state after two toggles"
        );
    }

    @Test
    void toggleBox2Twice_returnsToOriginalState() {
        // Capture the initial state
        boolean initialState = checkboxesPage.isChecked(1);

        // Toggle twice
        checkboxesPage.toggle(1);
        checkboxesPage.toggle(1);

        assertEquals(
                initialState,
                checkboxesPage.isChecked(1),
                "Checkbox 2 should return to its original state after two toggles"
        );
    }
}
