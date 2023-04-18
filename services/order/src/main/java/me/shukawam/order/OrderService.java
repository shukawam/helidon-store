package me.shukawam.order;

import java.util.List;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import me.shukawam.order.data.OrderRequest;

@ApplicationScoped
public class OrderService {
    
    private static final Logger logger = Logger.getLogger(OrderService.class.getName());

    @PersistenceContext(unitName = "atp")
    private EntityManager entityManager;

    public List<Order> getAllOrders() {
        var orders = entityManager.createNamedQuery("getAllOrders", Order.class).getResultList();
        if (orders.isEmpty()) {
            var message = "Order is empty.";
            logger.info(message);
            throw new NotFoundException(message);
        } else {
            return orders;
        }
    }

    public Order getOrderById(Integer id) {
        var order = entityManager.find(Order.class, id);
        if (order == null) {
            var message = String.format("Order: %s is not found.", id);
            logger.info(message);
            throw new NotFoundException(message);
        } else {
            return order;
        }
    }

    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        var order = new Order();
        order.setId(orderRequest.id());
        order.setCustomerId(orderRequest.customerId());
        order.setTotalPrice(orderRequest.totalPrice());
        entityManager.persist(order);
    }

    @Transactional
    public Order updateOrder(OrderRequest orderRequest) {
        var oldOrder = getOrderById(orderRequest.id());
        oldOrder.setId(orderRequest.id());
        oldOrder.setCustomerId(orderRequest.customerId());
        oldOrder.setTotalPrice(orderRequest.totalPrice());
        var newOrder = oldOrder;
        var updatedOrder = entityManager.merge(newOrder);
        logger.info(updatedOrder.toString());
        return updatedOrder;
    }

    @Transactional
    public void deleteOrder(Integer id) {
        var order = getOrderById(id);
        entityManager.remove(order);
        logger.info(String.format("Delete order: %s is completed.", id));
    }
    
}
