package com.portfolio.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SortableDataTablesPage extends BasePage {

    private static final By TABLE = By.id("table1");
    private static final By HEADERS = By.cssSelector("#table1 thead th");
    private static final By ROWS = By.cssSelector("#table1 tbody tr");

    public SortableDataTablesPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TABLE));
        wait.until(ExpectedConditions.numberOfElementsToBe(ROWS, 4));
    }

    public int rowCount() {
        return driver.findElements(ROWS).size();
    }

    public int columnCount() {
        return driver.findElements(HEADERS).size();
    }

    private void validateColumn(int column) {
        if (column < 1 || column > columnCount()) {
            throw new IllegalArgumentException(
                    "Invalid column index: " + column);
        }
    }

    public void sortBy(int column) {
        validateColumn(column);
        // Re-find the header on every click to avoid stale elements.
        click(By.cssSelector("#table1 thead th:nth-of-type(" + column + ")"));
    }

    public List<String> columnValues(int column) {
        validateColumn(column);
        return driver.findElements(
                        By.cssSelector("#table1 tbody tr td:nth-of-type(" + column + ")"))
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}