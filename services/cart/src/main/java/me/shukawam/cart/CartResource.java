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
    public List<CartResponse> getAllCarts() {
        var carts = cartService.getAllCarts();
        var cartResponses = new ArrayList<CartResponse>();
        carts.forEach(cart -> {
            cartResponses.add(new CartResponse(cart.getId(), cart.getCustomerId(), cart.getCreatedAt()));
        });
        return cartResponses;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CartResponse getCartById(@PathParam("id") Integer id) {
        var cart = cartService.getCartById(id);
        return new CartResponse(cart.getId(), cart.getCustomerId(), cart.getCreatedAt());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCart(CartRequest cartRequest) {
        cartService.createCart(cartRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CartResponse updateCart(CartRequest cartRequest) {
        var cart = cartService.updateCart(cartRequest);
        return new CartResponse(cart.getId(), cart.getCustomerId(), cart.getCreatedAt());
    }

    @DELETE
    @Path("{id}")
    public void deleteCart(@PathParam("id") Integer id) {
        cartService.deleteCart(id);
    }

}
