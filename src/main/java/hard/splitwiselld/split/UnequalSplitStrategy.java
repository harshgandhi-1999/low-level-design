package hard.splitwiselld.split;

import hard.splitwiselld.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnequalSplitStrategy implements SplitStrategy{

    Map<User, Double> userToAmount;

    public UnequalSplitStrategy(Map<User, Double> userToAmount) {
        this.userToAmount = userToAmount;
    }

    @Override
    public List<Split> calculateSplits(double amount, List<User> users) {
        List<Split> splits = new ArrayList<>();

        for(User user : users){
            double splitAmount = userToAmount.get(user);
            Split split = new Split(user, splitAmount);
            splits.add(split);
        }

        return splits;
    }
}
