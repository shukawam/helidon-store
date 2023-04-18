package me.shukawam.product.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import me.shukawam.product.data.ErrorResponse;

@Provider
public class EntityExistExceptionMapper implements ExceptionMapper<EntityExistsException> {

    @Override
    public Response toResponse(EntityExistsException exception) {
        return Response.status(Status.CONFLICT).entity(new ErrorResponse(Status.CONFLICT, exception.getMessage()))
                .type(MediaType.APPLICATION_JSON).build();
    }

}
