package com.example.applications.hello.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.applications.hello.model.SampleConditionParameter;
import com.example.applications.hello.model.SamplePatch;
import com.example.applications.hello.model.SampleRequest;
import com.example.applications.hello.model.SampleResponse;
import com.example.cores.hello.jpa.entity.Sample;
import com.example.cores.hello.jpa.repository.SampleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * HelloService
 * 
 * created by jonghyeon on 2022/11/01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class HelloService {

    private final SampleRepository sampleRepository;

    /**
     * retieveSample
     * 
     * @param sampleId
     * @return
     */
    private Sample retieveSample(Integer sampleId) {
        Sample findSampleData = sampleRepository.findById(sampleId).orElseThrow(() -> {
            log.error("cannnot found data. sample-id is '{}'.", sampleId);
            return new RuntimeException("cannnot found data. sample-id is '" + sampleId + "'.");
        });
        return findSampleData;
    }

    /**
     * retrieveSample
     * 
     * @param sampleId
     * @return
     */
    public SampleResponse retrieveSample(Integer sampleId) {
        return new SampleResponse(this.retieveSample(sampleId));
    }

    /**
     * retrieveSampleList
     * 
     * @param condition
     * @return
     */
    public List<SampleResponse> retrieveSampleList(SampleConditionParameter condition) {
        Sample conditionData = new Sample();
        BeanUtils.copyProperties(condition, conditionData);
        List<Sample> findSampleList = sampleRepository.findAll(Example.of(conditionData));
        return findSampleList.stream().map(item -> {
            return new SampleResponse(item);
        }).collect(Collectors.toList());
    }

    /**
     * addGreeting
     * 
     * @param requestSampleData
     * @return
     */
    @Transactional
    public SampleResponse addGreeting(SampleRequest requestSampleData) {
        Sample newSampleData = new Sample();
        BeanUtils.copyProperties(requestSampleData, newSampleData);
        newSampleData.setEnabled(true);
        sampleRepository.save(newSampleData);
        return new SampleResponse(newSampleData);
    }

    /**
     * modifyGreeting
     * 
     * @param sampleId
     * @param patchSampleData
     * @return
     */
    @Transactional
    public SampleResponse modifyGreeting(Integer sampleId, SamplePatch patchSampleData) {
        Sample findSampleData = this.retieveSample(sampleId);
        BeanUtils.copyProperties(patchSampleData, findSampleData);
        // sampleRepository.save(findSampleData); // This command is not required. It is automatically patched when the transaction ends.
        return new SampleResponse(findSampleData);
    }

    /**
     * removeGreeting
     * 
     * @param sampleId
     */
    @Transactional
    public void removeGreeting(Integer sampleId) {
        Sample findSampleData = this.retieveSample(sampleId);
        findSampleData.delete();
        // sampleRepository.save(findSampleData); // This command is not required. It is automatically patched when the transaction ends.
    }
}
