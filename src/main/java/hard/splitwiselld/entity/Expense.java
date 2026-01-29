package hard.splitwiselld.entity;

import hard.splitwiselld.split.SplitStrategy;
import hard.splitwiselld.split.Split;

import java.util.List;

public class Expense {
    private final String id;
    private final double amount;
    private String description;
    private final User paidBy;

    private SplitStrategy splitStrategy;
    private final List<Split> splits;

    public Expense(String id, double amount, String description, User paidBy, SplitStrategy splitStrategy, List<User> usersInvolved) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.paidBy = paidBy;
        this.splitStrategy = splitStrategy;
        this.splits = splitStrategy.calculateSplits(amount, usersInvolved);
    }

    public void addSplit(Split split) {
        splits.add(split);
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }
}
