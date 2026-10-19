package com.portfolio.qa.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public final class HttpStatus {

    // Default client does not follow redirects, so a 301 comes back as 301.
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private HttpStatus() {
    }

    public static int statusOf(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<Void> response = CLIENT.send(
                request,
                HttpResponse.BodyHandlers.discarding()
        );

        return response.statusCode();
    }
}