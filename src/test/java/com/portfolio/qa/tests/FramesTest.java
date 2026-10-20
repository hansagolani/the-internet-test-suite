package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.FramesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FramesTest extends BaseTest {

    private FramesPage framesPage;

    @BeforeEach
    void openFramesPage() {
        open("/nested_frames");
        framesPage = new FramesPage(driver);
    }

    @Test
    void leftFrame_showsLEFT() {
        assertEquals("LEFT", framesPage.readTopFrame("frame-left"));
    }

    @Test
    void middleFrame_showsMIDDLE() {
        assertEquals("MIDDLE", framesPage.readTopFrame("frame-middle"));
    }

    @Test
    void rightFrame_showsRIGHT() {
        assertEquals("RIGHT", framesPage.readTopFrame("frame-right"));
    }

    @Test
    void bottomFrame_showsBOTTOM() {
        assertEquals("BOTTOM", framesPage.readBottomFrame());
    }

    @Test
    void canSwitchBackAndReadAnotherFrame() {
        assertEquals("MIDDLE", framesPage.readTopFrame("frame-middle"));
        assertEquals("BOTTOM", framesPage.readBottomFrame());
    }
}