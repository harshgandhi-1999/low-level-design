package ratelimiterlld.algo;

public interface RateLimiter {
    boolean allowRequest(String userId);
}
