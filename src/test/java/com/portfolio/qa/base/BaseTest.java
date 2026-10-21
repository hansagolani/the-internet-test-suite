package com.portfolio.qa.base;

import com.portfolio.qa.config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {

    protected WebDriver driver;

    @RegisterExtension
    final ScreenshotOnFailure screenshots = new ScreenshotOnFailure(() -> driver);

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(TestConfig.DOWNLOAD_DIR);

        ChromeOptions options = new ChromeOptions();
        if (TestConfig.isHeadless()) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }

        // needed for the Secure File Download test
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", TestConfig.DOWNLOAD_DIR.toString());
        prefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", prefs);

        // Selenium Manager fetches chromedriver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void open(String path) {
        driver.get(TestConfig.BASE_URL + path);
    }
}
