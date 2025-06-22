package ratelimiterlld;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(String algorithmType, int maxRequests, long windowSizeMillis){

        // refill rate per second
        double refillRate = (1.0) * (maxRequests)/(windowSizeMillis*1000);

        return switch (algorithmType){
            case "token-bucket" -> new TokenBucketRateLimiter(maxRequests, refillRate);
            default -> throw new IllegalArgumentException("Unsupported type.");
        };
    }
}
