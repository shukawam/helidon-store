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
    public List<CustomerResponse> getAllCustomer() {
        var customers = customerService.getAllCustomers();
        var customerResponses = new ArrayList<CustomerResponse>();
        customers.stream().forEach(customer -> {
            customerResponses.add(new CustomerResponse(customer.getId(), customer.getFirstName(),
                    customer.getLastName(), customer.getEmail(), customer.getPhoneNumber()));
        });
        return customerResponses;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerResponse getCustomerById(@PathParam("id") Integer id) {
        var customer = customerService.getCustomerById(id);
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhoneNumber());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCustomer(CustomerRequest customerRequest) {
        customerService.createCustomer(customerRequest);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        var customer = customerService.updateCustomer(customerRequest);
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhoneNumber());
    }

    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") Integer id) {
        customerService.deleteCustomer(id);
    }
}
