package com.example.cores.sample;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.modules.datasource.memory.ModuleDataSourceMemoryScanner;

/**
 * CoresSamplePersistenceScanner
 * 
 * created by _sCream on 2022/11/01
 */
@Import(value = {
        ModuleDataSourceMemoryScanner.class
})
@ComponentScan(basePackageClasses = { CoresSamplePersistenceMarker.class })
public class CoresSamplePersistenceScanner {

}
