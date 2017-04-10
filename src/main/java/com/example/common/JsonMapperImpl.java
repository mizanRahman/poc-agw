package com.example.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by mizan on 4/8/17.
 */
@Component
@Slf4j
public class JsonMapperImpl implements JsonMapper {

    @Autowired
    ObjectMapper objectMapper;

    public String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("json processing error. ",e);
            throw new RuntimeException(e);
        }
    }

    public <T> T fromJson(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            log.error("json processing error. ",e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("json processing error. ",e);
            throw new RuntimeException(e);
        }
    }
}
