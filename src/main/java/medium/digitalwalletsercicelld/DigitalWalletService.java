package medium.digitalwalletsercicelld;

import medium.digitalwalletsercicelld.currency.CurrencyConverter;
import medium.digitalwalletsercicelld.currency.CurrencyType;
import medium.digitalwalletsercicelld.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DigitalWalletService {

    private static DigitalWalletService INSTANCE;
    private Map<String, User> users;
    private Map<String, Account> accounts;
    private Map<String, PaymentMethod> paymentMethods;

    private DigitalWalletService(){
        users = new ConcurrentHashMap<>();
        accounts = new ConcurrentHashMap<>();
        paymentMethods = new ConcurrentHashMap<>();
    }


    public static DigitalWalletService getInstance(){
        if(INSTANCE == null){
            synchronized (DigitalWalletService.class){
                if(INSTANCE == null){
                    INSTANCE = new DigitalWalletService();
                }
            }
        }
        return INSTANCE;
    }

    public void createUser(User user) {
        users.putIfAbsent(user.getId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public void createAccount(Account account) {
        accounts.put(account.getId(), account);
        account.getUser().addAccount(account);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.put(paymentMethod.getId(), paymentMethod);
    }

    public PaymentMethod getPaymentMethod(String paymentMethodId) {
        return paymentMethods.get(paymentMethodId);
    }

    public synchronized void transferFunds(Account sourceAccount, Account destAccount, BigDecimal amount, CurrencyType currencyType){
        // currencyType is the currency of the amount to be transferred
        if(sourceAccount.getCurrency() != currencyType){
            amount = CurrencyConverter.convert(amount, currencyType, sourceAccount.getCurrency());
        }

        sourceAccount.withdrawMoney(amount);

        if (destAccount.getCurrency() != currencyType) {
            amount = CurrencyConverter.convert(amount, currencyType, destAccount.getCurrency());
        }
        destAccount.depositMoney(amount);

        String transactionId = generateTransactionId();
        Transaction transaction = new Transaction(transactionId,sourceAccount,destAccount,amount,destAccount.getCurrency());
        sourceAccount.addTransaction(transaction);
        destAccount.addTransaction(transaction);
    }


    public List<Transaction> getTransactionHistory(Account account) {
        return account.getTransactions();
    }


    private String generateTransactionId() {
        return "TXN_" + UUID.randomUUID().toString();
    }

}
