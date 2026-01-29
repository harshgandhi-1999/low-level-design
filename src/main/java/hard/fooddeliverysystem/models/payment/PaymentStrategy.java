package hard.fooddeliverysystem.models.payment;

public interface PaymentStrategy {

    Payment pay(double amount);
}
