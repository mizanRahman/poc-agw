package com.example;

import com.example.annotation.AutoProxyCreator;
import com.example.annotation.LogMethod;
import com.example.dynamicroute.DynamicRouteLocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

@EnableZuulProxy
@SpringBootApplication
@Slf4j
public class GatewayApplication {

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    ZuulProperties zuulProperties;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    RouteLocator routeLocator() {
        return new DynamicRouteLocator(serverProperties.getServletPath(), zuulProperties);
    }
//
//    @Bean(name = "longRedisTemplate")
//    RedisTemplate<String, Long> LongRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Long> longRedisTemplate = new RedisTemplate<String, Long>();
//        longRedisTemplate.setConnectionFactory(redisConnectionFactory);
//        longRedisTemplate.setValueSerializer(new StringRedisSerializer());
//        longRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        longRedisTemplate.afterPropertiesSet();
//        return longRedisTemplate;
//
//    }


    @Bean
    AutoProxyCreator autoProxyCreator(ApplicationContext applicationContext) {
        AutoProxyCreator autoProxyCreator = new AutoProxyCreator();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(LogMethod.class);

        for (String beanName : beansWithAnnotation.keySet()) {
            Method[] declaredMethods =
                    AopUtils.getTargetClass(beansWithAnnotation.get(beanName)).getDeclaredMethods();
            for (Method m : declaredMethods) {
                Annotation[] annotations = AnnotationUtils.getAnnotations(m);
            }

            autoProxyCreator.setBeanNames(beanName);
            log.info("{}.  bean is annotated with: {}. proxy is created.", beanName, LogMethod.class);
        }
        return autoProxyCreator;
    }
}



