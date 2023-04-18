package me.shukawam.payment.data;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record PaymentResponse(Integer id, Integer orderId, PaymentMethod paymentMethod, BigDecimal amount,
        Timestamp paymentDate) {

}
