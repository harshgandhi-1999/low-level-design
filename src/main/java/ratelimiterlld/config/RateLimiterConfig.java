package ratelimiterlld.config;

import ratelimiterlld.Algorithm;

public class RateLimiterConfig {
    private Algorithm algorithm;
    private int limit;
    private long windowSizeInMillis;
    private double refillRate;  // per second how many tokens are refilled
    private double leakRate;  // per second how many request will be leaked

    public RateLimiterConfig(RateLimiterConfigBuilder builder) {
        this.algorithm = builder.getAlgorithm();
        this.limit = builder.getLimit();
        this.windowSizeInMillis = builder.getWindowSizeInMillis();
        this.refillRate = builder.getRefillRate();
        this.leakRate = builder.getLeakRate();
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public int getLimit() {
        return limit;
    }

    public long getWindowSizeInMillis() {
        return windowSizeInMillis;
    }

    public double getRefillRate() {
        return refillRate;
    }

    public double getLeakRate() {
        return leakRate;
    }

}
