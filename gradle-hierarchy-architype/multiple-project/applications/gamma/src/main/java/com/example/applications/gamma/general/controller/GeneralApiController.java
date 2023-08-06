package com.example.applications.gamma.general.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.applications.gamma.config.SecurityConfig;
import com.example.applications.gamma.function.model.FunctionHierarchy;
import com.example.applications.gamma.general.service.GeneralService;
import com.example.applications.gamma.path.ApiVersionPrefixRequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * GeneralApiController
 * 
 * @author _sCream
 *
 */
@RestController
@ApiVersionPrefixRequestMapping
@RequiredArgsConstructor
public class GeneralApiController {

    private final GeneralService generalService;

    /**
     * retrieveAccessableMenu:
     * 
     * @param authentication
     * @return
     */
    @GetMapping("/accessable-menu")
    @ResponseBody
    public FunctionHierarchy retrieveAccessableMenu(Authentication authentication) {
        String[] authorities = authentication.getAuthorities().stream()
                .map(item -> item.getAuthority().replaceFirst(SecurityConfig.ROLE_NAME_PREFIX, ""))
                .toArray(String[]::new);
        return generalService.retrieveAccessableMenu(authorities);
    }
}
