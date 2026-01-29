package hard.ratelimiterlld.factory;

import hard.ratelimiterlld.algo.impl.FixedWindowCounterRateLimiter;
import hard.ratelimiterlld.algo.impl.LeakyBucketRateLimiter;
import hard.ratelimiterlld.algo.RateLimiter;
import hard.ratelimiterlld.algo.impl.SlidingWindowLogRateLimiter;
import hard.ratelimiterlld.config.RateLimiterConfig;
import hard.ratelimiterlld.algo.impl.TokenBucketRateLimiter;

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
