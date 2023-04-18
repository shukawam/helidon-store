package me.shukawam.order.data;

import java.math.BigDecimal;

public record OrderRequest(Integer id, Integer customerId, BigDecimal totalPrice) {

}
