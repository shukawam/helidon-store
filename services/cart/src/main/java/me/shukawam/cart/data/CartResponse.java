package me.shukawam.cart.data;

import java.sql.Timestamp;

public record CartResponse(Integer id, Integer customerId, Timestamp createdAt) {

}
