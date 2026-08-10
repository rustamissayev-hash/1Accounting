package com.isayev.accounting.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Path("/api/v1/health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthResource {

    @ConfigProperty(name = "quarkus.application.name")
    String appName;

    @ConfigProperty(name = "quarkus.application.version")
    String appVersion;

    @GET
    public Response health() {
        log.debug("Health check requested");
        return Response.ok(Map.of(
            "status", "UP",
            "application", appName,
            "version", appVersion,
            "timestamp", LocalDateTime.now().toString()
        )).build();
    }
}
