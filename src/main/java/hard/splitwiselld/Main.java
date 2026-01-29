package hard.splitwiselld;

import hard.splitwiselld.entity.Expense;
import hard.splitwiselld.entity.Group;
import hard.splitwiselld.entity.User;
import hard.splitwiselld.split.EqualSplitStrategy;
import hard.splitwiselld.split.SplitStrategy;

import java.util.Arrays;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

//        Requirements
//        1. The system should allow users to create accounts and manage their profile information.
//        2. Users should be able to create groups and add other users to the groups.
//        3. Users should be able to add expenses within a group, specifying the amount, description, and participants.
//        4. The system should automatically split the expenses among the participants based on their share.
//        5. Users should be able to view their individual balances with other users and settle up the balances.
//        6. The system should support different split methods, such as equal split, percentage split, and exact amounts.
//        7. Users should be able to view their transaction history and group expenses.
//        8. The system should handle concurrent transactions and ensure data consistency.

        SplitwiseService splitwiseService = SplitwiseService.getInstance();

        // Create users
        User user1 = new User("1", "Alice", "alice@example.com");
        User user2 = new User("2", "Bob", "bob@example.com");
        User user3 = new User("3", "Charlie", "charlie@example.com");

        splitwiseService.createUser(user1);
        splitwiseService.createUser(user2);
        splitwiseService.createUser(user3);

        // Create a group
        Group group = new Group("1", "Apartment");
        group.addMember(user1);
        group.addMember(user2);
        group.addMember(user3);

        splitwiseService.createGroup(group);

        SplitStrategy equalSplitStrategy = new EqualSplitStrategy();

        // Add an expense
        Expense expense = new Expense("1", 300.0, "Rent", user1, equalSplitStrategy, group.getMembers());

        splitwiseService.addExpense(group.getId(), expense);

        // Settle balances
        splitwiseService.settleBalance(user1.getId(), user2.getId());
        splitwiseService.settleBalance(user1.getId(), user3.getId());

        // Print user balances
        for (User user : Arrays.asList(user1, user2, user3)) {
            System.out.println("User: " + user.getName());
            for (Map.Entry<String, Double> entry : user.getBalances().entrySet()) {
                System.out.println("  Balance with " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
