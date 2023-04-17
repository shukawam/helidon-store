package me.shukawam.customer;

import java.util.List;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import me.shukawam.customer.data.CustomerRequest;

@ApplicationScoped
public class CustomerService {
    private static final Logger logger = Logger.getLogger(CustomerService.class.getName());

    @PersistenceContext(unitName = "atp")
    private EntityManager entityManager;

    public List<Customer> getAllCustomers() {
        var customers = entityManager.createNamedQuery("getAllCustomer", Customer.class).getResultList();
        if (customers.isEmpty()) {
            var message = "Customer is empty.";
            logger.info(message);
            throw new NotFoundException(message);
        } else {
            return customers;
        }
    }

    public Customer getCustomerById(Integer id) {
        var customer = entityManager.find(Customer.class, id);
        if (customer == null) {
            var message = String.format("Customer: %s is not found.", id);
            logger.info(message);
            throw new NotFoundException(message);
        } else {
            return customer;
        }
    }

    @Transactional
    public void createCustomer(CustomerRequest customerRequest) {
        var customer = new Customer();
        customer.setId(customerRequest.id());
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer.setEmail(customerRequest.email());
        customer.setPhoneNumber(customerRequest.phoneNumber());
        entityManager.persist(customer);
    }

    @Transactional
    public Customer updateCustomer(CustomerRequest customerRequest) {
        var oldCustomer = getCustomerById(customerRequest.id());
        var newCustomer = updateCustomerDetails(customerRequest, oldCustomer);
        var updatedCustomer = entityManager.merge(newCustomer);
        logger.info(updatedCustomer.toString());
        return updatedCustomer;
    }

    @Transactional
    public void deleteCustomer(Integer id) {
        var customer = getCustomerById(id);
        entityManager.remove(customer);
        logger.info(String.format("Delete product: %s is completed.", id));
    }

    private Customer updateCustomerDetails(CustomerRequest customerRequest, Customer oldCustomer) {
        // FIXME: Using BeanUrils, etc.
        oldCustomer.setId(customerRequest.id());
        oldCustomer.setFirstName(customerRequest.firstName());
        oldCustomer.setLastName(customerRequest.lastName());
        oldCustomer.setEmail(customerRequest.email());
        oldCustomer.setPhoneNumber(customerRequest.phoneNumber());
        var updateCustomer = oldCustomer;
        return updateCustomer;
    }
}
