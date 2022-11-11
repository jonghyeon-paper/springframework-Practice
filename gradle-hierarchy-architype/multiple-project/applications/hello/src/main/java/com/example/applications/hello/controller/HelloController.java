package com.example.applications.hello.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.applications.hello.model.GreetingResponse;
import com.example.applications.hello.model.SampleConditionParameter;
import com.example.applications.hello.model.SamplePatch;
import com.example.applications.hello.model.SampleRequest;
import com.example.applications.hello.model.SampleResponse;
import com.example.applications.hello.service.HelloService;

import lombok.RequiredArgsConstructor;

/**
 * HelloController
 * 
 * created by jonghyeon on 2022/11/01
 */
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    /**
     * retrieveSample
     * 
     * @param sampleId
     * @return
     */
    @GetMapping("/sample/{sampleId}")
    @ResponseBody
    public SampleResponse retrieveSample(@PathVariable Integer sampleId) {
        return helloService.retrieveSample(sampleId);
    }

    /**
     * retrieveSampleList
     * 
     * @param condition
     * @return
     */
    @GetMapping("/sample")
    @ResponseBody
    public List<SampleResponse> retrieveSampleList(@RequestParam SampleConditionParameter condition) {
        if (!condition.isValidParameter()) {
            throw new RuntimeException("parameter invalid.");
        }
        return helloService.retrieveSampleList(condition);
    }

    /**
     * addSample
     * 
     * @param sampleId
     * @param requestData
     * @return
     */
    @PostMapping("/sample")
    @ResponseBody
    public Object addSample(@RequestBody SampleRequest requestData) {
        return helloService.addGreeting(requestData);
    }

    /**
     * modifySample
     * 
     * @param sampleId
     * @param patchData
     * @return
     */
    @PutMapping("/sample/{sampleId}")
    @ResponseBody
    public Object modifySample(@PathVariable Integer sampleId, @RequestBody SamplePatch patchData) {
        return helloService.modifyGreeting(sampleId, patchData);
    }

    /**
     * removeSample
     * 
     * @param sampleId
     */
    @DeleteMapping("/sample/{sampleId}")
    @ResponseBody
    public void removeSample(@PathVariable Integer sampleId) {
        helloService.removeGreeting(sampleId);
    }

    /**
     * getHelloMessage
     * 
     * @return
     */
    @GetMapping("/hello")
    public GreetingResponse getHelloMessage() {
        return helloService.getHelloMessage();
    }
}
