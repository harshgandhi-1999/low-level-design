package hard.ratelimiterlld.algo.impl;

import hard.ratelimiterlld.algo.RateLimiter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyBucketRateLimiter implements RateLimiter {

    private final int capacity; // Bucket size
    private final int leakIntervalInMillis;

    private final Queue<Long> bucket = new LinkedList<>();

    private final ScheduledExecutorService scheduledExecutorService;

    // leakRate: this many requests will be leaked per second
    public LeakyBucketRateLimiter(int capacity, double leakRate) {
        this.capacity = capacity;
        this.leakIntervalInMillis = (int) (1000 / leakRate);   /// so here 1 request will be removed  in this much time in millis
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // setting up scheduler to leak the bucket at fixed rate
        scheduledExecutorService.scheduleAtFixedRate(this::leakRequests, leakIntervalInMillis, leakIntervalInMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        if (bucket.size() < capacity) {
            bucket.offer(currentTime);
            return true;
        }

        return false;
    }

    private synchronized void leakRequests() {
        if (!bucket.isEmpty()) {
            bucket.poll();//Request processed
        }
    }

    public void shutdown(){
        scheduledExecutorService.shutdown();
    }
}
