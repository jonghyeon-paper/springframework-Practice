package com.example.cores.alpha;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cores.alpha.mybatis.mapper.SampleMapper;

@SpringBootTest(classes = { CoresAlphaPersistenceScanner.class })
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
