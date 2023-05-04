package com.example.updateapi.repository;

import com.example.updateapi.entety.JobPosition;
import com.example.updateapi.model.LocationsCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobPosRepository extends JpaRepository<JobPosition, Long> {

    JobPosition getJobPositionBySlug(String slug);
    List<JobPosition> findTop10ByOrderByCreatedAtDesc();

    //I made this line more complicated because I wanted to have a dto output instead of an Object
    @Query("SELECT new com.example.updateapi.model.LocationsCountDTO(j.location, COUNT(j)) FROM JobPosition j GROUP BY j.location ORDER BY COUNT(j) DESC")
    Page<LocationsCountDTO> countJobsByLocation(Pageable pageable);
    // clear table
    @Modifying
    @Query("DELETE FROM JobPosition")
    void deleteAll();
}