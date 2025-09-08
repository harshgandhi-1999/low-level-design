package medium.socialnetworkapplld.strategy;

import medium.socialnetworkapplld.model.Post;
import medium.socialnetworkapplld.model.User;

import java.util.List;

public interface NewsFeedGenerationStrategy {
    List<Post> generateFeed(User user);
}
