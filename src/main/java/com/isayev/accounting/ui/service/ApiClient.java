package com.isayev.accounting.ui.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

/**
 * HTTP клиент для связи с Quarkus Backend.
 * Использует встроенный java.net.http.HttpClient (JDK 11+).
 */
@Slf4j
public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api/v1";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private String authHeader;

    public ApiClient() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public void setCredentials(String username, String password) {
        String auth = username + ":" + password;
        this.authHeader = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        log.info("Credentials set for user: {}", username);
    }

    public CompletableFuture<Boolean> loginAsync(String username, String password) {
        setCredentials(username, password);
        return healthCheckAsync();
    }

    public CompletableFuture<Boolean> healthCheckAsync() {
        return sendGetAsync("/health")
                .thenApply(response -> {
                    boolean ok = response.statusCode() == 200;
                    log.info("Health check: {}", ok ? "UP" : "DOWN");
                    return ok;
                })
                .exceptionally(ex -> {
                    log.error("Health check failed: {}", ex.getMessage());
                    return false;
                });
    }

    public CompletableFuture<HttpResponse<String>> sendGetAsync(String path) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .GET();

        if (authHeader != null) {
            builder.header("Authorization", authHeader);
        }

        return httpClient.sendAsync(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    public CompletableFuture<HttpResponse<String>> sendPostAsync(String path, String jsonBody) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody));

        if (authHeader != null) {
            builder.header("Authorization", authHeader);
        }

        return httpClient.sendAsync(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
