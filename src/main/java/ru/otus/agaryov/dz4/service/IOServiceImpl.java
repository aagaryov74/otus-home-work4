package ru.otus.agaryov.dz4.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.out;

@Service
public class IOServiceImpl implements IOService {
    private final YamlMessageSource messageSource;
    private BufferedReader bufferedReader;

    public IOServiceImpl(YamlMessageSource messageSource) {
        this.messageSource = messageSource;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void printToConsole(String propertyParam) {
        out.println(messageSource.getMessage(propertyParam));

    }

    @Override
    public void printFToConsole(String propertyParam, Object... args) {
        out.printf(messageSource.getMessage(propertyParam), args);
    }

    @Override
    public String printSToConsole(String propertyParam, Object... args) {
        return String.format(messageSource.getMessage(propertyParam), args);
    }

    @Override
    public String readFromConsole() throws IOException {
        return bufferedReader.readLine();
    }

}