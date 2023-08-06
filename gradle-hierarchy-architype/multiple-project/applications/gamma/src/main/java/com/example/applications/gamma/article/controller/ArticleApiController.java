package com.example.applications.gamma.article.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.applications.gamma.path.ApiVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * ArticleApiController
 * 
 * @author _sCream
 *
 */
@RestController
@ApiVersionPrefixRequestMapping
@RequiredArgsConstructor
public class ArticleApiController {

    /**
     * retrieveArticle:
     * 
     * @param request
     * @return
     */
    @GetMapping("/article")
    @ResponseBody
    public Object retrieveArticle(HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }

    /**
     * retrieveArticle:
     * 
     * @param identifier
     * @param request
     * @return
     */
    @GetMapping("/article/{identifier}")
    @ResponseBody
    public Object retrieveArticle(@PathVariable String identifier, HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }

    /**
     * addArticle:
     * 
     * @param request
     * @return
     */
    @PostMapping("/article")
    @ResponseBody
    public Object addArticle(HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }

    /**
     * modifyArticle:
     * 
     * @param identifier
     * @param request
     * @return
     */
    @RequestMapping(value = "/article/{identifier}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    @ResponseBody
    public Object modifyArticle(@PathVariable String identifier, HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }

    /**
     * removeArticle:
     * 
     * @param identifier
     * @param request
     * @return
     */
    @DeleteMapping("/article/{identifier}")
    @ResponseBody
    public Object removeArticle(@PathVariable String identifier, HttpServletRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("request", request.getServletPath());
        responseData.put("method", request.getMethod());
        responseData.put("message", "success");
        return responseData;
    }

}
