package medium.socialnetworkapplld.model;

public class Post extends CommentableEntity{

    private final String imageUrl;

    public Post(User author, String content, String imageUrl) {
        super(author, content);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
