package com.example.cores.beta;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.modules.datasource.sample.ModuleDataSourceSampleScanner;

/**
 * CoresBetaPersistenceScanner
 * 
 * created by _sCream on 2022/11/01
 */
@Import(value = {
        ModuleDataSourceSampleScanner.class
})
@ComponentScan(basePackageClasses = { CoresBetaPersistenceMarker.class })
public class CoresBetaPersistenceScanner {

}
