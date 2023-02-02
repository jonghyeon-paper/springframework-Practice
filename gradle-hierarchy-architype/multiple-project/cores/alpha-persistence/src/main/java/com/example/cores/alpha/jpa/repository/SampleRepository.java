package com.example.cores.alpha.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cores.alpha.jpa.entity.Sample;

/**
 * SampleRepository
 * 
 * created by jonghyeon on 2022/11/01
 */
public interface SampleRepository extends JpaRepository<Sample, Integer> {

}
