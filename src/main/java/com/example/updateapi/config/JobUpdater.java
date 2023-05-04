package com.example.updateapi.config;

import com.example.updateapi.entety.JobPosition;
import com.example.updateapi.repository.JobPosRepository;
import com.example.updateapi.service.JobFromApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


//THIS CLASS UPDATES ALL RECORDS IN THE DATABASE
@Component
public class JobUpdater {
    @Autowired
    private JobFromApiService jobService;

    @Autowired
    private JobPosRepository jobPositionRepository;

    // update DB
    public void updateJobs() {
        List<JobPosition> jobPositions = jobService.findAll();

        jobPositionRepository.deleteAll();
        jobPositionRepository.saveAll(jobPositions);
    }
}
