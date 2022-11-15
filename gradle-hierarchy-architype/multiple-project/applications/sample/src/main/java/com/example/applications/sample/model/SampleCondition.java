package com.example.applications.sample.model;

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

    private String nickname;
    private String name;
    private Boolean enabled;

    /* (non-Javadoc)
     * @see com.example.applications.sample.model.ParameterValidation#isValidParameter()
     */
    @Override
    public boolean isValidParameter() {
        if (nickname == null && name == null && enabled == null) {
            log.error("All parameters are null. At least one parameter must be non-null.");
            return false;
        }
        return true;
    }
}
