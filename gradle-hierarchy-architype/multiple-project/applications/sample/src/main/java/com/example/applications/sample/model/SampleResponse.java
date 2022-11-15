package com.example.applications.sample.model;

import java.time.ZonedDateTime;

import com.example.cores.hello.jpa.entity.Sample;

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
        this.nickname = sampleData.getNickname();
        this.name = sampleData.getName();
        this.enabled = sampleData.getEnabled();
    }

    private Integer sampleId;
    private String nickname;
    private String name;
    private Boolean enabled;

    // extra variable
    private String returnMessage;
    private ZonedDateTime returnTime;
}
