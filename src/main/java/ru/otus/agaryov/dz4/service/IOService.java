package ru.otus.agaryov.dz4.service;

import java.io.IOException;

public interface IOService {

    void printToConsole(String propertyParam);

    void printFToConsole(String propertyParam, Object... args);

    String printSToConsole(String propertyParam, Object... args);

    String readFromConsole() throws IOException;
}
