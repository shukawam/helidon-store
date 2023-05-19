package me.shukawam.order;

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
import me.shukawam.order.data.OrderRequest;
import me.shukawam.order.data.OrderResponse;

@Path("orders")
public class OrderResource {

    private OrderService orderService;

    @Inject
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() {
        var orders = orderService.getAllOrders();
        var orderResponses = new ArrayList<OrderResponse>();
        orders.stream().forEach(order -> {
            orderResponses.add(new OrderResponse(order.getId(), order.getCustomerId(), order.getTotalPrice(),
                    order.getOrderDate(), order.getStatus()));
        });
        return Response.status(Status.OK).entity(orderResponses).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") Integer id) {
        var order = orderService.getOrderById(id);
        return Response.status(Status.OK).entity(new OrderResponse(order.getId(), order.getCustomerId(),
                order.getTotalPrice(), order.getOrderDate(), order.getStatus())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createOrder(OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(OrderRequest orderRequest) {
        var order = orderService.updateOrder(orderRequest);
        return Response.status(Status.OK).entity(new OrderResponse(order.getId(), order.getCustomerId(),
                order.getTotalPrice(), order.getOrderDate(), order.getStatus())).build();
    }

    @DELETE
    @Path("{id}")
    public void deleteOrder(@PathParam("id") Integer id) {
        orderService.deleteOrder(id);
    }

}