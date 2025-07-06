package medium.digitalwalletsercicelld.payment;

import medium.digitalwalletsercicelld.User;
import medium.digitalwalletsercicelld.currency.CurrencyType;

import java.math.BigDecimal;

public abstract class PaymentMethod {
    protected final String id;
    protected final User user;

    public PaymentMethod(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public abstract boolean processPayment(BigDecimal amount, CurrencyType currency);

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
