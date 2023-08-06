package com.example.cores.gamma.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * Authority
 * 
 * @author _sCream
 *
 */
@Data
@Entity
public class Authority {

    @Id
    private String authorityId;
    private String name;
    private Boolean enabled;
}
