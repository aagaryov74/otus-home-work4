package ru.otus.agaryov.dz4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
