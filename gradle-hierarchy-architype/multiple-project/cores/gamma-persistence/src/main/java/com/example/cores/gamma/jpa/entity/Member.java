package com.example.cores.gamma.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * Member
 * 
 * @author _sCream
 *
 */
@Data
@Entity
public class Member {

    @Id
    private String memberId;
    private String password;
    private String name;
    private Integer age;
}
