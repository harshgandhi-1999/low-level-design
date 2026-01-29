package hard.splitwiselld.split;

import hard.splitwiselld.entity.User;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy{

    @Override
    public List<Split> calculateSplits(double totalAmount, List<User> users) {
        List<Split> splits = new ArrayList<>();

        double splitAmount = totalAmount / (double)users.size();
        for(User user : users){
            Split split = new Split(user, splitAmount);
            splits.add(split);
        }

        return splits;
    }
}
