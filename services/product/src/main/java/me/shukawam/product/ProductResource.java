package me.shukawam.product;

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
import me.shukawam.product.data.ProductRequest;
import me.shukawam.product.data.ProductResponse;

@Path("products")
public class ProductResource {

    private final ProductService productService;

    @Inject
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductResponse> getAllProducts() {
        var products = productService.getAllProducts();
        var productResponses = new ArrayList<ProductResponse>();
        products.stream().forEach(product -> {
            productResponses.add(new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                    product.getPrice(), product.getQuantity()));
        });
        return productResponses;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductResponse getProductById(@PathParam("id") Integer id) {
        var product = productService.getProductById(id);
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                product.getQuantity());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProduct(Product product) {
        productService.createProduct(product);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ProductResponse updateProduct(ProductRequest productRequest) {
        var product = productService.updateProduct(productRequest);
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                product.getQuantity());
    }

    @DELETE
    @Path("{id}")
    public void deleteProduct(@PathParam("id") Integer id) {
        productService.deleteProduct(id);
    }
}
