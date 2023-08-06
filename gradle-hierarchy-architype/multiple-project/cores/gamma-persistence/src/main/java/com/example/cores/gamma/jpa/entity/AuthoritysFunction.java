package com.example.cores.gamma.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * AuthoritysFunction
 * 
 * @author _sCream
 *
 */
@Data
@Entity
public class AuthoritysFunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authoritysFunctionId;
    private String authorityId;
    private Integer functionId;
}
