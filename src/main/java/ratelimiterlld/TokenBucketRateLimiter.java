package ratelimiterlld;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter{
    private final int capacity;
    private final double refillRate;  // per second refill rate
    private final Map<String, Integer> tokens = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRefillTimestamp = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(int capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        lastRefillTimestamp.putIfAbsent(userId, currentTime);
        tokens.putIfAbsent(userId, capacity);

        long lastRefill = lastRefillTimestamp.get(userId);    // last refit time in millis
        long elapsedTime = (currentTime - lastRefill) / 1000;  // number of seconds elapsed
        if (elapsedTime > 0) {
            // now calculate number of refills during this period
            int numberOfRefills = (int) (elapsedTime * refillRate);
            // so new tokens inside the bucket will be
            int newTokens = Math.min(capacity, tokens.get(userId) + numberOfRefills);
            tokens.put(userId, newTokens);
            lastRefillTimestamp.put(userId, currentTime);
        }
        if (tokens.get(userId) > 0) {
            // decrease the token
            tokens.put(userId, tokens.get(userId)-1);
            return true;
        }
        return false;
    }
}
