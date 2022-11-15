package com.example.applications.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cores.hello.jpa.repository.SampleRepository;

/**
 * DatabaseJpaWorkingTest
 * 
 * created by jonghyeon on 2022/11/01
 */
@SpringBootTest
@AutoConfigureMockMvc
public class DatabaseJpaWorkingTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Test
    public void doTest() {
        sampleRepository.findAll().forEach(System.out::println);
    }
}
