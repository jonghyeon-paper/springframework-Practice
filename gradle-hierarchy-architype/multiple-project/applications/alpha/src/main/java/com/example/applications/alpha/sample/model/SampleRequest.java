package com.example.applications.alpha.sample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SampleRequest
 * 
 * created by jonghyeon on 2022/11/14
 */
@Slf4j
@Data
@NoArgsConstructor
public class SampleRequest implements ParameterValidation {

    private String name;
    private String altername;

    /* (non-Javadoc)
     * @see com.example.applications.alpha.sample.model.ParameterValidation#isValidParameter()
     */
    @Override
    public boolean isValidParameter() {
        if (name == null) {
            log.error("name is null.");
            return false;
        }
        if (altername == null) {
            log.error("altername is null.");
            return false;
        }
        return true;
    }
}
