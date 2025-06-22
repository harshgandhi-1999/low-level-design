package ratelimiterlld;

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

    public void registerUser(String userId, String algorithm, int maxRequests, long windowSizeSeconds){
        userRateLimiters.put(userId, RateLimiterFactory.createRateLimiter(algorithm, maxRequests, windowSizeSeconds*1000));
    }

    public boolean allowRequest(String userId){
        RateLimiter rateLimiter = userRateLimiters.get(userId);
        if (rateLimiter == null) throw new IllegalArgumentException("User not registered");
        return rateLimiter.allowRequest(userId);
    }
}
