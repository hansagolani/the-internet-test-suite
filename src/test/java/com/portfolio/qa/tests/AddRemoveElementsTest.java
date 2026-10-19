package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.AddRemoveElementsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddRemoveElementsTest extends BaseTest {

    private AddRemoveElementsPage addRemovePage;

    @BeforeEach
    void openAddRemoveElementsPage() {
        open("/add_remove_elements/");
        addRemovePage = new AddRemoveElementsPage(driver);
    }

    @Test
    void initialState_hasNoDeleteButtons() {
        assertEquals(
                0,
                addRemovePage.getDeleteButtonCount(),
                "No Delete buttons should exist initially"
        );
    }

    @Test
    void addOneElement_showsOneDeleteButton() {
        addRemovePage.addElement();
        assertEquals(
                1,
                addRemovePage.getDeleteButtonCount(),
                "One Delete button should appear after clicking Add"
        );
    }

    @Test
    void addMultipleElements_countMatches() {
        addRemovePage.addElements(5);
        assertEquals(
                5,
                addRemovePage.getDeleteButtonCount(),
                "Five Delete buttons should appear after five clicks"
        );
    }

    @Test
    void deleteOneElement_decreasesCount() {
        addRemovePage.addElements(3);
        addRemovePage.clickDelete(0);
        assertEquals(
                2,
                addRemovePage.getDeleteButtonCount(),
                "Deleting one element should leave two Delete buttons"
        );
    }

    @Test
    void deleteAllElements_returnsToEmpty() {
        addRemovePage.addElements(3);
        addRemovePage.deleteAll();
        assertEquals(
                0,
                addRemovePage.getDeleteButtonCount(),
                "No Delete buttons should remain after deleting all elements"
        );
    }

    @Test
    void deleteButtonsHaveCorrectLabel() {
        addRemovePage.addElements(3);
        assertEquals(
                List.of("Delete", "Delete", "Delete"),
                addRemovePage.getDeleteButtonTexts(),
                "Every dynamically added button should have the label Delete"
        );
    }
}