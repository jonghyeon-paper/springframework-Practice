package com.example.applications.sample.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.applications.sample.model.SampleCondition;
import com.example.applications.sample.model.SamplePatch;
import com.example.applications.sample.model.SampleRequest;
import com.example.applications.sample.model.SampleResponse;

/**
 * SampleApiTest
 * 
 * created by jonghyeon on 2022/11/14
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SampleApiTest {

    @Autowired
    private SampleService helloService;

    @Test
    public void readTest() {
        SampleResponse sampleData = helloService.retrieveSample(2);
        System.out.println("read data");
        System.out.println(sampleData);
    }

    @Test
    public void readListTest() {
        SampleCondition condition = new SampleCondition();
        condition.setName("tea");
        List<SampleResponse> sampleList = helloService.retrieveSampleList(condition);
        System.out.println("read list");
        sampleList.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void addTest() {
        SampleRequest requestData = new SampleRequest();
        requestData.setName("bubble");
        requestData.setAltername("bubble2");
        SampleResponse newData = helloService.addGreeting(requestData);
        System.out.println("new data");
        System.out.println(newData);
    }

    @Test
    @Transactional
    public void modifyTest() {
        Integer promaryKey = 2;
        SamplePatch patchData = new SamplePatch();
        patchData.setName("000000");
        SampleResponse modifiedData = helloService.modifyGreeting(promaryKey, patchData);
        System.out.println("patch data");
        System.out.println(modifiedData);
    }

    @Test
    @Transactional
    public void removeTest() {
        Integer promaryKey = 2;
        helloService.removeGreeting(promaryKey);
    }
}
