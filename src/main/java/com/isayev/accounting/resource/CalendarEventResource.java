package com.isayev.accounting.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Path("/api/v1/calendar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalendarEventResource {

    @GET
    public Response listEvents(
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("type") String type,
            @QueryParam("completed") Boolean completed) {
        log.info("Listing calendar events: type={}, completed={}", type, completed);
        return Response.ok(List.of()).build();
    }

    @GET
    @Path("/{id}")
    public Response getEvent(@PathParam("id") String id) {
        log.info("Getting calendar event: {}", id);
        return Response.ok(Map.of("id", id)).build();
    }

    @POST
    public Response createEvent(Map<String, Object> event) {
        String id = UUID.randomUUID().toString();
        event.put("id", id);
        event.put("createdAt", LocalDateTime.now().toString());
        log.info("Creating calendar event: {}", event.get("title"));
        return Response.status(Response.Status.CREATED).entity(event).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEvent(@PathParam("id") String id, Map<String, Object> event) {
        log.info("Updating calendar event: {}", id);
        return Response.ok(event).build();
    }

    @POST
    @Path("/{id}/complete")
    public Response completeEvent(@PathParam("id") String id) {
        log.info("Completing calendar event: {}", id);
        return Response.ok(Map.of("id", id, "completed", true, "completedAt", LocalDateTime.now())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEvent(@PathParam("id") String id) {
        log.info("Deleting calendar event: {}", id);
        return Response.noContent().build();
    }
}
