package com.example.applications.alpha.sample.model;

import java.time.ZonedDateTime;

import com.example.cores.alpha.jpa.entity.Sample;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SampleResponse
 * 
 * created by jonghyeon on 2022/11/14
 */
@Data
@NoArgsConstructor
public class SampleResponse {

    public SampleResponse(Sample sampleData) {
        this.sampleId = sampleData.getSampleId();
        this.name = sampleData.getName();
        this.altername = sampleData.getAltername();
        this.enabled = sampleData.getEnabled();
    }

    private Integer sampleId;
    private String name;
    private String altername;
    private Boolean enabled;

    // extra variable
    private String returnMessage;
    private ZonedDateTime returnTime;
}
