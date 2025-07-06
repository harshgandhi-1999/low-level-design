package medium.digitalwalletsercicelld;

import medium.digitalwalletsercicelld.currency.CurrencyType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final String id;
    private final Account sourceAccount;
    private final Account destinationAccount;
    private final BigDecimal amount;
    private final CurrencyType currencyType;
    private final LocalDateTime timestamp;

    public Transaction(String id, Account sourceAccount, Account destinationAccount, BigDecimal amount, CurrencyType currencyType) {
        this.id = id;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.currencyType = currencyType;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
