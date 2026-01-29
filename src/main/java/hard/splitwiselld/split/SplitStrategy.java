package hard.splitwiselld.split;

import hard.splitwiselld.entity.User;

import java.util.List;

public interface SplitStrategy {
    List<Split> calculateSplits(double totalAmount, List<User> users);
}
