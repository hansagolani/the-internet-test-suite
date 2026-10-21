package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.StatusCodesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatusCodesUiTest extends BaseTest {

    private StatusCodesPage statusCodesPage;

    @BeforeEach
    void openStatusCodesPage() {
        open("/status_codes");
        statusCodesPage = new StatusCodesPage(driver);
    }

    @Test
    void statusCodesPage_listsFourLinks() {
        List<String> links = statusCodesPage.getStatusCodeLinks();
        assertEquals(
                4,
                links.size(),
                "Status codes page should list four links"
        );
        assertTrue(
                links.containsAll(List.of("200", "301", "404", "500")),
                "Status codes page should contain links for 200, 301, 404 and 500"
        );
    }

    @ParameterizedTest
    @CsvSource({"200", "404", "500"})
    void clickLink_showsStatusMessage(String code) {
        statusCodesPage.clickStatusCode(code);
        String expectedMessage = "This page returned a " + code + " status code.";

        assertTrue(
                statusCodesPage.getContentText().contains(expectedMessage),
                "Page should display: " + expectedMessage
        );
    }
}