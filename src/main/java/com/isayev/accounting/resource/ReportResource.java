package com.isayev.accounting.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Path("/api/v1/reports")
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {

    @GET
    @Path("/balance-sheet")
    public Response balanceSheet(
            @QueryParam("date") String date,
            @QueryParam("orgId") String orgId) {
        log.info("Generating balance sheet for org={} on date={}", orgId, date);
        return Response.ok(Map.of(
            "reportType", "balance-sheet",
            "date", date,
            "assets", Map.of("total", 0),
            "liabilities", Map.of("total", 0)
        )).build();
    }

    @GET
    @Path("/income-statement")
    public Response incomeStatement(
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("orgId") String orgId) {
        log.info("Generating income statement for org={} period={}-{}", orgId, from, to);
        return Response.ok(Map.of(
            "reportType", "income-statement",
            "period", from + " - " + to,
            "revenue", 0,
            "expenses", 0
        )).build();
    }

    @GET
    @Path("/ledger")
    public Response ledger(
            @QueryParam("account") String accountCode,
            @QueryParam("from") String from,
            @QueryParam("to") String to) {
        log.info("Generating ledger for account={} period={}-{}", accountCode, from, to);
        return Response.ok(List.of()).build();
    }

    @GET
    @Path("/tax/{formCode}")
    public Response taxReport(
            @PathParam("formCode") String formCode,
            @QueryParam("period") String period,
            @QueryParam("orgId") String orgId) {
        log.info("Generating tax report {} for org={} period={}", formCode, orgId, period);
        return Response.ok(Map.of(
            "formCode", formCode,
            "period", period,
            "status", "generated"
        )).build();
    }
}
