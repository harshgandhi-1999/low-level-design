package ratelimiterlld;

import ratelimiterlld.algo.RateLimiter;
import ratelimiterlld.config.RateLimiterConfig;
import ratelimiterlld.factory.RateLimiterFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterService {

    private final Map<String, RateLimiter> userRateLimiters;

    private static volatile RateLimiterService INSTANCE;

    private RateLimiterService(){
        this.userRateLimiters = new ConcurrentHashMap<>();
    }

    public static RateLimiterService getInstance(){
        if(INSTANCE==null){
            synchronized (RateLimiterService.class){
                if (INSTANCE == null){
                    INSTANCE = new RateLimiterService();
                }
            }
        }

        return INSTANCE;
    }

    public void registerUser(String userId, RateLimiterConfig config){
        userRateLimiters.put(userId, RateLimiterFactory.createRateLimiter(config));
    }

    public RateLimiter getRateLimiter(String userId){
        return userRateLimiters.get(userId);
    }

    public boolean allowRequest(String userId){
        RateLimiter rateLimiter = userRateLimiters.get(userId);
        if (rateLimiter == null) throw new IllegalArgumentException("User not registered");
        return rateLimiter.allowRequest(userId);
    }
}
