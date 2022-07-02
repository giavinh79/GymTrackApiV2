package com.gymtrack.api.filter;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Uses a modified version of this rate limiter https://stackoverflow.com/a/58774257
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class RequestThrottleFilter extends OncePerRequestFilter implements Filter {
    private final static int MAX_REQUESTS_PER_SECOND = 5;

    private LoadingCache<String, Integer> requestCountsPerIpAddress;

    public RequestThrottleFilter() {
        super();
        requestCountsPerIpAddress = Caffeine.newBuilder().
                expireAfterWrite(1, TimeUnit.SECONDS).build(key -> 0);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        log.info("[SUCCESS] {} request received for endpoint: {}", request.getMethod(), request.getRequestURI());

        String clientIpAddress = getClientIP(request);

        if (isMaximumRequestsPerSecondExceeded(clientIpAddress)) {
            log.warn("[WARNING] User reached request rate limit - [ip={}]", request.getRemoteAddr());
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "Too many requests");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isMaximumRequestsPerSecondExceeded(String clientIpAddress) {
        Integer requests = requestCountsPerIpAddress.get(clientIpAddress);
        if (requests != null) {
            if (requests > MAX_REQUESTS_PER_SECOND) {
                requestCountsPerIpAddress.asMap().remove(clientIpAddress);
                requestCountsPerIpAddress.put(clientIpAddress, requests);
                return true;
            }
        } else {
            requests = 0;
        }
        requestCountsPerIpAddress.put(clientIpAddress, requests + 1);
        return false;
    }

    public String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}