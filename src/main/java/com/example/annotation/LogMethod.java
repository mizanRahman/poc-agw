package com.example.annotation;

import org.springframework.scheduling.annotation.Async;

import java.lang.annotation.*;
import java.util.concurrent.Executor;

/**
 * Created by mizan on 5/14/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface LogMethod {
}
