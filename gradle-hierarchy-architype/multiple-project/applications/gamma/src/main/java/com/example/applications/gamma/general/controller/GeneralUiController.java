package com.example.applications.gamma.general.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.applications.gamma.path.UiVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * GeneralUiController
 * 
 * @author _sCream
 *
 */
@Controller
@UiVersionPrefixRequestMapping
@RequiredArgsConstructor
public class GeneralUiController {

    /**
     * viewMain:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/main")
    public String viewMain(Model model) {
        return "main";
    }
}
