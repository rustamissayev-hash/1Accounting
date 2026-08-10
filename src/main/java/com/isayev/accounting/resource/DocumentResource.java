package com.isayev.accounting.resource;

import com.isayev.accounting.entity.Document;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Path("/api/v1/documents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentResource {

    @GET
    public Response listDocuments(
            @QueryParam("type") String type,
            @QueryParam("status") String status,
            @QueryParam("from") String fromDate,
            @QueryParam("to") String toDate) {
        log.info("Listing documents with filters: type={}, status={}", type, status);
        return Response.ok(List.of()).build();
    }

    @GET
    @Path("/{id}")
    public Response getDocument(@PathParam("id") String id) {
        log.info("Getting document: {}", id);
        return Response.ok(Map.of("id", id, "status", "placeholder")).build();
    }

    @POST
    public Response createDocument(Document document) {
        document.setId(UUID.randomUUID().toString());
        document.setCreatedAt(LocalDateTime.now());
        document.setStatus("draft");
        log.info("Creating document: {}", document.getDocumentNumber());
        return Response.status(Response.Status.CREATED).entity(document).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateDocument(@PathParam("id") String id, Document document) {
        log.info("Updating document: {}", id);
        return Response.ok(document).build();
    }

    @POST
    @Path("/{id}/post")
    public Response postDocument(@PathParam("id") String id) {
        log.info("Posting document: {}", id);
        return Response.ok(Map.of("id", id, "status", "posted", "postedAt", LocalDateTime.now())).build();
    }

    @POST
    @Path("/{id}/sign")
    public Response signDocument(@PathParam("id") String id, Map<String, String> signatureData) {
        log.info("Signing document: {}", id);
        return Response.ok(Map.of("id", id, "status", "signed", "signedAt", LocalDateTime.now())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDocument(@PathParam("id") String id) {
        log.info("Deleting document: {}", id);
        return Response.noContent().build();
    }
}
