package com.example.cores.gamma.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.cores.gamma.jpa.constant.FunctionType;
import com.example.cores.gamma.jpa.constant.RequestMethodType;

import lombok.Data;

/**
 * Function
 * 
 * @author _sCream
 *
 */
@Data
@Entity
public class Function {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer functionId;
    private Integer parentFunctionId;
    private String name;
    @Enumerated(EnumType.STRING)
    private FunctionType type;
    private String path;
    @Enumerated(EnumType.STRING)
    private RequestMethodType method;
    private Boolean enabled;
}
