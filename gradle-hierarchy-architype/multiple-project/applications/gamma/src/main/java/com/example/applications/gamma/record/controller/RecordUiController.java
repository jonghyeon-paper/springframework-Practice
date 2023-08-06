package com.example.applications.gamma.record.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.applications.gamma.path.UiVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * RecordUiController
 * 
 * @author _sCream
 *
 */
@Controller
@UiVersionPrefixRequestMapping
@RequiredArgsConstructor
public class RecordUiController {

    /**
     * viewAlpha:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/record/alpha")
    public String viewAlpha(Model model) {
        model.addAttribute("name", "alpha");
        return "view/dummy";
    }

    /**
     * viewBeta:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/record/beta")
    public String viewBeta(Model model) {
        model.addAttribute("name", "beta");
        return "view/dummy";
    }

    /**
     * viewGamma:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/record/gamma")
    public String viewGamma(Model model) {
        model.addAttribute("name", "gamma");
        return "view/dummy";
    }
}
