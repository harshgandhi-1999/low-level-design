package medium.socialnetworkapplld;

import medium.socialnetworkapplld.model.Post;
import medium.socialnetworkapplld.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Requirements
//        User Registration and Authentication:
//            Users should be able to create an account with their personal information, such as name, email, and password.
//            Users should be able to log in and log out of their accounts securely.
//        User Profiles:
//            Each user should have a profile with their information, such as profile picture, bio, and interests.
//            Users should be able to update their profile information.
//        Friend Connections:
//            Users should be able to send friend requests to other users.
//            Users should be able to accept or decline friend requests.
//            Users should be able to view their list of friends.
//        Posts and Newsfeed:
//            Users should be able to create posts with text, images, or videos.
//            Users should be able to view a newsfeed consisting of posts from their friends and their own posts.
//            The newsfeed should be sorted in reverse chronological order.
//        Likes and Comments:
//            Users should be able to like and comment on posts.
//            Users should be able to view the list of likes and comments on a post.
//        Privacy and Security:
//            Users should be able to control the visibility of their posts and profile information.
//            The system should enforce secure access control to ensure data privacy.
//        Notifications:
//            Users should receive notifications for events such as friend requests, likes, comments, and mentions.
//            Notifications should be delivered in real-time.
//        Scalability and Performance:
//            The system should be designed to handle a large number of concurrent users and high traffic load.
//            The system should be scalable and efficient in terms of resource utilization.

        SocialNetworkFacade socialNetwork = new SocialNetworkFacade();

        System.out.println("----------- 1. Creating Users -----------");
        User alice = socialNetwork.createUser("Alice", "alice@example.com");
        User bob = socialNetwork.createUser("Bob", "bob@example.com");
        User charlie = socialNetwork.createUser("Charlie", "charlie@example.com");
        System.out.println("Created users: " + alice.getName() + ", " + bob.getName() + ", " + charlie.getName());

        System.out.println("\n----------- 2. Building Friendships -----------");
        socialNetwork.addFriend(alice.getId(), bob.getId());
        socialNetwork.addFriend(bob.getId(), charlie.getId());
        System.out.println(alice.getName() + " and " + bob.getName() + " are now friends.");
        System.out.println(bob.getName() + " and " + charlie.getName() + " are now friends.");

        System.out.println("\n----------- 3. Users Create Posts -----------");
        Post alicePost = socialNetwork.createPost(alice.getId(), "Hello from Alice!", "");
        Post bobPost = socialNetwork.createPost(bob.getId(), "It's a beautiful day!", "");
        Post charliePost = socialNetwork.createPost(charlie.getId(), "Thinking about design patterns.", "");

        System.out.println("\n----------- 4. Users Interact with Posts -----------");
        socialNetwork.addComment(bob.getId(), alicePost.getId(), "Hey Alice, nice to see you here!");
        socialNetwork.likePost(charlie.getId(), alicePost.getId());

        System.out.println("\n----------- 5. Viewing News Feeds (Strategy Pattern) -----------");

        System.out.println("\n--- Alice's News Feed (should see Bob's post) ---");
        List<Post> alicesFeed = socialNetwork.getNewsFeed(alice.getId());
        printFeed(alicesFeed);

        System.out.println("\n--- Bob's News Feed (should see Alice's, and Charlie's post) ---");
        List<Post> bobsFeed = socialNetwork.getNewsFeed(bob.getId());
        printFeed(bobsFeed);

        System.out.println("\n--- Charlie's News Feed (should see Bob's post) ---");
        List<Post> charliesFeed = socialNetwork.getNewsFeed(charlie.getId());
        printFeed(charliesFeed);
    }

    private static void printFeed(List<Post> feed) {
        if (feed.isEmpty()) {
            System.out.println("  No posts in the feed.");
            return;
        }
        feed.forEach(post -> {
            System.out.println("  Post by " + post.getAuthor().getName() + " at " + post.getTimestamp());
            System.out.println("    \"" + post.getContent() + "\"");
            System.out.println("    Likes: " + post.getLikes().size() + ", Comments: " + post.getComments().size());
        });
    }
}
