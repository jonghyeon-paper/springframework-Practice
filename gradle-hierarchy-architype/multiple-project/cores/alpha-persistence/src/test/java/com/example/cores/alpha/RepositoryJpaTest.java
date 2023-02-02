package com.example.cores.alpha;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cores.alpha.jpa.repository.SampleRepository;

@SpringBootTest(classes = { CoresAlphaPersistenceScanner.class })
@SpringBootConfiguration
public class RepositoryJpaTest {

    @Autowired
    private SampleRepository sampleRepository;

    @Test
    public void doJpaTest() {
        sampleRepository.findAll().forEach(System.out::println);
    }
}
