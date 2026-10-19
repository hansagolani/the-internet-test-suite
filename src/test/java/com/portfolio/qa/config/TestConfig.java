package com.portfolio.qa.config;

import java.nio.file.Path;
import java.time.Duration;

public final class TestConfig {

    public static final String BASE_URL = "https://the-internet.herokuapp.com";
    public static final Duration TIMEOUT = Duration.ofSeconds(10);
    public static final Path SCREENSHOT_DIR = Path.of("screenshots");

    private TestConfig() {
    }

    // run with -Dheadless=true
    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }
}
