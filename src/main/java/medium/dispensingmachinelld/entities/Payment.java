package medium.dispensingmachinelld.entities;

public class Payment {
    private final String id;
    private Order order;
    private double amount;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;

    public Payment(String id, Order order, double amount, PaymentMode paymentMode) {
        this.id = id;
        this.order = order;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.paymentStatus = PaymentStatus.PENDING;
    }
}
