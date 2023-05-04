package com.example.updateapi.controllers;

import com.example.updateapi.entety.JobPosition;
import com.example.updateapi.model.JobDTO;
import com.example.updateapi.model.LocationsCountDTO;
import com.example.updateapi.repository.JobPosRepository;
import com.example.updateapi.service.JobFromApiService;
import com.example.updateapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*THIS IS MAIN CONTROLLER
*
* MAIN URL (localhost:8080/jobs)
*   -NAVIGATION VARIABLES(localhost:8080/jobs?page=(int)&sortBy=(variable name))
*    variable name - is the name of the variable that is written in the class (JobPosition)
*
* FIND BY SLUG URL(localhost:8080/jobs/{slug})
*
* DISPLAYS THE 10 MOST RECENT JOB OFFERS (localhost:8080/jobs/new)
*
* DISPLAYS THE NUMBER OF JOB OFFERS FOR EACH DISTRICT (localhost:8080/jobs/locations)
*   -NAVIGATION VARIABLE(localhost:8080/jobs/locations?page=(int))
*
* */
@RestController
@RequestMapping("jobs")
public class MainController {
    @Autowired
    JobPosRepository jobRepo;
    @Autowired
    JobService jobService;



    @GetMapping("{slug}")
    public JobDTO getOne(@PathVariable("slug") String slug) {
        return jobService.getJobDtoBySlug(slug);
    }
    @GetMapping("/new")
    public List<JobDTO> getOrder(){
        return jobService.getOrderByDate();
    }
    @GetMapping
    public Page<JobDTO> main(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return jobService.getAllJobs(page, sortBy);
    }

    @GetMapping("/locations")
    public Page<LocationsCountDTO> countJobsByLocation(@RequestParam Optional<Integer> page) {
        return jobRepo.countJobsByLocation(PageRequest.of(page.orElse(0), 5));
    }

}
