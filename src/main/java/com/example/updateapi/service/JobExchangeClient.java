package com.example.updateapi.service;


import com.example.updateapi.model.JobDTO;
import com.example.updateapi.model.JobsDTO;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class JobExchangeClient {
    HttpClient httpClient= HttpClientBuilder.create().build();
    ClientHttpRequestFactory requestFactory= new HttpComponentsClientHttpRequestFactory(httpClient);
    private RestTemplate restTemplate=new RestTemplate(requestFactory);
    public List<JobDTO> getJob(){
        String url="https://www.arbeitnow.com/api/job-board-api";
        try{
            JobsDTO response=restTemplate.getForObject(new URI(url),JobsDTO.class);
            return response.getData();
        }catch (URISyntaxException e){
            throw new RuntimeException(e);
        }
    }
}
