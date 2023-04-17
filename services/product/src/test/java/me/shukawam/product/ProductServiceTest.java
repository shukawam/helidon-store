package me.shukawam.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;

@HelidonTest
public class ProductServiceTest {

    @Inject
    private ProductService productService;

    @Test
    void testGetAllProduct() {
        var expected = 5;
        var products = productService.getAllProducts();
        assertEquals(expected, products.size());
    }

}
