package com.example.applications.gamma.stats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.applications.gamma.path.UiVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * StatsUiController
 * 
 * @author _sCream
 *
 */
@Controller
@UiVersionPrefixRequestMapping
@RequiredArgsConstructor
public class StatsUiController {

    /**
     * viewStatisticsMonth:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/statistics-month")
    public String viewStatisticsMonth(Model model) {
        model.addAttribute("name", "statistics-month");
        return "view/dummy";
    }

    /**
     * viewStatisticsYear:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/statistics-year")
    public String viewStatisticsYear(Model model) {
        model.addAttribute("name", "statistics-year");
        return "view/dummy";
    }

}
