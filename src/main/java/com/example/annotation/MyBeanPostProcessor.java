//package com.example.annotation;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.data.util.ReflectionUtils;
//import org.springframework.stereotype.Component;
//
///**
// * Created by mizan on 5/14/17.
// */
//@Component
//@Slf4j
//public class MyBeanPostProcessor implements BeanPostProcessor {
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        log.info("bean is going to be initialized: {}", beanName);
////        AnnotationUtils.findAnnotation();
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        log.info("bean is initialized: {}", beanName);
//
//        return bean;
//    }
//}
