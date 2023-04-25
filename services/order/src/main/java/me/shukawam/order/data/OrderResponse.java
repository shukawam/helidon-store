package me.shukawam.order.data;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record OrderResponse(Integer id, Integer customerId, BigDecimal totalPrice, Timestamp orderDate,
        OrderStatus status) {

}
