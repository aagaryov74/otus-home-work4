package ru.otus.agaryov.dz4;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

@SpringBootApplication
@PropertySource("application.yaml")
public class Main {
/*

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
*/

    public static void main(String[] args) {
        //ApplicationContext context =
        SpringApplication.run(Main.class, args);
        //ExamExecutor executor = context.getBean(ExamExecutor.class);
        //executor.doExam();
    }

    @Bean(name = "quizProperties")
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
        ms.setBasename("messages/messages");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
