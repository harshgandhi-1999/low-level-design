package ratelimiterlld.factory;

import ratelimiterlld.algo.impl.FixedWindowCounterRateLimiter;
import ratelimiterlld.algo.impl.LeakyBucketRateLimiter;
import ratelimiterlld.algo.RateLimiter;
import ratelimiterlld.config.RateLimiterConfig;
import ratelimiterlld.algo.impl.TokenBucketRateLimiter;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimiterConfig config) {
        switch (config.getAlgorithm()) {
            case FIXED_WINDOW:
                return new FixedWindowCounterRateLimiter(config.getLimit(), config.getWindowSizeInMillis());
//            case SLIDING_WINDOW:
//                return new SlidingWindowRateLimiter(config.getLimit(), config.getWindowInSeconds());
            case LEAKY_BUCKET:
                return new LeakyBucketRateLimiter(config.getLimit(), config.getLeakRate());
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(config.getLimit(), config.getRefillRate());
            default:
                throw new IllegalArgumentException("Invalid rate limiter type");
        }
    }
}
