package com.example;

import lombok.*;

/**
 * Created by mizan on 4/9/17.
 */
@Data
@Builder
@ToString
public class UserCredential {
    String userId;
    String passwordHash;
}
