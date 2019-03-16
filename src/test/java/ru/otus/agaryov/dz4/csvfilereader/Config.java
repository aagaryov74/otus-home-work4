package ru.otus.agaryov.dz4.csvfilereader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;

@Configuration
public class Config {

    @Value("${csvfile}")
    private String csvfile;

    @Bean(name = "ruCSVFileReader")
    CsvFileReader testRUCsvFileReader() {
        return new ImplCsvFileReader(csvfile, "ru");
    }

    @Bean(name = "enCSVFileReader")
    CsvFileReader testENCsvFileReader() {
        return new ImplCsvFileReader(csvfile, "en");
    }

    @Component
    public class MapConfig {
        private Map<String, Map<String, String>> maps;

        public MapConfig() {

            Yaml yaml = new Yaml();
            InputStream inputStream = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream("testquestions.yaml");
            this.maps = yaml.load(inputStream);
        }

        Map<String, String> getMapByLang(String lang) {
            return maps.get(lang);
        }

        Set<String> getLanguages() {
            return maps.keySet();
        }
    }

}
