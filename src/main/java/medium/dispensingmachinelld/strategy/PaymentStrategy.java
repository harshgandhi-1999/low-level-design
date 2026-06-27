package medium.dispensingmachinelld.strategy;

import medium.dispensingmachinelld.entities.PaymentMode;

public interface PaymentStrategy {

    PaymentMode getMode();
    PaymentResult pay(double amount);
}
