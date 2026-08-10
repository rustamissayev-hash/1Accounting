package com.isayev.accounting.config;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ConfigUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Slf4j
@ApplicationScoped
public class AppStartupListener {

    @ConfigProperty(name = "quarkus.application.name")
    String appName;

    @ConfigProperty(name = "quarkus.application.version")
    String appVersion;

    @ConfigProperty(name = "quarkus.neo4j.uri")
    String neo4jUri;

    @ConfigProperty(name = "accounting.app.default-language")
    String defaultLanguage;

    @ConfigProperty(name = "accounting.app.country")
    String country;

    void onStart(@Observes StartupEvent ev) {
        log.info("═══ Application Started ═══");
        log.info("Name: {}", appName);
        log.info("Version: {}", appVersion);
        log.info("Profile: {}", ConfigUtils.getProfiles());
        log.info("Neo4j URI: {}", neo4jUri);
        log.info("Default Language: {}", defaultLanguage);
        log.info("Country: {}", country);
        log.info("════════════════════════════");
    }
}
