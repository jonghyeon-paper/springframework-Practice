package com.example.applications.hello.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.applications.hello.model.SampleConditionParameter;
import com.example.applications.hello.model.SamplePatch;
import com.example.applications.hello.model.SampleRequest;
import com.example.applications.hello.model.SampleResponse;

/**
 * SampleApiTest
 * 
 * created by jonghyeon on 2022/11/10
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SampleApiTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void readTest() {
        SampleResponse sampleData = helloService.retrieveSample(2);
        System.out.println("read data");
        System.out.println(sampleData);
    }

    @Test
    public void readListTest() {
        SampleConditionParameter condition = new SampleConditionParameter();
        condition.setName("sam");
        List<SampleResponse> sampleList = helloService.retrieveSampleList(condition);
        System.out.println("read list");
        sampleList.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void addTest() {
        SampleRequest requestData = new SampleRequest();
        requestData.setNickname("abc");
        requestData.setName("efg");
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
