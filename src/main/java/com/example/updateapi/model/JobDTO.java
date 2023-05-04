package com.example.updateapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    private String slug;
    private String company_name;
    private String title;
    private String description;
    private boolean remote;

    private String url;
    List<String> tags=new ArrayList<String>();
    List<String> job_types=new ArrayList<String>();

    private String location;
    private String created_at;
}
