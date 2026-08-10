package com.isayev.accounting.resource;

import com.isayev.accounting.entity.TaxPolicy;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Path("/api/v1/tax-policies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaxPolicyResource {

    @GET
    public Response listTaxPolicies() {
        log.info("Listing tax policies");
        return Response.ok(List.of()).build();
    }

    @GET
    @Path("/{id}")
    public Response getTaxPolicy(@PathParam("id") String id) {
        log.info("Getting tax policy: {}", id);
        return Response.ok(Map.of("id", id, "status", "placeholder")).build();
    }

    @POST
    public Response createTaxPolicy(TaxPolicy policy) {
        policy.setId(UUID.randomUUID().toString());
        policy.setCreatedAt(LocalDateTime.now());
        log.info("Creating tax policy: {}", policy.getName());
        return Response.status(Response.Status.CREATED).entity(policy).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTaxPolicy(@PathParam("id") String id, TaxPolicy policy) {
        log.info("Updating tax policy: {}", id);
        return Response.ok(policy).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTaxPolicy(@PathParam("id") String id) {
        log.info("Deleting tax policy: {}", id);
        return Response.noContent().build();
    }
}
