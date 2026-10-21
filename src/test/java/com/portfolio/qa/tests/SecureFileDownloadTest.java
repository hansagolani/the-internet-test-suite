package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.config.TestConfig;
import com.portfolio.qa.pages.SecureFileDownloadPage;
import com.portfolio.qa.util.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecureFileDownloadTest extends BaseTest {

    private SecureFileDownloadPage downloadPage;

    @BeforeEach
    void initializePage() {
        downloadPage = new SecureFileDownloadPage(driver);
    }

    @Test
    void withCredentials_pageLoadsAndListsFiles() {
        downloadPage.openWithCredentials();

        assertEquals(
                "Secure File Downloader",
                downloadPage.getHeading(),
                "Page should display the secure file downloader heading"
        );
        assertTrue(
                downloadPage.fileCount() > 0,
                "At least one downloadable file should be listed"
        );
    }

    @Test
    void withoutCredentials_returns401()
            throws IOException, InterruptedException {
        int status = HttpStatus.statusOf(
                TestConfig.BASE_URL + "/download_secure"
        );
        assertEquals(
                401,
                status,
                "Secure download endpoint should require authentication"
        );
    }

    @Test
    void clickFirstFile_downloadsIt() throws IOException {
        clearDownloadDirectory();
        downloadPage.openWithCredentials();

        String filename = downloadPage.clickFirstFile();
        Path downloadedFile = TestConfig.DOWNLOAD_DIR.resolve(filename);

        // Wait for the file to exist and Chrome's temporary download to disappear.
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(d -> {
            try (Stream<Path> files = Files.list(TestConfig.DOWNLOAD_DIR)) {
                boolean downloadInProgress = files.anyMatch(path ->
                        path.getFileName().toString().endsWith(".crdownload"));
                return Files.isRegularFile(downloadedFile)
                        && !downloadInProgress;
            } catch (IOException e) {
                return false;
            }
        });
        assertTrue(
                Files.size(downloadedFile) > 0,
                "Downloaded file should not be empty"
        );
    }

    private void clearDownloadDirectory() throws IOException {
        Files.createDirectories(TestConfig.DOWNLOAD_DIR);

        try (Stream<Path> files = Files.list(TestConfig.DOWNLOAD_DIR)) {
            for (Path file : files.toList()) {
                if (Files.isDirectory(file)) {
                    try (Stream<Path> children = Files.walk(file)) {
                        for (Path child : children
                                .sorted(Comparator.reverseOrder()).toList()) {
                            Files.delete(child);
                        }
                    }
                } else {
                    Files.delete(file);
                }
            }
        }
    }
}