package com.example.cores.gamma.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * MembersAuthority
 * 
 * @author _sCream
 *
 */
@Data
@Entity
public class MembersAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer membersAuthorityId;
    private String memberId;
    private String authorityId;
}
