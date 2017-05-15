package com.example;

/**
 * Created by mizan on 5/16/17.
 */
public interface CardDownloadInfoService {

    Long getUsageCountForService();
    Long setUsageCountForService(Long count);

    Long getUsageCountForService(String userId);
    Long setUsageCountForService(String userId, Long count);

    void incrementUsageCount(String userId);

    void resetUsageCount();
}
