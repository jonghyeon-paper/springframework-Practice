package com.example.applications.gamma.record.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.applications.gamma.path.ApiVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * RecordApiController
 * 
 * @author _sCream
 *
 */
@RestController
@ApiVersionPrefixRequestMapping
@RequiredArgsConstructor
public class RecordApiController {

    /**
     * retrieveRecord:
     * 
     * @param request
     * @return
     */
    @GetMapping("/record")
    @ResponseBody
    public Object retrieveRecord(HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }

    /**
     * retrieveRecord:
     * 
     * @param identifier
     * @param request
     * @return
     */
    @GetMapping("/record/{identifier}")
    @ResponseBody
    public Object retrieveRecord(@PathVariable String identifier, HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }

    /**
     * addRecord:
     * 
     * @param request
     * @return
     */
    @PostMapping("/record")
    @ResponseBody
    public Object addRecord(HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }
}
