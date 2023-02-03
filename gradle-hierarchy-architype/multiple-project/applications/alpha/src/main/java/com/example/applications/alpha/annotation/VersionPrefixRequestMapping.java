package com.example.applications.alpha.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * VersionPrefixRequestMapping
 * 
 * created by jonghyeon on 2023/02/03
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(value = "/v2")
public @interface VersionPrefixRequestMapping {

}
