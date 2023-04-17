package me.shukawam.product.exception;

import jakarta.ws.rs.core.Response.StatusType;

public class ErrorResponse {
    private StatusType status;
    private String message;

    public ErrorResponse(StatusType status, String message) {
        this.status = status;
        this.message = message;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
