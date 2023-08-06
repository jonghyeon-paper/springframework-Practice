package com.example.cores.gamma;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.example.modules.datasource.security.ModuleDataSourceSecurityScanner;

/**
 * CoresGammaPersistenceScanner
 * 
 * @author _sCream
 */
@Import(value = {
        ModuleDataSourceSecurityScanner.class
})
@ComponentScan(basePackageClasses = { CoresGammaPersistenceMarker.class })
public class CoresGammaPersistenceScanner {

}
