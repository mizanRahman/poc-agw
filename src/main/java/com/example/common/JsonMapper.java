package com.example.common;

/**
 * Created by mizan on 4/8/17.
 */
public interface JsonMapper {
    String toJson(Object value);

    <T> T fromJson(String json, Class<T> tClass);

}
