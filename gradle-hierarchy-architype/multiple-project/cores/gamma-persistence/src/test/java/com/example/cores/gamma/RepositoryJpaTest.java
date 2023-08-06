package com.example.cores.gamma;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { CoresGammaPersistenceScanner.class })
@SpringBootConfiguration
public class RepositoryJpaTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void doQuerydslTest() {
        System.out.println(entityManager);
    }
}
