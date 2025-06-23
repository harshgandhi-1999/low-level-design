package ratelimiterlld.config;

import ratelimiterlld.Algorithm;

public class RateLimiterConfig {
    private Algorithm algorithm;
    private int limit;
    private int windowInSeconds;
    private double refillRate;
    private double leakRate;

    public RateLimiterConfig(RateLimiterConfigBuilder builder) {
        this.algorithm = builder.getAlgorithm();
        this.limit = builder.getLimit();
        this.windowInSeconds = builder.getWindowInSeconds();
        this.refillRate = builder.getRefillRate();
        this.leakRate = builder.getLeakRate();
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getWindowInSeconds() {
        return windowInSeconds;
    }

    public void setWindowInSeconds(int windowInSeconds) {
        this.windowInSeconds = windowInSeconds;
    }

    public double getRefillRate() {
        return refillRate;
    }

    public void setRefillRate(double refillRate) {
        this.refillRate = refillRate;
    }

    public double getLeakRate() {
        return leakRate;
    }

    public void setLeakRate(double leakRate) {
        this.leakRate = leakRate;
    }
}
