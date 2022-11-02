package com.example.cores.hello.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cores.hello.jpa.entity.Sample;

/**
 * SampleRepository
 * 
 * created by jonghyeon on 2022/11/01
 */
public interface SampleRepository extends JpaRepository<Sample, String> {

}
