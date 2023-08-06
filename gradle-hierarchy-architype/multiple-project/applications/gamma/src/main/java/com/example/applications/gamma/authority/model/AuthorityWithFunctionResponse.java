package com.example.applications.gamma.authority.model;

import java.util.List;

import com.example.applications.gamma.function.model.FunctionHierarchy;
import com.example.cores.gamma.jpa.constant.FunctionType;
import com.example.cores.gamma.jpa.constant.RequestMethodType;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthorityWithFunctionResponse
 * 
 * @author _sCream
 *
 */
@Data
@NoArgsConstructor
public class AuthorityWithFunctionResponse {

    private String authorityId;
    private String name;
    private List<Function> hasApiList;
    private FunctionHierarchy hasMenu;

    /**
     * Function:
     * 
     * @author _Scream
     *
     */
    @Data
    @NoArgsConstructor
    public static class Function {

        private Integer functionId;
        private String name;
        private FunctionType type;
        private String path;
        private RequestMethodType method;
    }
}
