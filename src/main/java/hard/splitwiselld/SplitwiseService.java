package hard.splitwiselld;

import hard.splitwiselld.entity.Expense;
import hard.splitwiselld.entity.Group;
import hard.splitwiselld.entity.Transaction;
import hard.splitwiselld.entity.User;
import hard.splitwiselld.split.Split;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SplitwiseService {

    private static SplitwiseService INSTANCE;
    private final Map<String, User> users;
    private final Map<String, Group> groups;
    // Per-group balances: groupId -> ("user1Id:user2Id" -> balance)
    // Positive value: user2 owes amount to user1 (user1 expects to receive)
    // Negative value: user1 owes amount to user2 (user1 needs to pay)
    private final Map<String, Map<String, Double>> groupBalances;

    private static final String TRANSACTION_ID_PREFIX = "TXN";
    private static final AtomicInteger transactionCounter = new AtomicInteger(0);

    public static SplitwiseService getInstance(){
        if (INSTANCE == null){
            synchronized (SplitwiseService.class){
                if(INSTANCE == null){
                    INSTANCE = new SplitwiseService();
                }
            }
        }

        return INSTANCE;
    }

    private SplitwiseService(){
        this.users = new ConcurrentHashMap<>();
        this.groups = new ConcurrentHashMap<>();
        this.groupBalances = new ConcurrentHashMap<>();
    }

    public void createUser(User user){
        users.put(user.getId(), user);
    }

    public void createGroup(Group group){
        groups.put(group.getId(), group);
        groupBalances.put(group.getId(), new ConcurrentHashMap<>());
    }

    // Add a group-scoped expense; updates the group's balances only
    public void addExpense(String groupId, Expense expense){
        if(!groups.containsKey(groupId)){
            throw new IllegalArgumentException("No such group present");
        }

        // 1. add the expense to group
        // 2. update group balances only
        groups.get(groupId).addExpense(expense);
        updateGroupBalances(groupId, expense);
    }

    // Add a personal/non-group expense; updates the user's global/personal balances only
    public void addPersonalExpense(Expense expense){
        updatePersonalBalances(expense);
    }

    // PERSONAL (non-group) balances live on the User object
    private void updatePersonalBalances(Expense expense) {
        User paidBy = expense.getPaidBy();
        for (Split split : expense.getSplits()) {
            User user = split.getUser();
            double amount = split.getAmount();

            if (!paidBy.equals(user)) {
                // user owes amount to paidBy
                updatePersonalBalance(paidBy, user, amount); // amount that paidBy will receive
                updatePersonalBalance(user, paidBy, -amount); // amount that user will give
            }
        }
    }

    // GROUP balances live in groupBalances per groupId
    private void updateGroupBalances(String groupId, Expense expense) {
        User paidBy = expense.getPaidBy();
        for (Split split : expense.getSplits()) {
            User user = split.getUser();
            double amount = split.getAmount();

            if (!paidBy.equals(user)) {
                // user owes amount to paidBy
                updateGroupBalance(groupId, paidBy, user, amount);   // amount that paidBy will receive
                updateGroupBalance(groupId, user, paidBy, -amount);   // amount that user will give
            }
        }
    }

    private void updatePersonalBalance(User user1, User user2, double amount) {
        String key = getBalanceKey(user1, user2);
        user1.getBalances().put(key, user1.getBalances().getOrDefault(key, 0.0) + amount);
    }

    private void updateGroupBalance(String groupId, User user1, User user2, double amount) {
        Map<String, Double> gb = groupBalances.get(groupId);
        if (gb == null) {
            throw new IllegalStateException("Group balances not initialized for group: " + groupId);
        }
        String key = getBalanceKey(user1, user2);
        gb.put(key, gb.getOrDefault(key, 0.0) + amount);
    }

    private String getBalanceKey(User user1, User user2) {
        return user1.getId() + ":" + user2.getId();
    }

    // Personal/global settlement between two users (non-group)
    public void settleBalance(String userId1, String userId2) {
        User user1 = users.get(userId1);
        User user2 = users.get(userId2);

        if (user1 != null && user2 != null) {
            String key = getBalanceKey(user1, user2);
            double balance = user1.getBalances().getOrDefault(key, 0.0);

            if (balance > 0) {
                createTransaction(user2, user1, balance);
                user1.getBalances().put(key, 0.0);
                user2.getBalances().put(getBalanceKey(user2, user1), 0.0);
            } else if (balance < 0) {
                createTransaction(user1, user2, Math.abs(balance));
                user1.getBalances().put(key, 0.0);
                user2.getBalances().put(getBalanceKey(user2, user1), 0.0);
            }
        }
    }

    // Group-scoped settlement between two users inside a group
    public void settleGroupBalance(String groupId, String userId1, String userId2) {
        if (!groups.containsKey(groupId)) {
            throw new IllegalArgumentException("No such group present");
        }
        Map<String, Double> gb = groupBalances.get(groupId);
        if (gb == null) {
            throw new IllegalStateException("Group balances not initialized for group: " + groupId);
        }

        User user1 = users.get(userId1);
        User user2 = users.get(userId2);
        if (user1 == null || user2 == null) return;

        String key = getBalanceKey(user1, user2);
        String reverseKey = getBalanceKey(user2, user1);
        double balance = gb.getOrDefault(key, 0.0);

        if (balance > 0) {
            createTransaction(user2, user1, balance);
            gb.put(key, 0.0);
            gb.put(reverseKey, 0.0);
        } else if (balance < 0) {
            createTransaction(user1, user2, Math.abs(balance));
            gb.put(key, 0.0);
            gb.put(reverseKey, 0.0);
        }
    }

    // Convenience queries
    public Map<String, Double> getPersonalBalancesForUser(String userId) {
        User u = users.get(userId);
        if (u == null) return Collections.emptyMap();
        return Collections.unmodifiableMap(u.getBalances());
    }

    public Map<String, Double> getGroupBalances(String groupId) {
        Map<String, Double> gb = groupBalances.get(groupId);
        if (gb == null) return Collections.emptyMap();
        return Collections.unmodifiableMap(gb);
    }

    private void createTransaction(User sender, User receiver, double amount) {
        String transactionId = generateTransactionId();
        Transaction transaction = new Transaction(transactionId, sender, receiver, amount);
        System.out.println("Transaction completed: " + sender.getName() + " transferred " + amount + " to " + receiver.getName());
        // Process the transaction
        // ...
    }

    private String generateTransactionId() {
        int transactionNumber = transactionCounter.incrementAndGet();
        return TRANSACTION_ID_PREFIX + String.format("%06d", transactionNumber);
    }
}
