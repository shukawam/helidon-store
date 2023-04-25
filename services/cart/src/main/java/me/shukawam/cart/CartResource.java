package me.shukawam.cart;

import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import me.shukawam.cart.data.CartRequest;
import me.shukawam.cart.data.CartResponse;

@Path("carts")
public class CartResource {
    private final CartService cartService;

    @Inject
    public CartResource(CartService cartService) {
        this.cartService = cartService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCarts() {
        var carts = cartService.getAllCarts();
        var cartResponses = new ArrayList<CartResponse>();
        carts.forEach(cart -> {
            cartResponses.add(new CartResponse(cart.getId(), cart.getCustomerId(), cart.getCreatedAt()));
        });
        return Response.status(Status.OK).entity(cartResponses).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCartById(@PathParam("id") Integer id) {
        var cart = cartService.getCartById(id);
        return Response.status(Status.OK)
                .entity(new CartResponse(cart.getId(), cart.getCustomerId(), cart.getCreatedAt())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCart(CartRequest cartRequest) {
        cartService.createCart(cartRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCart(CartRequest cartRequest) {
        var cart = cartService.updateCart(cartRequest);
        return Response.status(Status.OK)
                .entity(new CartResponse(cart.getId(), cart.getCustomerId(), cart.getCreatedAt())).build();
    }

    @DELETE
    @Path("{id}")
    public void deleteCart(@PathParam("id") Integer id) {
        cartService.deleteCart(id);
    }

}
