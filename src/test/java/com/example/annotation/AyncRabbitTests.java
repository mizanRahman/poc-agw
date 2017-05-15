package com.example.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mizan on 5/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AyncRabbitTests {

    @Test
    public void test() {
        log.info("testing method");
        asyncMethod();
    }

    @AsyncRabbit
    public void asyncMethod() {
        log.info("async running...");
    }

}
