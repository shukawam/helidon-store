package me.shukawam.order;

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
import jakarta.persistence.Table;
import me.shukawam.order.data.OrderStatus;

@Entity(name = "Orders")
@Table(name = "orders")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "getAllOrders", query = "SELECT o FROM Orders o") })
public class Order {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "order_date")
    private Timestamp orderDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @PrePersist
    public void prePersist() {
        var now = new Timestamp(System.currentTimeMillis());
        setOrderDate(now);
        setStatus(OrderStatus.processing);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", customerId=" + customerId + ", totalPrice=" + totalPrice + ", orderDate="
                + orderDate + ", status=" + status + "]";
    }

}