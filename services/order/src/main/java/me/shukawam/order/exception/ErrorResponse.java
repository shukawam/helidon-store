package me.shukawam.order.exception;

import jakarta.ws.rs.core.Response.Status;

public record ErrorResponse(Status status, String message) {

}
