package com.isayev.accounting.resource;

import com.isayev.accounting.entity.ChartOfAccounts;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Path("/api/v1/chart-of-accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChartOfAccountsResource {

    @GET
    public Response listAccounts(@QueryParam("type") String type) {
        log.info("Listing chart of accounts, type filter: {}", type);
        return Response.ok(List.of()).build();
    }

    @GET
    @Path("/{code}")
    public Response getAccount(@PathParam("code") String code) {
        log.info("Getting account: {}", code);
        return Response.ok(Map.of("code", code)).build();
    }

    @POST
    public Response createAccount(ChartOfAccounts account) {
        account.setId(UUID.randomUUID().toString());
        log.info("Creating account: {} - {}", account.getCode(), account.getNameRu());
        return Response.status(Response.Status.CREATED).entity(account).build();
    }

    @PUT
    @Path("/{code}")
    public Response updateAccount(@PathParam("code") String code, ChartOfAccounts account) {
        log.info("Updating account: {}", code);
        return Response.ok(account).build();
    }

    @DELETE
    @Path("/{code}")
    public Response deleteAccount(@PathParam("code") String code) {
        log.info("Deleting account: {}", code);
        return Response.noContent().build();
    }
}
