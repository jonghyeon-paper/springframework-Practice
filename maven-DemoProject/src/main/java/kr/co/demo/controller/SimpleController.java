package kr.co.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * sample
 * 
 * @author good
 *
 */
@Controller
public class SimpleController {

    @Value("${spring.application.name}")
    private String appName;
    
    @Value("${spring.thymeleaf.view.path.prefix}")
    private String thymeleafViewPathPrefix;

    /**
     * thymeleaf 뷰 리졸버 샘플
     * 뷰의 경로 접미사를 확인하여 뷰리졸버가 동작하기 때문에 꼭 붙여주어야 한다.
     * 
     * @param model
     * @return
     */
    @GetMapping("hello-thymeleaf")
    public String helloThymeleaf(Model model) {
        model.addAttribute("appName", appName);
        return thymeleafViewPathPrefix + "/helloThymeleaf";
    }

    /**
     * jsp 뷰 리졸버 샘플
     * 
     * @param model
     * @return
     */
    @GetMapping("hello-jsp")
    public String helloJsp(Model model) {
        model.addAttribute("appName", appName);
        return "helloJsp";
    }
}
