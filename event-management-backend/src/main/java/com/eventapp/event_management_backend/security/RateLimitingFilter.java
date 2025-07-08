package com.eventapp.event_management_backend.security;

import io.github.bucket4j.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter extends GenericFilter {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    private Bucket newBucket() {
        Refill refill = Refill.greedy(10, Duration.ofMinutes(1)); // 10 requests per minute
        Bandwidth limit = Bandwidth.classic(10, refill);
        return Bucket.builder().addLimit(limit).build();
    }

    private Bucket resolveBucket(HttpServletRequest request) {
        String key = extractKey(request);
        return cache.computeIfAbsent(key, k -> newBucket());
    }

    private String extractKey(HttpServletRequest request) {
        // If authenticated, rate limit by user; else fallback to IP
        String user = request.getRemoteUser();
        return (user != null) ? "user:" + user : "ip:" + request.getRemoteAddr();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Bucket bucket = resolveBucket(request);
        if (bucket.tryConsume(1)) {
            chain.doFilter(req, res);
        } else {
            response.setStatus(429);
            response.getWriter().write("Too Many Requests");
        }
    }
}
