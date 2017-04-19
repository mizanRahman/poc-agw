package com.example;

import com.example.filter.DynamicRouteLocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

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
//    @Bean
//    public JettyEmbeddedServletContainerFactory  jettyEmbeddedServletContainerFactory() {
//        JettyEmbeddedServletContainerFactory jettyContainer =
//                new JettyEmbeddedServletContainerFactory();
//
//        jettyContainer.setPort(serverProperties.getPort());
//        jettyContainer.setContextPath(serverProperties.getContextPath());
//        return jettyContainer;
//    }
//
//
//    @Bean
//    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
//        UndertowEmbeddedServletContainerFactory factory =
//                new UndertowEmbeddedServletContainerFactory();
//
//        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
//            @Override
//            public void customize(io.undertow.Undertow.Builder builder) {
//                builder.addHttpListener(serverProperties.getPort(), "0.0.0.0");
//            }
//        });
//
//        return factory;
//    }


}



