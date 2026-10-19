package com.portfolio.qa.base;

import com.portfolio.qa.config.TestConfig;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

// Runs before @AfterEach, so the browser is still open when we grab the screenshot.
// (TestWatcher runs after teardown - too late.)
public class ScreenshotOnFailure implements TestExecutionExceptionHandler {

    private final Supplier<WebDriver> driver;

    public ScreenshotOnFailure(Supplier<WebDriver> driver) {
        this.driver = driver;
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable thrown) throws Throwable {
        try {
            WebDriver d = driver.get();
            if (d != null) {
                byte[] png = ((TakesScreenshot) d).getScreenshotAs(OutputType.BYTES);
                Files.createDirectories(TestConfig.SCREENSHOT_DIR);
                String name = context.getRequiredTestClass().getSimpleName() + "_"
                        + context.getRequiredTestMethod().getName() + ".png";
                Files.write(Path.of(TestConfig.SCREENSHOT_DIR.toString(), name), png);
            }
        } catch (Exception e) {
            System.err.println("Could not save screenshot: " + e.getMessage());
        }
        throw thrown;
    }
}
