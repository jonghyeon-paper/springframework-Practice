package com.example.cores.gamma;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.modules.datasource.sample.ModuleDataSourceSampleScanner;

/**
 * CoresGammaPersistenceScanner
 * 
 * @author _sCream
 */
@Import(value = {
        ModuleDataSourceSampleScanner.class
})
@ComponentScan(basePackageClasses = { CoresGammaPersistenceMarker.class })
public class CoresGammaPersistenceScanner {

}
