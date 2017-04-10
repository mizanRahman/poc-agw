package com.example;


import com.example.ExecutionStatus;
import lombok.Data;

/**
 * Created by mizan on 4/8/17.
 */
public class KppBaseResponse {
    private Long processingStart;
    private Long processesingEnd;
    private ExecutionStatus executionStatus;

    public Long getProcessingStart() {
        return processingStart;
    }

    public void setProcessingStart(Long processingStart) {
        this.processingStart = processingStart;
    }

    public Long getProcessesingEnd() {
        return processesingEnd;
    }

    public void setProcessesingEnd(Long processesingEnd) {
        this.processesingEnd = processesingEnd;
    }

    public ExecutionStatus getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }
}
