package ratelimiterlld.config;

import ratelimiterlld.Algorithm;

public class RateLimiterConfigBuilder {
    private Algorithm algorithm;
    private int limit;
    private long windowSizeInMillis;
    private double refillRate;
    private double leakRate;

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public RateLimiterConfigBuilder setAlgorithm(Algorithm algorithm){
        this.algorithm = algorithm;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public RateLimiterConfigBuilder setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public long getWindowSizeInMillis() {
        return windowSizeInMillis;
    }

    public RateLimiterConfigBuilder setWindowSizeInMillis(long windowSizeInMillis) {
        this.windowSizeInMillis = windowSizeInMillis;
        return this;
    }

    public double getRefillRate() {
        return refillRate;
    }

    public RateLimiterConfigBuilder setRefillRate(double refillRate) {
        this.refillRate = refillRate;
        return this;
    }

    public double getLeakRate() {
        return leakRate;
    }

    public RateLimiterConfigBuilder setLeakRate(double leakRate) {
        this.leakRate = leakRate;
        return this;
    }
    public RateLimiterConfig build(){
        return new RateLimiterConfig(this);
    }
}
