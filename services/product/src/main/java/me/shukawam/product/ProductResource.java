package me.shukawam.product;

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

@Path("products")
public class ProductResource {

    private final ProductService productService;

    @Inject
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("id") Integer id) {
        return productService.getProductById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProduct(Product product) {
        productService.createProduct(product);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Product updateProduct(Product product) {
        return productService.updateProduct(product);
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProduct(@PathParam("id") Integer id) {
        productService.deleteProduct(id);
    }
}
