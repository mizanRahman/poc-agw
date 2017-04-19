package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Created by mizan on 4/20/17.
 */
@Component
@ConditionalOnProperty(name = "sms.provider", havingValue = "kona", matchIfMissing = true)
@Slf4j
public class RealSmsSenderService implements SmsSenderService {

    @Override
    public String test() {
        return "I am real";
    }
}
