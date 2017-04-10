package com.example;

import lombok.Data;

/**
 * Created by mizan on 4/8/17.
 */
public class ExecutionStatus {
    private String reasonCode;
    private String reasonMessage;

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReasonMessage() {
        return reasonMessage;
    }

    public void setReasonMessage(String reasonMessage) {
        this.reasonMessage = reasonMessage;
    }
}
