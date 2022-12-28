package com.example.cores.sample;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.modules.datasource.sample.ModuleDataSourceSampleScanner;

/**
 * CoresSamplePersistenceScanner
 * 
 * created by _sCream on 2022/11/01
 */
@Import(value = {
        ModuleDataSourceSampleScanner.class
})
@ComponentScan(basePackageClasses = { CoresSamplePersistenceMarker.class })
public class CoresSamplePersistenceScanner {

}
