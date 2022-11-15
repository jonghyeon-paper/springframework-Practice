package com.example.cores.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cores.sample.mybatis.mapper.SampleMapper;

@SpringBootTest(classes = { CoresSamplePersistenceScanner.class })
@SpringBootConfiguration
public class RepositoryMybatisTest {

    @Autowired
    private SampleMapper sampleMapper;

    @Test
    public void doMybatisTest() {
        sampleMapper.selectSampleUseAnnotation().forEach(System.out::println);
        sampleMapper.selectSampleUseXml().forEach(System.out::println);
    }
}
