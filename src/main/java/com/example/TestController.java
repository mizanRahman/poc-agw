package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mizan on 4/20/17.
 */
@RestController
public class TestController {

    @Autowired
    SmsSenderService testBean;

    @GetMapping("/api/1234")
    public String test() {
        return testBean.test();
    }

}
