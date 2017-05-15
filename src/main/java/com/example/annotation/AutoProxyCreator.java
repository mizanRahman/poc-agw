package com.example.annotation;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;

/**
 * Created by mizan on 5/14/17.
 */
public class AutoProxyCreator extends BeanNameAutoProxyCreator {

    @Override
    protected boolean isMatch(String beanName, String mappedName) {
        return super.isMatch(beanName, mappedName);
    }
}
