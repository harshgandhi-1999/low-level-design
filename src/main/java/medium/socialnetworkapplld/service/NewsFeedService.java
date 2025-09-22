package medium.socialnetworkapplld.service;

import medium.socialnetworkapplld.model.Post;
import medium.socialnetworkapplld.model.User;
import medium.socialnetworkapplld.strategy.ChronologicalStrategy;
import medium.socialnetworkapplld.strategy.NewsFeedGenerationStrategy;

import java.util.List;

public class NewsFeedService {

    private NewsFeedGenerationStrategy strategy;

    public NewsFeedService() {
        this.strategy = new ChronologicalStrategy(); // Default strategy
    }

    public void setStrategy(NewsFeedGenerationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Post> getNewsFeed(User user) {
        return strategy.generateFeed(user);
    }
}
