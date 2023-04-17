package me.shukawam.product.data;

import java.math.BigDecimal;

public record ProductResponse(Integer id, String name, String description, BigDecimal price, Integer quantity) {

}
