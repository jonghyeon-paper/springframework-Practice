package com.example.cores.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cores.sample.jpa.repository.SampleRepository;

@SpringBootTest(classes = { CoresSamplePersistenceScanner.class })
@SpringBootConfiguration
public class RepositoryJpaTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Test
    public void doTest() {
        sampleRepository.findAll().forEach(System.out::println);
    }
}
