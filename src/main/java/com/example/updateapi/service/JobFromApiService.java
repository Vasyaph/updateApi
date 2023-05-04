package com.example.updateapi.service;

import com.example.updateapi.entety.JobPosition;
import com.example.updateapi.model.JobDTO;
import com.example.updateapi.repository.JobPosRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class JobFromApiService {

    @Autowired
    private JobExchangeClient jobExchangeClient;


    public List<JobPosition> findAll() {
        return jobExchangeClient.getJob().stream()
                .map(this::toJobPosition)
                .collect(Collectors.toList());
    }

    private JobPosition toJobPosition(@NonNull JobDTO input) {
        return new JobPosition(
                input.getSlug(),
                input.getCompany_name(),
                input.getTitle(),
                input.getDescription(),
                input.isRemote(),
                input.getUrl(),
                input.getLocation(),
                input.getCreated_at(),
                input.getTags(),
                input.getJob_types()
        );




    }
}
