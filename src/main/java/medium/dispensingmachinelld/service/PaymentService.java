package medium.dispensingmachinelld.service;

import medium.dispensingmachinelld.entities.PaymentMode;
import medium.dispensingmachinelld.strategy.PaymentResult;
import medium.dispensingmachinelld.strategy.PaymentStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentService {
    private final Map<PaymentMode, PaymentStrategy> strategies;

    public PaymentService(List<PaymentStrategy> strategyList) {
        strategies = new HashMap<>();
        for (PaymentStrategy strategy : strategyList) {
            strategies.put(strategy.getMode(), strategy);
        }
    }

    public PaymentResult makePayment(double amount, PaymentMode mode) {
        PaymentStrategy strategy = strategies.get(mode);

        if (strategy == null) {
            return PaymentResult.failure("Unsupported payment mode");
        }

        return strategy.pay(amount);
    }
}
