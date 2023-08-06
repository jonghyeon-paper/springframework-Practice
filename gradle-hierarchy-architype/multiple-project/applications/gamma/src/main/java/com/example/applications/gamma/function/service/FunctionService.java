package com.example.applications.gamma.function.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cores.gamma.jpa.entity.Function;
import com.example.cores.gamma.jpa.repository.FunctionRepository;

import lombok.RequiredArgsConstructor;

/**
 * FunctionService
 * 
 * @author _sCream
 *
 */
@Service
@RequiredArgsConstructor
public class FunctionService {

    private final FunctionRepository functionRepository;

    /**
     * retrieveFunction:
     * 
     * @param conditionData
     * @return
     */
    public List<Function> retrieveFunction(Function conditionData) {
        // todo
        return null;
    }

    /**
     * retrieveFunction:
     * 
     * @param functionId
     * @return
     */
    public Function retrieveFunction(Integer functionId) {
        // todo
        return null;
    }

    /**
     * addFunction:
     * 
     * @param requestData
     * @return
     */
    public Function addFunction(Function requestData) {
        // todo
        return null;
    }

    /**
     * modifyFunction:
     * 
     * @param patchData
     * @return
     */
    public Function modifyFunction(Function patchData) {
        // todo
        return null;
    }

    /**
     * removeFunction:
     * 
     * @param functionId
     */
    public void removeFunction(Integer functionId) {
        // todo
    }
}
