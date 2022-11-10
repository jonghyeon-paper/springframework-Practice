package com.example.applications.hello.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * SampleRequest
 * 
 * created by jonghyeon on 2022/11/01
 */
@Slf4j
@Data
@NoArgsConstructor
public class SampleRequest implements ParameterValidation {

    private String nickname;
    private String name;

    /* (non-Javadoc)
     * @see com.example.applications.hello.model.ParameterValidation#isValidParameter()
     */
    @Override
    public boolean isValidParameter() {
        if (nickname == null) {
            log.error("nickname is null.");
            return false;
        }
        if (name == null) {
            log.error("name is null.");
            return false;
        }
        return true;
    }
}
