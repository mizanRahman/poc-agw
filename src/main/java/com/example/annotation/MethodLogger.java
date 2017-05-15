package com.example.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * Created by mizan on 5/14/17.
 */
@Slf4j
@Component
public class MethodLogger implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("method annotations: {}", invocation.getMethod().getDeclaredAnnotations());
        if (invocation.getMethod().getAnnotation(LogMethod.class) != null) {
            log.info("accessing {}.{} params: {}",
                    invocation.getMethod().getDeclaringClass(),
                    invocation.getMethod().getName(),
                    invocation.getMethod().getParameters()
            );
        }
        final Object result = invocation.proceed();
        if (invocation.getMethod().getAnnotation(LogMethod.class) != null) {

            log.info("returned from {}.{} returns: {}",
                    invocation.getMethod().getDeclaringClass(),
                    invocation.getMethod().getName(),
                    invocation.getMethod().getReturnType()
            );
        }
        return result;
    }
}
