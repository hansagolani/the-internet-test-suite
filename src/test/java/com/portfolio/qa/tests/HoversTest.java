package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.HoversPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HoversTest extends BaseTest {

    private HoversPage hoversPage;

    @BeforeEach
    void openHoversPage() {
        open("/hovers");
        hoversPage = new HoversPage(driver);
    }

    @Test
    void captionsHiddenByDefault() {
        for (int user = 1; user <= hoversPage.figureCount(); user++) {
            assertFalse(
                    hoversPage.isCaptionVisible(user),
                    "User " + user + " caption should initially be hidden"
            );
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void hoverShowsCorrectName(int user) {
        hoversPage.hoverOverUser(user);
        assertTrue(hoversPage.isCaptionVisible(user));
        assertTrue(
                hoversPage.captionText(user).contains("name: user" + user),
                "Expected caption for user " + user
        );
    }

    @Test
    void hoveringOneImage_showsOnlyThatCaption() {
        hoversPage.hoverOverUser(2);
        assertTrue(hoversPage.isCaptionVisible(2));
        assertEquals(1, hoversPage.visibleCaptionCount());
    }

    @Test
    void viewProfileLink_pointsToCorrectUser() {
        hoversPage.hoverOverUser(1);
        assertTrue(hoversPage.isCaptionVisible(1));
        assertTrue(hoversPage.profileUrl(1).endsWith("/users/1"));
    }
}