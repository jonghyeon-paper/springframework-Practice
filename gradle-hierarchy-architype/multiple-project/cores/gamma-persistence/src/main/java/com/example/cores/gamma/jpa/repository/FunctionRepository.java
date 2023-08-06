package com.example.cores.gamma.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cores.gamma.jpa.entity.Function;

/**
 * FunctionRepository
 * 
 * @author _sCream
 * 
 */
public interface FunctionRepository extends JpaRepository<Function, Integer> {

    List<Function> findByFunctionIdIn(List<Integer> functionIdList);
}
