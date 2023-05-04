package com.example.updateapi.entety;


import jakarta.persistence.*;
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
@Entity
@Table
public class JobPosition {
    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition="TEXT")
    private String slug;
    private String companyName;
    @Column(columnDefinition="TEXT")
    private String title;
    @Column(columnDefinition="TEXT")
    private String description;
    private boolean remote;

    private String url;
    @ElementCollection
    List<String> tags=new ArrayList<String>();
    @ElementCollection
    List<String> jobTypes =new ArrayList<String>();

    private String location;

    private String createdAt;
    public JobPosition(String slug, String company_name, String title, String description, boolean remote, String url, String location, String created_at, List<String> tags, List<String> types){
          this.slug=slug;
          this.companyName =company_name;
          this.title=title;
          this.description=description;
          this.remote=remote;
          this.url=url;
          this.location=location;
          this.createdAt =created_at;
          this.tags.addAll(tags);
          this.jobTypes.addAll(types);
    }
}
