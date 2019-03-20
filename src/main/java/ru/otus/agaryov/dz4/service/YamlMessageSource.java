package ru.otus.agaryov.dz4.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Properties;

@Service
public class YamlMessageSource implements MessageSource {

    private final String messagesFile;
    private Properties yamlProperties;
    private MessageSource messageSource;
    private Locale locale;

    public YamlMessageSource(@Value("${config.messages}") String messagesFile) {
        this.messagesFile = messagesFile;
        this.locale = Locale.getDefault();
        String language = locale.getLanguage();
        setMessageSourceByLang(language);
    }

    @Override
    public String getMessage(String s, Object[] objects, String s1, Locale locale) {
        return messageSource.getMessage(s, objects, s1, this.locale);
    }

    @Override
    public String getMessage(String s, Object[] objects, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(s, objects, this.locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable messageSourceResolvable, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(messageSourceResolvable, this.locale);
    }


    String getMessage(String s) throws NoSuchMessageException {
        return messageSource.getMessage(s, null, this.locale);
    }


    void setMessageSourceByLang(String language) {

        String messagesYaml;
        if (language.toLowerCase().matches("^en$")) {
            messagesYaml = messagesFile +
                    ".yaml";
        } else {
            messagesYaml = messagesFile
                    + "_" + language + ".yaml";
        }

        locale = new Locale.Builder().
                setLanguage(language.toLowerCase()).
                setRegion(language.toUpperCase()).build();

        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(new ClassPathResource(messagesYaml));
        yamlProperties = bean.getObject();
        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setCommonMessages(yamlProperties);
        ms.setDefaultEncoding("UTF-8");
        messageSource = ms;
    }


}
