package ru.otus.agaryov.dz4.service;


import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;
@Configuration
public class YamlMessageSource {

    @Bean
    public Properties yamlProperties() {
        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(new ClassPathResource("messages.yaml"));
        return bean.getObject();
    }

    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setCommonMessages(yamlProperties());
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

}
