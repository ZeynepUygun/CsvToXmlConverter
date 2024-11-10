package com.magarsus.fileconverter.config;

import com.magarsus.fileconverter.constants.ConverterStatic;
import freemarker.template.utility.XmlEscape;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FreeMarkerConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath(ConverterStatic.CLASSPATH_FILES);
        Map<String, Object> variables = new HashMap<>();
        variables.put(ConverterStatic.XML_ESCAPE, new XmlEscape());
        configurer.setFreemarkerVariables(variables);
        return configurer;
    }
}
