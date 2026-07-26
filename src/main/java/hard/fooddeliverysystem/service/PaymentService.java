package hard.fooddeliverysystem.service;

import hard.fooddeliverysystem.models.payment.Payment;
import hard.fooddeliverysystem.models.payment.PaymentStrategy;

public class PaymentService {


    public static volatile PaymentService INSTANCE = null;

    private PaymentService(){
    }


    public static PaymentService getInstance(){
        if(INSTANCE == null){
            synchronized (PaymentService.class){
                if(INSTANCE == null){
                    INSTANCE = new PaymentService();
                }
            }
        }

        return INSTANCE;
    }

    public Payment pay(double amount, PaymentStrategy paymentStrategy){
        return paymentStrategy.pay(amount);
    }

}
