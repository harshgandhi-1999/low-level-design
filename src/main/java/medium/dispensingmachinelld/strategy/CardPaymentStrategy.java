package medium.dispensingmachinelld.strategy;

import medium.dispensingmachinelld.entities.PaymentMode;

public class CardPaymentStrategy implements PaymentStrategy{

    @Override
    public PaymentMode getMode() {
        return PaymentMode.CARD;
    }

    @Override
    public PaymentResult pay(double amount) {
        return new PaymentResult();
    }
}
