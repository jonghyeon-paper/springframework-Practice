package com.example.modules.messagesource.sample;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import com.example.modules.security.ModuleSecurityScanner;

@SpringBootTest(classes = { ModuleSecurityScanner.class })
@SpringBootConfiguration
public class MessageSourceTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    public void doTest() {
        System.out.println(messageSource.getMessage("text.sample", null, Locale.ENGLISH));
    }
}
