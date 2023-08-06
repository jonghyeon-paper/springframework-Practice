package com.example.cores.gamma.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Sample
 * 
 * @author _sCream
 */
@Data
@Entity
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sampleId;
    private String name;
    private String altername;
    private Boolean enabled;

    /**
     * delete <br>
     * The delete command does not delete the data, it only changes the state.
     */
    public void delete() {
        enabled = false;
    }
}
