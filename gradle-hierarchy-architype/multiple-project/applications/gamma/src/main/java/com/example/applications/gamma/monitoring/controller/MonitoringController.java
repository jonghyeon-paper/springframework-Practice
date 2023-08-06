package com.example.applications.gamma.monitoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.applications.gamma.monitoring.service.MonitoringService;
import com.example.applications.gamma.path.MonitoringVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * MonitoringApiController
 * 
 * @author _sCream
 *
 */
@Controller
@MonitoringVersionPrefixRequestMapping
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringService monitoringService;

    /**
     * viewDatabase:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/database")
    public String viewDatabase(Model model) {
        monitoringService.retrieveDatabase().entrySet().stream().forEach(item -> {
            model.addAttribute(item.getKey(), item.getValue());
        });
        return "monitoring/database";
    }

    /**
     * viewCoupledData:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/coupled-data")
    public String viewCoupledData(Model model) {
        model.addAttribute("memberWithAuthority", monitoringService.retrieveMemberWithAothority());
        model.addAttribute("authorityWithFunction", monitoringService.retrieveAuthorityWithFunction());
        return "monitoring/coupled-data";
    }

    /**
     * viewMenuHierarchy:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/menu-hierarchy")
    public String viewMenuHierarchy(Model model) {
        model.addAttribute("menuHierarchy", monitoringService.retrieveMenuHierarchy());
        return "monitoring/menu-hierarchy";
    }
}
