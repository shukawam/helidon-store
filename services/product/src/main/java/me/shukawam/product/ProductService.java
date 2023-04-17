package me.shukawam.product;

import java.util.List;
import java.util.logging.Logger;

import io.helidon.common.http.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductService {
    private static final Logger logger = Logger.getLogger(ProductService.class.getName());

    @PersistenceContext(unitName = "atp")
    private EntityManager entityManager;

    public List<Product> getAllProducts() {
        var products = entityManager.createNamedQuery("getAllProduct", Product.class).getResultList();
        if (products.isEmpty()) {
            var message = "Product is empty.";
            logger.info(message);
            throw new NotFoundException(message);
        } else {
            return products;
        }
    }

    public Product getProductById(Integer id) {
        var product = entityManager.find(Product.class, id);
        if (product == null) {
            var message = String.format("Product: %s is not found.", id);
            logger.info(message);
            throw new NotFoundException(String.format(message));
        } else {
            return product;
        }
    }

    @Transactional
    public void createProduct(Product product) {
        entityManager.persist(product);
    }

    @Transactional
    public Product updateProduct(Product product) {
        var updatedProduct = entityManager.merge(product);
        logger.info(updatedProduct.toString());
        return updatedProduct;
    }

    @Transactional
    public void deleteProduct(Integer id) {
        var product = getProductById(id);
        entityManager.remove(product);
        logger.info(String.format("Delete product: %s is completed.", id));
    }

}
