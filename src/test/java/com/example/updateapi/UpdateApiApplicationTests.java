package com.example.updateapi;

import com.example.updateapi.controllers.MainController;
import com.example.updateapi.model.JobDTO;
import com.example.updateapi.repository.JobPosRepository;
import com.example.updateapi.service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UpdateApiApplicationTests {
    @Autowired private ObjectMapper objectMapper;
    @Autowired
    private MainController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    JobService jobService;

    @InjectMocks
    private MainController mainController;

    @Test
    void UrlTest() throws Exception {

        this.mockMvc.perform(get("/jobs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void getOneTest() throws Exception {
        Mockito.when(jobService.getJobDtoBySlug("rt-director-munich-121407")).thenReturn(new JobDTO());
        this.mockMvc.perform(get("/jobs/rt-director-munich-121407"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }
    @Test
    void getOrderTest() throws Exception {

        this.mockMvc.perform(get("/jobs/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }
    @Test
    void countJobsByLocationTest() throws Exception {

        this.mockMvc.perform(get("/jobs/locations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }




}
