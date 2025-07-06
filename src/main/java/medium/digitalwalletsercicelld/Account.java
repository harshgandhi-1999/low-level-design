package medium.digitalwalletsercicelld;

import medium.digitalwalletsercicelld.currency.CurrencyType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String id;
    private final User user;
    private final String accountNumber;
    private final CurrencyType currency;  // currency of the account
    private BigDecimal balance;
    private final List<Transaction> transactions;

    public Account(String id, User user, String accountNumber, CurrencyType currency) {
        this.id = id;
        this.user = user;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
    }

    public synchronized void withdrawMoney(BigDecimal amount){
        if(balance.compareTo(amount) < 0){
            throw new RuntimeException("Insufficient funds in your account");
        }

        balance = balance.subtract(amount);
    }

    public synchronized void depositMoney(BigDecimal amount){
        balance = balance.add(amount);
    }

    public synchronized void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }


    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
