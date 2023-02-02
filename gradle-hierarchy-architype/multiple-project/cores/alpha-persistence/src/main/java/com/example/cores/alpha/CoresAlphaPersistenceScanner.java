package com.example.cores.alpha;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.modules.datasource.sample.ModuleDataSourceSampleScanner;

/**
 * CoresAlphaPersistenceScanner
 * 
 * created by _sCream on 2022/11/01
 */
@Import(value = {
        ModuleDataSourceSampleScanner.class
})
@ComponentScan(basePackageClasses = { CoresAlphaPersistenceMarker.class })
public class CoresAlphaPersistenceScanner {

}
