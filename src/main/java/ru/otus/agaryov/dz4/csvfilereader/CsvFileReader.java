package ru.otus.agaryov.dz4.csvfilereader;

import java.util.Map;

public interface CsvFileReader {
    // Read csv file into a Map
    Map<String, String> readCsvIntoMap();

    // How many correct strings are in config file?
    Integer getReadedStrsCount();

    // change file if we need to change locale in runtime;
    Map<String, String> setCsvFile(String fileName);
}
