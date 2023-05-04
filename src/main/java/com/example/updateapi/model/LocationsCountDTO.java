package com.example.updateapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationsCountDTO {
    private String location;
    private Long count;
}
