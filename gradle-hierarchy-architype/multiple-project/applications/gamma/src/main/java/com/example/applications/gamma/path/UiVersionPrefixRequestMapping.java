package com.example.applications.gamma.path;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * UiVersionPrefixRequestMapping:
 * 
 * @author _sCream
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(value = StaticConst.UI_PATH)
public @interface UiVersionPrefixRequestMapping {

}
