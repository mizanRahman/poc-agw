package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Created by mizan on 4/20/17.
 */
@Component
@ConditionalOnProperty(value = "sms.provider", havingValue = "infobip")
@Slf4j
public class TestSmsSenderService implements SmsSenderService {

    @Override
    public String test() {
        log.info("I am TestSmsSenderService");
        return "I am TestSmsSenderService";
    }
}
