package medium.digitalwalletsercicelld.payment;

import medium.digitalwalletsercicelld.User;
import medium.digitalwalletsercicelld.currency.CurrencyType;

import java.math.BigDecimal;

public class BankAccountTransfer extends PaymentMethod{

    private final String accountNumber;
    private final String routingNumber;

    public BankAccountTransfer(String id, User user, String accountNumber, String routingNumber) {
        super(id, user);
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
    }

    @Override
    public boolean processPayment(BigDecimal amount, CurrencyType currency) {
        // Process bank account payment
        return true;
    }
}
