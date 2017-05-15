package com.example;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by mizan on 4/9/17.
 */
@Data
@Component
@ConfigurationProperties(prefix = "gateway", ignoreUnknownFields = true)
public class GatewayProperties {

    private Long rateLimit = 10L;

    private boolean enabled = true;
    private Map<String, UserSensitiveApi> userSensitiveApis;

    @Data
    @ToString
    public static class UserSensitiveApi {
        private String method;
        private String url;
    }
}
