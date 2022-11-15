package com.example.applications.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cores.hello.mybatis.mapper.SampleMapper;

/**
 * DatabaseMybatisWorkingTest
 * 
 * created by jonghyeon on 2022/11/01
 */
@SpringBootTest
@AutoConfigureMockMvc
public class DatabaseMybatisWorkingTest {

    @Autowired
    private SampleMapper sampleMapper;

    @Test
    public void doTest() {
        sampleMapper.selectSampleUseAnnotation().forEach(System.out::println);
        sampleMapper.selectSampleUseXml().forEach(System.out::println);
    }
}
