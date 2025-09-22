package medium.socialnetworkapplld.observer;

import medium.socialnetworkapplld.model.Comment;
import medium.socialnetworkapplld.model.Post;
import medium.socialnetworkapplld.model.User;

public class UserNotifier implements PostObserver {

    @Override
    public void onPost(Post post) {
        // notify all the post users's friends
        User author = post.getAuthor();
        for (User friend : author.getFriends()) {
            System.out.println("Notification for " + friend.getName() + ": " + author.getName() + " created a new post: " + post.getContent());
        }
    }

    @Override
    public void onLike(Post post, User user) {
        // user whose post has been liked will get notified
        User author = post.getAuthor();
        System.out.println("Notification for " + author.getName() + ": " + user.getName() + " liked your post");
    }

    @Override
    public void onComment(Post post, Comment comment) {
        User author = post.getAuthor();
        System.out.println("Notification for " + author.getName() + ": " + comment.getAuthor().getName() + " commented on your post");
    }
}
