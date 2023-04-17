package me.shukawam.product.exception;

import io.helidon.common.http.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(Status.NOT_FOUND).entity(new ErrorResponse(Status.NOT_FOUND, exception.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }

}