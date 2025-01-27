package com.example.ewallet.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitingMiddleware implements HandlerInterceptor {

    private static final int MAX_REQUESTS = 10; // Maximum requests allowed
    private static final long TIME_WINDOW = 60000; // Time window in milliseconds (1 minute)

    private final ConcurrentHashMap<String, UserRequestCounter> requestCounters = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = request.getRemoteAddr();
        long currentTime = System.currentTimeMillis();

        requestCounters.compute(clientIp, (ip, counter) -> {
            if (counter == null || currentTime - counter.timestamp > TIME_WINDOW) {
                return new UserRequestCounter(currentTime, new AtomicInteger(1));
            } else {
                counter.requestCount.incrementAndGet();
                return counter;
            }
        });

        UserRequestCounter counter = requestCounters.get(clientIp);
        if (counter.requestCount.get() > MAX_REQUESTS) {
            response.setStatus(429); // Use the HTTP status code directly
            response.getWriter().write("Too Many Requests");
            return false;
        }

        return true;
    }

    private static class UserRequestCounter {
        long timestamp;
        AtomicInteger requestCount;

        public UserRequestCounter(long timestamp, AtomicInteger requestCount) {
            this.timestamp = timestamp;
            this.requestCount = requestCount;
        }
    }
}
