package me.shukawam.payment.data;

import java.math.BigDecimal;

public record PaymentRequest(Integer id, Integer orderId, PaymentMethod paymentMethod, BigDecimal amount) {

}
