package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GatewayApplicationTests {

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @Test
    public void contextLoads() {
    }

    @Test
    public void testRedisHashOps() {
        BoundHashOperations<String, String, String> hashOps
                = redisTemplate.boundHashOps("service.usage-info");

        String svcId = "1123", value = "121231";

        hashOps.delete(svcId);

        log.info("result = {}", String.valueOf(hashOps.get(svcId)));
        Assert.assertEquals(null, hashOps.get(svcId));

        hashOps.increment(svcId, 1);
        log.info("result = {}", String.valueOf(hashOps.get(svcId)));
        Assert.assertEquals("1", hashOps.get(svcId));

        hashOps.put(svcId, value);
        log.info("result = {}", String.valueOf(hashOps.get(svcId)));
        Assert.assertEquals(value, hashOps.get(svcId));


        Long increment = hashOps.increment(svcId, 1);
        log.info("result = {}, incr={}", String.valueOf(hashOps.get(svcId)), increment);
        Assert.assertEquals("121232", hashOps.get(svcId));
        Assert.assertEquals("121232", String.valueOf(increment));
    }


    @Test
    public void testRedisSetOps() {
        BoundValueOperations<String, String> ok =
                redisTemplate.boundValueOps("ok");
    }
}
