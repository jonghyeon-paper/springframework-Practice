package com.example.applications.gamma.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.applications.gamma.path.AuthVersionPrefixRequestMapping;

/**
 * LoginUiController
 * 
 * @author _sCream
 *
 */
@Controller
@AuthVersionPrefixRequestMapping
public class LoginUiController {

    /**
     * viewLogin:
     * 
     * @return
     */
    @RequestMapping("/login")
    public String viewLogin() {
        return "login";
    }
}
