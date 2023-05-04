package com.example.updateapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Autowired
    private JobUpdater jobUpdater;

    // update db every 2 min
    @Scheduled(fixedRate = 120000)
    public void updateJobs() {
        jobUpdater.updateJobs();
    }
}
