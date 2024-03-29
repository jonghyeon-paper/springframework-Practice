package com.example.applications.beta.sample.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.applications.beta.sample.model.SampleCondition;
import com.example.applications.beta.sample.model.SamplePatch;
import com.example.applications.beta.sample.model.SampleRequest;
import com.example.applications.beta.sample.model.SampleResponse;
import com.example.cores.beta.jpa.entity.Sample;
import com.example.cores.beta.jpa.repository.SampleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SampleService
 * 
 * created by jonghyeon on 2022/11/14
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SampleService {

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
     * @param conditionData
     * @return
     */
    public List<SampleResponse> retrieveSampleList(SampleCondition conditionData) {
        Sample condition = new Sample();
        BeanUtils.copyProperties(conditionData, condition);
        List<Sample> findSampleList = sampleRepository.findAll(Example.of(condition));
        return findSampleList.stream().map(item -> {
            return new SampleResponse(item);
        }).collect(Collectors.toList());
    }

    /**
     * addSample
     * 
     * @param requestData
     * @return
     */
    @Transactional
    public SampleResponse addSample(SampleRequest requestData) {
        Sample newData = new Sample();
        BeanUtils.copyProperties(requestData, newData);
        newData.setEnabled(true);
        sampleRepository.save(newData);
        return new SampleResponse(newData);
    }

    /**
     * modifySample
     * 
     * @param sampleId
     * @param patchData
     * @return
     */
    @Transactional
    public SampleResponse modifySample(Integer sampleId, SamplePatch patchData) {
        Sample foundData = this.retieveSample(sampleId);
        BeanUtils.copyProperties(patchData, foundData);
        // sampleRepository.save(findSampleData); // This command is not required. It is automatically patched when the transaction ends.
        return new SampleResponse(foundData);
    }

    /**
     * removeSample
     * 
     * @param sampleId
     */
    @Transactional
    public void removeSample(Integer sampleId) {
        Sample findSampleData = this.retieveSample(sampleId);
        findSampleData.delete();
        // sampleRepository.save(findSampleData); // This command is not required. It is automatically patched when the transaction ends.
    }
}
