package com.example.updateapi;


import com.example.updateapi.entety.JobPosition;
import com.example.updateapi.model.JobDTO;
import com.example.updateapi.repository.JobPosRepository;
import com.example.updateapi.service.JobService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class JobServiceTest {
    @MockBean
    JobPosRepository jobRepo;

    @InjectMocks
    private JobService jobService;

    @Test
    public void getJobDtoBySlugCorrectTest(){
        JobPosition jobPosition=JobPosition.builder().id(1l).slug("unior-pmo-automotive-all-gender-wolfsburg-187433").companyName("unior-pmo").build();
        Mockito.when(jobRepo.getJobPositionBySlug("unior-pmo-automotive-all-gender-wolfsburg-187433")).thenReturn(jobPosition);
        JobDTO jobDTO=jobService.getJobDtoBySlug("unior-pmo-automotive-all-gender-wolfsburg-187433");
        assertTrue(jobDTO.getCompany_name().equals(jobPosition.getCompanyName()));
    }
    @Test
    public void getJobDtoBySlugNullValueTest(){
        JobPosition jobPosition=new JobPosition();
        Mockito.when(jobRepo.getJobPositionBySlug("unior-pmo-automotive-all-gender-wolfsburg-187433")).thenReturn(jobPosition);
        JobDTO jobDTO=jobService.getJobDtoBySlug("unior-pmo-automotive-all-gender-wolfsburg-187433");
        assertEquals(jobDTO,new JobDTO());
    }

    @Test
    public void getOrderByDateTest(){
        List<JobPosition> jobPosition=new ArrayList<>();
        jobPosition.add(JobPosition.builder().id(1l).slug("unior-pmo-automotive-all-gender-wolfsburg-187433").companyName("unior-pmo").build());
        jobPosition.add(JobPosition.builder().id(2l).slug("unior-pmo-automotive-all-gender-wolfsburg-187434").companyName("unior-pmo").build());
        Mockito.when(jobRepo.findTop10ByOrderByCreatedAtDesc()).thenReturn(jobPosition);
        List<JobDTO> jobDTO=jobService.getOrderByDate();
        assertEquals(jobDTO.get(0).getSlug(),jobPosition.get(0).getSlug());
    }


}
