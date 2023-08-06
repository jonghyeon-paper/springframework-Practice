package com.example.cores.gamma.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cores.gamma.jpa.entity.AuthoritysFunction;

/**
 * AuthoritysFunctionRepository
 * 
 * @author _sCream
 * 
 */
public interface AuthoritysFunctionRepository extends JpaRepository<AuthoritysFunction, Integer> {

    List<AuthoritysFunction> findByFunctionIdIn(List<Integer> functionIdList);
    List<AuthoritysFunction> findByAuthorityIdIn(List<String> authorityIdList);
}
