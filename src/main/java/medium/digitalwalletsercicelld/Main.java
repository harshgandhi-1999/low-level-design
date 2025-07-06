package medium.digitalwalletsercicelld;

import medium.digitalwalletsercicelld.currency.CurrencyType;
import medium.digitalwalletsercicelld.payment.BankAccountTransfer;
import medium.digitalwalletsercicelld.payment.CreditCard;
import medium.digitalwalletsercicelld.payment.PaymentMethod;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DigitalWalletService digitalWallet = DigitalWalletService.getInstance();

        // Create users
        User user1 = new User("John Doe", "john@example.com", "password123");
        User user2 = new User("Jane Smith" , "jane@example.com", "password456");
        digitalWallet.createUser(user1);
        digitalWallet.createUser(user2);

        // Create accounts
        Account account1 = new Account("A001", user1, "1234567890", CurrencyType.USD);
        Account account2 = new Account("A002", user2, "9876543210", CurrencyType.EUR);
        digitalWallet.createAccount(account1);
        digitalWallet.createAccount(account2);

        // Add payment methods
        PaymentMethod creditCard = new CreditCard("PM001", user1, "1234567890123456", "12/25", "123");
        PaymentMethod bankAccount = new BankAccountTransfer("PM002", user2, "9876543210", "987654321");
        digitalWallet.addPaymentMethod(creditCard);
        digitalWallet.addPaymentMethod(bankAccount);

        // Deposit funds
        account1.depositMoney(new BigDecimal("1000.00"));
        account2.depositMoney(new BigDecimal("500.00"));

        // Transfer funds
        digitalWallet.transferFunds(account1, account2, new BigDecimal("100.00"), CurrencyType.USD);

        // Get transaction history
        List<Transaction> transactionHistory1 = digitalWallet.getTransactionHistory(account1);
        List<Transaction> transactionHistory2 = digitalWallet.getTransactionHistory(account2);

        // Print transaction history
        System.out.println("Transaction History for Account 1:");
        for (Transaction transaction : transactionHistory1) {
            System.out.println("Transaction ID: " + transaction.getId());
            System.out.println("Amount: " + transaction.getAmount() + " " + transaction.getCurrencyType());
            System.out.println("Timestamp: " + transaction.getTimestamp());
            System.out.println();
        }

        System.out.println("Transaction History for Account 2:");
        for (Transaction transaction : transactionHistory2) {
            System.out.println("Transaction ID: " + transaction.getId());
            System.out.println("Amount: " + transaction.getAmount() + " " + transaction.getCurrencyType());
            System.out.println("Timestamp: " + transaction.getTimestamp());
            System.out.println();
        }
    }
}
