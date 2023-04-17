package me.shukawam.customer.exception;

import jakarta.ws.rs.core.Response.Status;

public record ErrorResponse(Status status, String message) {
    
}
