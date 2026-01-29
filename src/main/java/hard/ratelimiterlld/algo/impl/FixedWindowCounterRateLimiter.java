package hard.ratelimiterlld.algo.impl;

import hard.ratelimiterlld.algo.RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class FixedWindowCounterRateLimiter implements RateLimiter {

    private final int maxRequests;  // how much max request can come inside a window
    private final long windowSizeInMillis;  // it is window size
    private final Map<String, Integer> requestCounts;  // fo every user we are keeping request count.

    private final Map<String, Long> windowStartTimes;  // for every user we are keeping its window start time.

    public FixedWindowCounterRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        this.requestCounts = new HashMap<>();
        this.windowStartTimes = new HashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();

        // first time user
        windowStartTimes.putIfAbsent(userId, currentTime);
        requestCounts.putIfAbsent(userId, 0);

        long windowStartTime = windowStartTimes.get(userId);

        // here new window starts
        if(currentTime - windowStartTime >= windowSizeInMillis){
            windowStartTimes.put(userId, currentTime);
            requestCounts.put(userId, 0);
        }

        int requestCount = requestCounts.get(userId);
        if(requestCount < maxRequests){
            // accept the request
            requestCounts.put(userId, requestCount+1);
            return true;
        }

        return false;
    }
}
