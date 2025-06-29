package ratelimiterlld.algo.impl;

import ratelimiterlld.algo.RateLimiter;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLogRateLimiter implements RateLimiter {

    private final int maxRequests;  // how much max request can come inside a window
    private final long windowSizeInMillis;  // it is sliding window size

    private final Map<String, Deque<Long>> requestLog;

    public SlidingWindowLogRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        requestLog = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        requestLog.putIfAbsent(userId, new LinkedList<>());

        Deque<Long> timestamps = requestLog.get(userId);

        while (!timestamps.isEmpty() && timestamps.peek() < currentTime - windowSizeInMillis) {
            timestamps.removeFirst();
            // i.e. removing the earliest time which does not belong to this window
        }

        // now check if we can include this request in current window
        // check if current window has already this much requests
        if (timestamps.size() >= maxRequests) {
            return false;
        }

        timestamps.addLast(currentTime);
        return true;
    }
}
