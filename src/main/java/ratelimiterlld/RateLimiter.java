package ratelimiterlld;

public interface RateLimiter {

    boolean allowRequest(String userId);
}
