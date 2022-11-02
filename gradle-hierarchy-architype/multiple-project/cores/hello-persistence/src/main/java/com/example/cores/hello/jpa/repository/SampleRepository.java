package com.example.cores.hello.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cores.hello.jpa.entity.Sample;

public interface SampleRepository extends JpaRepository<Sample, String> {

}
