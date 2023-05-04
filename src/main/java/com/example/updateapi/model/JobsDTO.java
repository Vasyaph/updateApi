package com.example.updateapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class JobsDTO {
    List<JobDTO> data=new ArrayList<JobDTO>();
}
