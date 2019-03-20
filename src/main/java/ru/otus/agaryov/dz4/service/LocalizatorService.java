package ru.otus.agaryov.dz4.service;

import java.util.Locale;

public interface LocalizatorService {
    Boolean setLanguage(String language);

    String getCSVFile();

    Locale getLocale();
}
