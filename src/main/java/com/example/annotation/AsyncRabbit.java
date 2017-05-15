package com.example.annotation;

import java.lang.annotation.*;

/**
 * Created by mizan on 5/11/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AsyncRabbit {
}
