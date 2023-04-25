package me.shukawam.product;

import java.util.ArrayList;

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
    public Response getAllProducts() {
        var products = productService.getAllProducts();
        var productResponses = new ArrayList<ProductResponse>();
        products.stream().forEach(product -> {
            productResponses.add(new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                    product.getPrice(), product.getQuantity()));
        });
        return Response.status(Status.OK).entity(productResponses).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") Integer id) {
        var product = productService.getProductById(id);
        return Response.status(Status.OK).entity(new ProductResponse(product.getId(), product.getName(),
                product.getDescription(), product.getPrice(), product.getQuantity())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProduct(Product product) {
        productService.createProduct(product);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(ProductRequest productRequest) {
        var product = productService.updateProduct(productRequest);
        return Response.status(Status.OK).entity(new ProductResponse(product.getId(), product.getName(),
                product.getDescription(), product.getPrice(), product.getQuantity())).build();
    }

    @DELETE
    @Path("{id}")
    public void deleteProduct(@PathParam("id") Integer id) {
        productService.deleteProduct(id);
    }
}
