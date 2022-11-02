package com.example.cores.hello.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * Sample
 * 
 * created by jonghyeon on 2022/11/01
 */
@Data
@Entity
public class Sample {

    @Id
    private String sampleId;
    private String name;
    private Boolean enabled;
}
