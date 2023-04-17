package me.shukawam.product.data;

import java.math.BigDecimal;

public record ProductRequest(Integer id, String name, String description, BigDecimal price, Integer quantity) {

}
