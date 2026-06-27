package medium.dispensingmachinelld.strategy;

public class PaymentResult {

    boolean success;
    double paidAmount;
    double change;
    String failureReason;

    public static PaymentResult success(double paid, double change) {
        PaymentResult r = new PaymentResult();
        r.success = true;
        r.paidAmount = paid;
        r.change = change;
        return r;
    }

    public static PaymentResult failure(String reason) {
        PaymentResult r = new PaymentResult();
        r.success = false;
        r.failureReason = reason;
        return r;
    }
}
