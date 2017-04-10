package com.example;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by mizan on 4/9/17.
 */
@Data
@Component
@ConfigurationProperties(prefix = "gateway")
public class GatewayProperties {
    private boolean enabled = true;
    private Map<String, UserSensitiveApi> userSensitiveApis;

    @Data
    @ToString
    public static class UserSensitiveApi {
        private String method;
        private String url;
    }
}
