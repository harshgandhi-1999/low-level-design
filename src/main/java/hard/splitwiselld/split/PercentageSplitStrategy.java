package splitwise.split;

import splitwise.User;
import splitwise.splittype.Split;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy{
    Map<User, Double> userToPercentage;

    public PercentageSplitStrategy(Map<User, Double> userToPercentage) {
        this.userToPercentage = userToPercentage;
    }

    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> users) {
        List<Split> splits = new ArrayList<>();
        for (User user : users) {
            double percent = userToPercentage.get(user);
            double splitAmount = (percent / 100.0) * totalAmount;
            Split split = new Split(user, splitAmount);
            splits.add(split);
        }
        return splits;
    }
}
