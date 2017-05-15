package com.example.ratelimit;

import com.example.GatewayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by mizan on 5/7/17.
 */
@Service
@Slf4j
public class RateLimiterService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    GatewayProperties gatewayProperties;

    private static final String REDIS_RATE_LIMIT_KEY = "gateway.rate-limit.key.";

    public boolean checkRateLimit() {
        int duration = 10;
        Long now = System.currentTimeMillis();
        Long window = now / (1000 * duration);
        String key = REDIS_RATE_LIMIT_KEY.concat(String.valueOf(window));
        log.debug("key={}", key);
        Long increment = redisTemplate.opsForValue().increment(key, 1);
        log.debug("increment={}. limit={}", increment, gatewayProperties.getRateLimit());
        redisTemplate.expire(key, duration, TimeUnit.SECONDS);

        if (increment > gatewayProperties.getRateLimit()) {
            log.warn("system is under heavy load. please try some times later");
            return true;
        }
        return false;
    }
}
