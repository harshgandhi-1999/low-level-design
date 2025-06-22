package ratelimiterlld;

public class LeakyBucketRateLimiter implements RateLimiter{

    @Override
    public boolean allowRequest(String userId) {
        return false;
    }
}
