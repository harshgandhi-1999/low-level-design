package hard.fooddeliverysystem.models.payment;

public class UPIPaymentStrategy implements PaymentStrategy {


    @Override
    public Payment pay(double amount) {
        return new Payment(amount);
    }
}
