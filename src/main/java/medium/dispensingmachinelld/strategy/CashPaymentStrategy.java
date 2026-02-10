package medium.dispensingmachinelld.strategy;

import medium.dispensingmachinelld.entities.PaymentMode;

public class CashPaymentStrategy implements PaymentStrategy{

    @Override
    public PaymentMode getMode() {
        return PaymentMode.CASH;
    }

    @Override
    public PaymentResult pay(double amount) {
        return new PaymentResult();
    }
}
