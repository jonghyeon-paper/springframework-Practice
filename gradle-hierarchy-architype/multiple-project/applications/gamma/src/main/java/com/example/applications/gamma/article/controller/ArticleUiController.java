package com.example.applications.gamma.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.applications.gamma.path.UiVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * ArticleUiController
 * 
 * @author _sCream
 *
 */
@Controller
@UiVersionPrefixRequestMapping
@RequiredArgsConstructor
public class ArticleUiController {

    /**
     * viewArticle:
     * 
     * @param model
     * @return
     */
    @RequestMapping("/article")
    public String viewArticle(Model model) {
        model.addAttribute("name", "article");
        return "view/dummy";
    }
}
