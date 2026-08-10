package com.isayev.accounting.resource;

import com.isayev.accounting.entity.AccountingPolicy;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Path("/api/v1/accounting-policies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountingPolicyResource {

    @GET
    public Response listPolicies() {
        log.info("Listing accounting policies");
        return Response.ok(List.of()).build();
    }

    @GET
    @Path("/{id}")
    public Response getPolicy(@PathParam("id") String id) {
        log.info("Getting accounting policy: {}", id);
        return Response.ok(Map.of("id", id, "status", "placeholder")).build();
    }

    @POST
    public Response createPolicy(AccountingPolicy policy) {
        policy.setId(UUID.randomUUID().toString());
        policy.setCreatedAt(LocalDateTime.now());
        log.info("Creating accounting policy: {}", policy.getName());
        return Response.status(Response.Status.CREATED).entity(policy).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePolicy(@PathParam("id") String id, AccountingPolicy policy) {
        log.info("Updating accounting policy: {}", id);
        return Response.ok(policy).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePolicy(@PathParam("id") String id) {
        log.info("Deleting accounting policy: {}", id);
        return Response.noContent().build();
    }
}
