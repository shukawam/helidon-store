package me.shukawam.payment;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import me.shukawam.payment.data.PaymentMethod;

@Entity(name = "Payments")
@Table(name = "payments")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "getAllPayments", query = "SELECT p FROM Payments p") })
public class Payment {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_id")
    private Integer orderId;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "payment_date")
    private Timestamp paymentDate;

    @PrePersist
    public void prePersist() {
        var now = new Timestamp(System.currentTimeMillis());
        setPaymentDate(now);
    }

    @PreUpdate
    public void preUpdate() {
        var now = new Timestamp(System.currentTimeMillis());
        setPaymentDate(now);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment [id=" + id + ", orderId=" + orderId + ", paymentMethod=" + paymentMethod + ", amount=" + amount
                + ", paymentDate=" + paymentDate + "]";
    }

}
