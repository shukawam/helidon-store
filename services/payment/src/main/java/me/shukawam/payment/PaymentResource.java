package me.shukawam.payment;

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
import me.shukawam.payment.data.PaymentRequest;
import me.shukawam.payment.data.PaymentResponse;

@Path("payments")
public class PaymentResource {
    private final PaymentService paymentService;

    @Inject
    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPayments() {
        var payments = paymentService.getAllPayments();
        var paymentResponses = new ArrayList<PaymentResponse>();
        payments.forEach(payment -> {
            paymentResponses.add(new PaymentResponse(payment.getId(), payment.getOrderId(), payment.getPaymentMethod(),
                    payment.getAmount(), payment.getPaymentDate()));
        });
        return Response.status(Status.OK).entity(paymentResponses).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentById(@PathParam("id") Integer id) {
        var payment = paymentService.getPaymentById(id);
        return Response.status(Status.OK)
                .entity(new PaymentResponse(payment.getId(), payment.getOrderId(), payment.getPaymentMethod(),
                        payment.getAmount(), payment.getPaymentDate()))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPayment(PaymentRequest paymentRequest) {
        paymentService.createPayment(paymentRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePayment(PaymentRequest paymentRequest) {
        var payment = paymentService.updatePayment(paymentRequest);
        return Response.status(Status.OK)
                .entity(new PaymentResponse(payment.getId(), payment.getOrderId(), payment.getPaymentMethod(),
                        payment.getAmount(), payment.getPaymentDate()))
                .build();
    }

    @DELETE
    @Path("{id}")
    public void deletePayment(@PathParam("id") Integer id) {
        paymentService.deletePayment(id);
    }
}
