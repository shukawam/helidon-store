package me.shukawam.cart;

import java.sql.Timestamp;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity(name = "Carts")
@Table(name = "carts")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "getAllCarts", query = "SELECT c FROM Carts c") })
public class Cart {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "created_at")
    private Timestamp createdAt;

    @PrePersist
    public void prePersist() {
        var now = new Timestamp(System.currentTimeMillis());
        setCreatedAt(now);
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Cart [id=" + id + ", customerId=" + customerId + ", createdAt=" + createdAt + "]";
    }

}
