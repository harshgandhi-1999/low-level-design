package ratelimiterlld.factory;

import ratelimiterlld.algo.impl.FixedWindowCounterRateLimiter;
import ratelimiterlld.algo.impl.LeakyBucketRateLimiter;
import ratelimiterlld.algo.RateLimiter;
import ratelimiterlld.algo.impl.SlidingWindowLogRateLimiter;
import ratelimiterlld.config.RateLimiterConfig;
import ratelimiterlld.algo.impl.TokenBucketRateLimiter;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimiterConfig config) {
        return switch (config.getAlgorithm()) {
            case FIXED_WINDOW -> new FixedWindowCounterRateLimiter(config.getLimit(), config.getWindowSizeInMillis());
            case SLIDING_WINDOW_LOG ->
                    new SlidingWindowLogRateLimiter(config.getLimit(), config.getWindowSizeInMillis());
            case LEAKY_BUCKET -> new LeakyBucketRateLimiter(config.getLimit(), config.getLeakRate());
            case TOKEN_BUCKET -> new TokenBucketRateLimiter(config.getLimit(), config.getRefillRate());
        };
    }
}
