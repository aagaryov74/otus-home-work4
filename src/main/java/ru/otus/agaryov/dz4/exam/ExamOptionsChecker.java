package ru.otus.agaryov.dz4.exam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import ru.otus.agaryov.dz4.service.AsciiCheckerService;

import java.io.File;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

@Service
public class ExamOptionsChecker {
    private final AsciiCheckerService asciiCheckerService;

    @Value("${config.messages}")
            private String messagesFile;

    @Value("${config.csvfile}")
    private String csvfile;

    public ExamOptionsChecker(AsciiCheckerService asciiCheckerService) {
        this.asciiCheckerService = asciiCheckerService;
    }

    Boolean isAscii(String name) {
        return asciiCheckerService.isASCII(name);
    }

    Boolean isLangOk(String language) {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(messagesFile);
            Map<String, Map<String, String>> maps = yaml.load(inputStream);
            if (maps.get(language.toLowerCase()).size() > 0) {
                File qFile = new File(csvfile+"_"+language+".csv");
                if (qFile.canRead()) {
                    Locale locale = new Locale.Builder().
                            setLanguage(language.toLowerCase()).
                            setRegion(language.toUpperCase()).build();
                    if (locale != null) {
                        return true;
                    }
                } else {
                    System.err.println("There are no csv file with questions with this Language");
                }
            } else {
                System.err.println("There are no messages with this Language in config file");
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
