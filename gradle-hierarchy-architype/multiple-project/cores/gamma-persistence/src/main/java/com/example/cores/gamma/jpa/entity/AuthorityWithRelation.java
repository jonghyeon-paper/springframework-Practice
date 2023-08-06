package com.example.cores.gamma.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * AuthorityWithRelation
 * 
 * @author _sCream
 *
 */
@Data
@Entity
@Table(name = "authority")
public class AuthorityWithRelation {

    @Id
    private String authorityId;
    private String name;
    private Boolean enabled;
    @OneToMany
    @JoinColumn(name = "authorityId")
    List<AuthoritysFunction> authoritysFunctionList;
}
