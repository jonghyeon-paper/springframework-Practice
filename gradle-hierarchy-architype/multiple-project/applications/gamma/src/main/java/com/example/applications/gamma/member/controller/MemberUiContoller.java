package com.example.applications.gamma.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.applications.gamma.path.UiVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * MemberUiContoller
 * 
 * @author _sCream
 *
 */
@Controller
@UiVersionPrefixRequestMapping
@RequiredArgsConstructor
public class MemberUiContoller {

    /**
     * viewPersionalInfo:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/member/general-info")
    public String viewGeneralInfo(Model model) {
        model.addAttribute("name", "general-info");
        return "view/dummy";
    }

    /**
     * viewSecurityInfo:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/member/security-info")
    public String viewSecurityInfo(Model model) {
        model.addAttribute("name", "security-info");
        return "view/dummy";
    }
}
