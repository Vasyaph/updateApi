package com.example.updateapi;

import com.example.updateapi.entety.JobPosition;
import com.example.updateapi.model.JobDTO;
import com.example.updateapi.repository.JobPosRepository;
import com.example.updateapi.service.JobExchangeClient;
import com.example.updateapi.service.JobFromApiService;
import com.example.updateapi.service.JobService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class JobFromApiServiceTest {

    @MockBean
    private JobExchangeClient jobExchangeClient;
    @InjectMocks
    private JobFromApiService jobFromApiService;

    @Test
    public void findAllTest() {
        List<JobDTO> jobDTOList = new ArrayList<>();
        jobDTOList.add(JobDTO.builder().location("Kyiv").remote(true).created_at("123456").description("discription").url("http//index.html").slug("myCompany-1234").company_name("myCompany").job_types(new ArrayList<>()).tags(new ArrayList<>()).build());
        Mockito.when(jobExchangeClient.getJob()).thenReturn(jobDTOList);
        List<JobPosition> jobPositions = jobFromApiService.findAll();
        assertEquals(jobPositions.get(0).getSlug(), jobDTOList.get(0).getSlug());
    }

}
