package medium.socialnetworkapplld.service;

import medium.socialnetworkapplld.model.Comment;
import medium.socialnetworkapplld.model.Post;
import medium.socialnetworkapplld.model.User;
import medium.socialnetworkapplld.observer.PostObserver;
import medium.socialnetworkapplld.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostService {

    private final PostRepository postRepository = PostRepository.getInstance();
    private final List<PostObserver> observers = new ArrayList<>();

    public void addObserver(PostObserver postObserver){
        this.observers.add(postObserver);
    }


    public Post createPost(User author, String content, String imageUrl) {
        Post post = new Post(author, content, imageUrl);
        postRepository.save(post);
        author.addPost(post);
        observers.forEach(observer -> observer.onPost(post)); // Notify observers
        return post;
    }

    public void likePost(User user, String postId){
        Post post = postRepository.findById(postId);
        post.addLike(user);
        observers.forEach(observer -> observer.onLike(post, user));
    }

    public void addComment(User user, String commentableId, String content) {
        Comment comment = new Comment(user, content);
        Post post = postRepository.findById(commentableId);
        post.addComment(comment);
        observers.forEach(observer -> observer.onComment(post, comment));
    }
}
