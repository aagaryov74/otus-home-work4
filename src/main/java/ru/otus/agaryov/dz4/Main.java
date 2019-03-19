package ru.otus.agaryov.dz4;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

@SpringBootApplication
public class Main {
/*
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
    */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
