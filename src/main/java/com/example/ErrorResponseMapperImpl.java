package com.example;

import org.springframework.stereotype.Component;

/**
 * Created by mizan on 4/8/17.
 */
@Component
public class ErrorResponseMapperImpl implements ErrorResponseMapper {

    @Override
    public ErrorResponse map(KppBaseResponse kppBaseResponse) {
        String reason = kppBaseResponse.getExecutionStatus().getReasonCode();
        String message = kppBaseResponse.getExecutionStatus().getReasonMessage();
        return ErrorResponse.of(reason, message);
    }
}
