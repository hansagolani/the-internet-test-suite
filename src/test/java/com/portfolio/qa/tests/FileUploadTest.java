package com.portfolio.qa.tests;

import com.portfolio.qa.base.BaseTest;
import com.portfolio.qa.pages.FileUploadPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileUploadTest extends BaseTest {

    private FileUploadPage fileUploadPage;

    @BeforeEach
    void openFileUploadPage() {
        open("/upload");
        fileUploadPage = new FileUploadPage(driver);
    }

    @ParameterizedTest
    @CsvSource({
            "upload-test.txt, hello",
            "upload-test.csv, 'name,age'"
    })
    void uploadFile_showsSuccessHeadingAndFilename(
            String filename,
            String content,
            @TempDir Path dir) throws IOException {
        Path file = dir.resolve(filename);
        Files.writeString(file, content);
        fileUploadPage.uploadFile(file);

        assertEquals(
                "File Uploaded!",
                fileUploadPage.getSuccessHeading(),
                "Uploading " + filename + " should succeed"
        );

        assertEquals(
                filename,
                fileUploadPage.getUploadedFilename(),
                "Uploaded filename should match " + filename
        );
    }

    @Test
    void submitWithoutFile_returnsServerError_knownIssue() {
        fileUploadPage.submitWithoutFile();

        assertEquals(
                "Internal Server Error",
                fileUploadPage.getErrorHeading(),
                "Submitting without a file should display an error heading"
        );

        assertEquals(
                "No file was chosen. Please choose a file to upload.",
                fileUploadPage.getErrorMessage(),
                "Error page should explain that no file was selected"
        );
    }

}