package me.shukawam.payment.data;

import jakarta.ws.rs.core.Response.Status;

public record ErrorResponse(Status status, String message) {

}
