package com.example.updateapi.service;

import com.example.updateapi.entety.JobPosition;
import com.example.updateapi.model.JobDTO;
import com.example.updateapi.repository.JobPosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//service for MainController
@Service
public class JobService {
    @Autowired
    JobPosRepository jobRepo;

    public Page<JobDTO> getAllJobs(@RequestParam Optional<Integer> page,
                                   @RequestParam Optional<String> sortBy
    ) {
        List<JobDTO> outPage = jobRepo.findAll(PageRequest.of(
                        page.orElse(0), 5,
                        Sort.Direction.ASC, sortBy.orElse("id")))
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
        return new PageImpl<JobDTO>(outPage);
    }
    public JobDTO getJobDtoBySlug(String slug){
       return convertEntityToDto(jobRepo.getJobPositionBySlug(slug));
    }
    public List<JobDTO>getOrderByDate(){
        return jobRepo.findTop10ByOrderByCreatedAtDesc().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }


    //CONVERT JobPosition to JobDTO
    private JobDTO convertEntityToDto(JobPosition jobPosition) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setSlug(jobPosition.getSlug());
        jobDTO.setRemote(jobPosition.isRemote());
        jobDTO.setDescription(jobPosition.getDescription());
        jobDTO.setTags(jobPosition.getTags());
        jobDTO.setTitle(jobPosition.getTitle());
        jobDTO.setUrl(jobPosition.getUrl());
        jobDTO.setCompany_name(jobPosition.getCompanyName());
        jobDTO.setCreated_at(jobPosition.getCreatedAt());
        jobDTO.setJob_types(jobPosition.getJobTypes());
        jobDTO.setLocation(jobPosition.getLocation());
        return jobDTO;

    }


}
