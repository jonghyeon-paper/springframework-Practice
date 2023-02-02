package com.example.applications.beta.sample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SampleCondition
 * 
 * created by jonghyeon on 2022/11/14
 */
@Slf4j
@Data
@NoArgsConstructor
public class SampleCondition implements ParameterValidation {

    private String name;
    private String altername;
    private Boolean enabled;

    /* (non-Javadoc)
     * @see com.example.applications.beta.sample.model.ParameterValidation#isValidParameter()
     */
    @Override
    public boolean isValidParameter() {
        if (name == null && altername == null && enabled == null) {
            log.error("All parameters are null. At least one parameter must be non-null.");
            return false;
        }
        return true;
    }
}
