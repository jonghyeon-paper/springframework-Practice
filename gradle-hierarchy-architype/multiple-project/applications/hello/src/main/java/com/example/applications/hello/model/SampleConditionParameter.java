package com.example.applications.hello.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SampleConditionParameter
 * 
 * created by jonghyeon on 2022/11/01
 */
@Slf4j
@Data
@NoArgsConstructor
public class SampleConditionParameter implements ParameterValidation {

    private String nickname;
    private String name;
    private Boolean enabled;

    /* (non-Javadoc)
     * @see com.example.applications.hello.model.ParameterValidation#isValidParameter()
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
