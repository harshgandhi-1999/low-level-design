package ratelimiterlld;

import ratelimiterlld.algo.impl.FixedWindowCounterRateLimiter;
import ratelimiterlld.algo.impl.LeakyBucketRateLimiter;
import ratelimiterlld.config.RateLimiterConfig;
import ratelimiterlld.config.RateLimiterConfigBuilder;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        RateLimiterService service = RateLimiterService.getInstance();

        tokenBucketDemo(service);
        leakyBucketDemo(service);
        fixedWindowCounterDemo(service);
    }

    public static void tokenBucketDemo(RateLimiterService service) throws InterruptedException{
        RateLimiterConfig config = new RateLimiterConfigBuilder()
                .setAlgorithm(Algorithm.TOKEN_BUCKET)
                .setLimit(3)
                .setRefillRate(3)  // per second 3 token will be filled
                .build();

        service.registerUser("user-1", config);
        for (int i = 0; i < 15; i++) {
            System.out.println("User 1 Request " + (i + 1) + " : " + service.allowRequest("user-1"));
            Thread.sleep(100);
        }
    }

    public static void leakyBucketDemo(RateLimiterService service) throws InterruptedException{
        RateLimiterConfig config = new RateLimiterConfigBuilder()
                .setAlgorithm(Algorithm.LEAKY_BUCKET)
                .setLimit(5)
                .setLeakRate(2)
                .build();

        service.registerUser("user-2", config);

        LeakyBucketRateLimiter leakyBucketRateLimiter = (LeakyBucketRateLimiter) service.getRateLimiter("user-2");
        if(leakyBucketRateLimiter != null){
            for (int i = 0; i < 15; i++) {
                System.out.println("User 2 Request " + (i + 1) + " : " + service.allowRequest("user-2"));
                Thread.sleep(100);
            }
            leakyBucketRateLimiter.shutdown();
        }
    }

    public static void fixedWindowCounterDemo(RateLimiterService service) throws InterruptedException{
        RateLimiterConfig config = new RateLimiterConfigBuilder()
                .setAlgorithm(Algorithm.FIXED_WINDOW)
                .setLimit(5)
                .setWindowSizeInMillis(1000)
                .build();

        service.registerUser("user-3", config);

        FixedWindowCounterRateLimiter fixedWindowCounterRateLimiter = (FixedWindowCounterRateLimiter) service.getRateLimiter("user-3");
        if(fixedWindowCounterRateLimiter != null){
            for (int i = 0; i < 20; i++) {
                System.out.println("User 3 Request " + (i + 1) + " : " + service.allowRequest("user-3"));
                Thread.sleep(100);
            }
        }
    }
}
