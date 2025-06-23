package ratelimiterlld.algo.impl;

import ratelimiterlld.algo.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final double refillRate;  // per second refill rate
    private final Map<String, Double> tokens = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRefillTimestamp = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(int capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    @Override
    public synchronized boolean allowRequest(String userId) {
        tokens.putIfAbsent(userId, (double) capacity);
        refill(userId);

        if (tokens.get(userId) > 0) {
            // decrease the token
            tokens.put(userId, tokens.get(userId) - 1);
            return true;
        }
        return false;
    }

    private void refill(String userId) {
        long currentTime = System.currentTimeMillis();
        lastRefillTimestamp.putIfAbsent(userId, currentTime);

        long lastRefillTime = lastRefillTimestamp.get(userId);    // last refill time in millis
        long elapsedTime = (currentTime - lastRefillTime) / 1000;  // number of seconds elapsed
        if (elapsedTime > 0) {
            double tokensToAdd = elapsedTime * refillRate;
            // so new tokens inside the bucket will be
            double newTokens = Math.min(capacity, tokens.get(userId) + tokensToAdd);

            tokens.put(userId, newTokens);
            lastRefillTimestamp.put(userId, currentTime);
        }
    }
}
