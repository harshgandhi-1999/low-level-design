package hard.fooddeliverysystem.models.payment;

import java.util.UUID;

public class Payment {

    private final String id;
    private final double amount;
    private PaymentStatus paymentStatus;
    private final String transactionId;


    public Payment(double amount) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.paymentStatus = PaymentStatus.PENDING;
        this.transactionId = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
