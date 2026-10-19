package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.BrokenImagesPage;
import com.portfolio.qa.util.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrokenImagesTest extends BaseTest {

    private BrokenImagesPage brokenImagesPage;

    @BeforeEach
    void openBrokenImagesPage() {
        open("/broken_images");
        brokenImagesPage = new BrokenImagesPage(driver);
        brokenImagesPage.waitUntilLoaded();
    }

    @Test
    void pageHasThreeImages() {
        assertEquals(3, brokenImagesPage.imageCount(), "Page should contain exactly three images");
    }

    @Test
    void exactlyOneImageLoads() {
        assertEquals(1, brokenImagesPage.loadedCount(), "Exactly one image should load");
    }

    @Test
    void exactlyTwoImagesAreBroken() {
        assertEquals(2, brokenImagesPage.brokenCount(), "Exactly two images should be broken");
    }

    @Test
    void imageHttpStatus_matchesRenderedState() throws IOException, InterruptedException {
        for (int i = 0; i < brokenImagesPage.imageCount(); i++) {
            String src = brokenImagesPage.imageSrc(i);
            int expectedStatus = brokenImagesPage.isLoaded(i) ? 200 : 404;

            assertEquals(
                    expectedStatus,
                    HttpStatus.statusOf(src),
                    "Unexpected HTTP status for image: " + src
            );
        }
    }
}