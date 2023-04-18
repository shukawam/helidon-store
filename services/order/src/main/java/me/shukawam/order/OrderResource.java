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
    public List<OrderResponse> getAllOrders() {
        var orders = orderService.getAllOrders();
        var orderResponses = new ArrayList<OrderResponse>();
        orders.stream().forEach(order -> {
            orderResponses.add(new OrderResponse(order.getId(), order.getCustomerId(), order.getTotalPrice(), order.getOrderDate(), order.getStatus()));
        });
        return orderResponses;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderResponse getOrderById(@PathParam("id") Integer id) {
        var order = orderService.getOrderById(id);
        return new OrderResponse(order.getId(), order.getCustomerId(), order.getTotalPrice(), order.getOrderDate(), order.getStatus());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createOrder(OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public OrderResponse updateOrder(OrderRequest orderRequest) {
        var order = orderService.updateOrder(orderRequest);
        return new OrderResponse(order.getId(), order.getCustomerId(), order.getTotalPrice(), order.getOrderDate(), order.getStatus());
    }

    @DELETE
    @Path("{id}")
    public void deleteOrder(@PathParam("id") Integer id) {
        orderService.deleteOrder(id);
    }
    
}