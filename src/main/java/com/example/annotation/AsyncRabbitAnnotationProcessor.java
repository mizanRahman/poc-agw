//package com.example.annotation;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ReflectionUtils;
//
///**
// * Created by mizan on 5/11/17.
// */
//@Component
//public class AsyncRabbitAnnotationProcessor implements BeanPostProcessor {
//
//
//    @Autowired
//    @Async
//    ConfigurableListableBeanFactory configurableBeanFactory;
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        this.scanDataAccessAnnotation(bean, beanName);
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        return bean;
//    }
//    protected void scanDataAccessAnnotation(Object bean, String beanName) {
//        this.configureFieldInjection(bean);
//    }
//
//    private void configureFieldInjection(Object bean) {
//        Class<?> managedBeanClass = bean.getClass();
//        ReflectionUtils.FieldCallback fieldCallback =
//                new DataAccessFieldCallback(configurableBeanFactory, bean);
//        ReflectionUtils.doWithFields(managedBeanClass, fieldCallback);
//    }
//}
