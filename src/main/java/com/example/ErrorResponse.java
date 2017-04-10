package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by mizan on 4/8/17.
 */
@Data(staticConstructor = "of")
public class ErrorResponse {
     final String reason;
     final String message;
}
