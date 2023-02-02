package com.example.applications.alpha.sample.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.applications.alpha.sample.model.SampleCondition;
import com.example.applications.alpha.sample.model.SamplePatch;
import com.example.applications.alpha.sample.model.SampleRequest;
import com.example.applications.alpha.sample.model.SampleResponse;
import com.example.applications.alpha.sample.service.SampleService;

import lombok.RequiredArgsConstructor;

/**
 * SampleController
 * 
 * created by jonghyeon on 2022/11/14
 */
@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    /**
     * retrieveSample
     * 
     * @param sampleId
     * @return
     */
    @GetMapping("/sample/{sampleId}")
    @ResponseBody
    public SampleResponse retrieveSample(@PathVariable Integer sampleId) {
        return sampleService.retrieveSample(sampleId);
    }

    /**
     * retrieveSampleList
     * 
     * @param conditionData
     * @return
     */
    @GetMapping("/sample")
    @ResponseBody
    public List<SampleResponse> retrieveSampleList(SampleCondition conditionData) {
        if (!conditionData.isValidParameter()) {
            throw new RuntimeException("parameter invalid.");
        }
        return sampleService.retrieveSampleList(conditionData);
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
        return sampleService.addGreeting(requestData);
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
        return sampleService.modifyGreeting(sampleId, patchData);
    }

    /**
     * removeSample
     * 
     * @param sampleId
     */
    @DeleteMapping("/sample/{sampleId}")
    @ResponseBody
    public void removeSample(@PathVariable Integer sampleId) {
        sampleService.removeGreeting(sampleId);
    }
}
