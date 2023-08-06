package com.example.applications.gamma.stats.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.applications.gamma.path.ApiVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * StatsApiController
 * 
 * @author _sCream
 *
 */
@RestController
@ApiVersionPrefixRequestMapping
@RequiredArgsConstructor
public class StatsApiController {

    /**
     * retrieveStatistics:
     * 
     * @param request
     * @return
     */
    @GetMapping("/statistics")
    @ResponseBody
    public Object retrieveStatistics(HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }
}
