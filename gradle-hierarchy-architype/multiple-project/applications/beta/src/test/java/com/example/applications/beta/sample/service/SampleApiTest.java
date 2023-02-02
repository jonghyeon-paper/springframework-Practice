package com.example.applications.beta.sample.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.applications.beta.sample.model.SampleCondition;
import com.example.applications.beta.sample.model.SamplePatch;
import com.example.applications.beta.sample.model.SampleRequest;
import com.example.applications.beta.sample.model.SampleResponse;

/**
 * SampleApiTest
 * 
 * created by jonghyeon on 2022/11/14
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SampleApiTest {

    @Autowired
    private SampleService sampleService;

    @Test
    public void readTest() {
        SampleResponse sampleData = sampleService.retrieveSample(2);
        System.out.println("read data");
        System.out.println(sampleData);
    }

    @Test
    public void readListTest() {
        SampleCondition condition = new SampleCondition();
        condition.setName("tea");
        List<SampleResponse> sampleList = sampleService.retrieveSampleList(condition);
        System.out.println("read list");
        sampleList.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void addTest() {
        SampleRequest requestData = new SampleRequest();
        requestData.setName("bubble");
        requestData.setAltername("bubble2");
        SampleResponse newData = sampleService.addSample(requestData);
        System.out.println("new data");
        System.out.println(newData);
    }

    @Test
    @Transactional
    public void modifyTest() {
        Integer promaryKey = 2;
        SamplePatch patchData = new SamplePatch();
        patchData.setName("000000");
        SampleResponse modifiedData = sampleService.modifySample(promaryKey, patchData);
        System.out.println("patch data");
        System.out.println(modifiedData);
    }

    @Test
    @Transactional
    public void removeTest() {
        Integer promaryKey = 2;
        sampleService.removeSample(promaryKey);
    }
}
