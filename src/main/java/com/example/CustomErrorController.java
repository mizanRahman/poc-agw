package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.cloud.netflix.zuul.util.RequestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mizan on 4/26/17.
 */
@RestController
public class CustomErrorController implements ErrorController {

    @Value("${error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "hi")
    public ResponseEntity hi() {
        return ResponseEntity.ok("Hello");
    }

    @RequestMapping(value = "${error.path:/error}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity error(HttpServletRequest request) {

        final int status = getErrorStatus(request);
        final String errorMessage = getErrorMessage(request);
        ErrorResponse error = ErrorResponse.of("30_10002_232", errorMessage);
        return ResponseEntity.status(status).body(error);
    }

    private int getErrorStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private String getErrorMessage(HttpServletRequest request) {
        final Throwable exc = (Throwable) request.getAttribute("javax.servlet.error.exception");
        return exc != null ? exc.getMessage() : "Unexpected error occurred";
    }
}