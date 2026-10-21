package com.portfolio.qa.tests;

import com.portfolio.qa.config.TestConfig;
import com.portfolio.qa.util.HttpStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusCodesHttpTest {

    // HttpStatus uses Java's default HttpClient redirect behavior,
    // so the 301 response is verified without following the redirect.
    @ParameterizedTest
    @ValueSource(ints = {200, 301, 404, 500})
    void statusCode_isReturnedByServer(int code) throws IOException, InterruptedException {
        int actualStatus = HttpStatus.statusOf(
                TestConfig.BASE_URL + "/status_codes/" + code
        );
        assertEquals(code, actualStatus, "Endpoint should return HTTP status " + code
        );
    }
}