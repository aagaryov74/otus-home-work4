package ru.otus.agaryov.dz4.service;

import java.io.IOException;
import java.util.Locale;

public interface IOService {

    void printToConsole(String propertyParam);
    void printFToConsole(String propertyParam, Object ... args);
    String readFromConsole() throws IOException;
    void setLocale(Locale locale);

}
