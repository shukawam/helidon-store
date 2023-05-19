package me.shukawam.customer;

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
import me.shukawam.customer.data.CustomerRequest;
import me.shukawam.customer.data.CustomerResponse;

@Path("customers")
public class CustomerResource {

    private final CustomerService customerService;

    @Inject
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomer() {
        var customers = customerService.getAllCustomers();
        var customerResponses = new ArrayList<CustomerResponse>();
        customers.stream().forEach(customer -> {
            customerResponses.add(new CustomerResponse(customer.getId(), customer.getFirstName(),
                    customer.getLastName(), customer.getEmail(), customer.getPhoneNumber()));
        });
        return Response.status(Status.OK).entity(customerResponses).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") Integer id) {
        var customer = customerService.getCustomerById(id);
        return Response.status(Status.OK).entity(new CustomerResponse(customer.getId(), customer.getFirstName(),
                customer.getLastName(), customer.getEmail(), customer.getPhoneNumber())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCustomer(CustomerRequest customerRequest) {
        customerService.createCustomer(customerRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(CustomerRequest customerRequest) {
        var customer = customerService.updateCustomer(customerRequest);
        return Response.status(Status.OK).entity(new CustomerResponse(customer.getId(), customer.getFirstName(),
                customer.getLastName(), customer.getEmail(), customer.getPhoneNumber())).build();
    }

    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") Integer id) {
        customerService.deleteCustomer(id);
    }
}
