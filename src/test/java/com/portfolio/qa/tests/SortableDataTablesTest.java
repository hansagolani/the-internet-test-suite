package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.SortableDataTablesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SortableDataTablesTest extends BaseTest {

    private SortableDataTablesPage tablesPage;

    @BeforeEach
    void openTablesPage() {
        open("/tables");
        tablesPage = new SortableDataTablesPage(driver);
        tablesPage.waitUntilLoaded();
    }

    @Test
    void tableHasFourRowsAndSixColumns() {
        assertEquals(4, tablesPage.rowCount(), "Table should contain four data rows");
        assertEquals(6, tablesPage.columnCount(), "Table should contain six columns");
    }

    @Test
    void sortByLastName_ascending() {
        assertSortedAscending(1, Comparator.naturalOrder());
    }

    // Each test opens a fresh page, so sorting starts in its default state.
    // Clicking the Last Name header twice toggles ascending, then descending.
    @Test
    void sortByLastName_descending() {
        tablesPage.sortBy(1);
        tablesPage.sortBy(1);
        assertSortedDescending(tablesPage.columnValues(1), Comparator.naturalOrder());
    }

    @Test
    void sortByEmail_ascending() {
        assertSortedAscending(3, Comparator.naturalOrder());
    }

    @Test
    void sortByDue_ascending() {
        List<Double> defaultValues = getDueValues();
        List<Double> expected = new ArrayList<>(defaultValues);
        expected.sort(Comparator.naturalOrder());

        assertNotEquals(expected, defaultValues,
                "Precondition: Due column should not already be sorted numerically"
        );
        tablesPage.sortBy(4);
        assertEquals(expected, getDueValues(),
                "Due amounts should be sorted numerically in ascending order"
        );
    }

    private void assertSortedAscending(int column, Comparator<String> comparator) {
        List<String> defaultValues = tablesPage.columnValues(column);
        List<String> expected = new ArrayList<>(defaultValues);
        expected.sort(comparator);

        assertNotEquals(
                expected,
                defaultValues,
                "Precondition: column " + column + " should not already be sorted"
        );

        tablesPage.sortBy(column);

        assertEquals(
                expected,
                tablesPage.columnValues(column),
                "Column " + column + " should be sorted ascending"
        );
    }

    private List<Double> getDueValues() {
        return tablesPage.columnValues(4)
                .stream()
                .map(value -> Double.parseDouble(
                        value.replace("$", "").trim()))
                .toList();
    }

    private void assertSortedDescending(List<String> actual, Comparator<String> comparator) {
        List<String> expected = new ArrayList<>(actual);
        expected.sort(comparator.reversed());

        assertEquals(expected, actual, "Column should be sorted descending");
    }
}