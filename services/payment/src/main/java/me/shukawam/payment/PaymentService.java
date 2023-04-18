package me.shukawam.payment;

import java.util.List;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import me.shukawam.payment.data.PaymentRequest;

@ApplicationScoped
public class PaymentService {
    private static final Logger logger = Logger.getLogger(PaymentService.class.getName());

    @PersistenceContext(unitName = "atp")
    private EntityManager entityManager;

    public List<Payment> getAllPayments() {
        var payments = entityManager.createNamedQuery("getAllPayments", Payment.class).getResultList();
        if (payments.isEmpty()) {
            var message = "Payment is empty.";
            logger.info(message);
            throw new NotFoundException(message);
        } else {
            return payments;
        }
    }

    public Payment getPaymentById(Integer id) {
        var payment = entityManager.find(Payment.class, id);
        if (payment == null) {
            var message = String.format("Payment: %s is not found.", id);
            logger.info(message);
            throw new NotFoundException(message);
        } else {
            return payment;
        }
    }

    @Transactional
    public void createPayment(PaymentRequest paymentRequest) {
        var payment = new Payment();
        payment.setId(paymentRequest.id());
        payment.setOrderId(paymentRequest.orderId());
        payment.setPaymentMethod(paymentRequest.paymentMethod());
        payment.setAmount(paymentRequest.amount());
        entityManager.persist(payment);
    }

    @Transactional
    public Payment updatePayment(PaymentRequest paymentRequest) {
        var oldPayment = getPaymentById(paymentRequest.id());
        oldPayment.setId(paymentRequest.id());
        oldPayment.setOrderId(paymentRequest.orderId());
        oldPayment.setPaymentMethod(paymentRequest.paymentMethod());
        oldPayment.setAmount(paymentRequest.amount());
        var newPayment = oldPayment;
        var updatedPayment = entityManager.merge(newPayment);
        logger.info(updatedPayment.toString());
        return updatedPayment;
    }

    @Transactional
    public void deletePayment(Integer id) {
        var payment = getPaymentById(id);
        entityManager.remove(payment);
    }

}
