package com.example.cores.hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.modules.datasource.memory.ModuleDataSourceMemoryScanner;

/**
 * CoresHelloPersistenceScanner
 * 
 * created by _sCream on 2022/11/01
 */
@Import(value = {
        ModuleDataSourceMemoryScanner.class
})
@ComponentScan(basePackageClasses = { CoresHelloPersistenceMarker.class })
public class CoresHelloPersistenceScanner {

}
