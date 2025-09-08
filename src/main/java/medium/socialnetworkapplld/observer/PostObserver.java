package medium.socialnetworkapplld.observer;

import medium.socialnetworkapplld.model.Comment;
import medium.socialnetworkapplld.model.Post;
import medium.socialnetworkapplld.model.User;

public interface PostObserver {
    void onPost(Post post);
    void onLike(Post post, User user);
    void onComment(Post post, Comment comment);
}
