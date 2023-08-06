package com.example.applications.gamma.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.applications.gamma.path.ApiVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * MemberApiController
 * 
 * @author _sCream
 *
 */
@RestController
@ApiVersionPrefixRequestMapping
@RequiredArgsConstructor
public class MemberApiController {

    /**
     * retrieveGeneralInfo:
     * 
     * @param request
     * @return
     */
    @GetMapping("/member/general-info")
    @ResponseBody
    public Object retrieveGeneralInfo(HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }

    /**
     * modifyGeneralInfo:
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/member/general-info", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @ResponseBody
    public Object modifyGeneralInfo(HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }

    /**
     * retrieveSecurityInfo:
     * 
     * @param request
     * @return
     */
    @GetMapping("/member/security-info")
    @ResponseBody
    public Object retrieveSecurityInfo(HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }

    /**
     * modifySecurityInfo:
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/member/security-info", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @ResponseBody
    public Object modifySecurityInfo(HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }
}
