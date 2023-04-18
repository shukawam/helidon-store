package me.shukawam.cart;

import java.util.List;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import me.shukawam.cart.data.CartRequest;

@ApplicationScoped
public class CartService {
    private static final Logger logger = Logger.getLogger(CartService.class.getName());

    @PersistenceContext(unitName = "atp")
    private EntityManager entityManager;

    public List<Cart> getAllCarts() {
        var carts = entityManager.createNamedQuery("getAllCarts", Cart.class).getResultList();
        if (carts.isEmpty()) {
            var message = "Cart is empty.";
            logger.info(message);
            throw new NotFoundException(message);
        } else {
            return carts;
        }

    }

    public Cart getCartById(Integer id) {
        var cart = entityManager.find(Cart.class, id);
        if (cart == null) {
            var message = String.format("Cart: %s is not found.", id);
            logger.info(message);
            throw new NotFoundException(message);
        } else {
            return cart;
        }
    }

    @Transactional
    public void createCart(CartRequest cartRequest) {
        var cart = new Cart();
        cart.setId(cartRequest.id());
        cart.setCustomerId(cartRequest.customerId());
        entityManager.persist(cart);
    }

    @Transactional
    public Cart updateCart(CartRequest cartRequest) {
        var oldCart = getCartById(cartRequest.id());
        oldCart.setId(cartRequest.id());
        oldCart.setCustomerId(cartRequest.customerId());
        var newCart = oldCart;
        var updatedCart = entityManager.merge(newCart);
        return updatedCart;
    }

    @Transactional
    public void deleteCart(Integer id) {
        var cart = getCartById(id);
        entityManager.remove(cart);
    }
}
