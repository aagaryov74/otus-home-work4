package ru.otus.agaryov.dz4.exam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.agaryov.dz4.csvfilereader.CsvFileReader;
import ru.otus.agaryov.dz4.results.ResultChecker;
import ru.otus.agaryov.dz4.service.AsciiCheckerService;
import ru.otus.agaryov.dz4.service.IOService;

import java.io.IOException;

@ShellComponent
public class ExamExecutor {
    private final CsvFileReader csvFile;
    private final ResultChecker checker;
    private final IOService ioService;
    private final ExamOptionsChecker examOptionsChecker;
    private String userName;
    private String consoleLanguage;

    @NonNull
    @Value("${config.csvfile}")
    private String csvFilePrefix;

    @Autowired
    public ExamExecutor(@Qualifier("implCsvFileReader") CsvFileReader csvFileReader,
                        ResultChecker resultChecker,
                        IOService ioService,
                        AsciiCheckerService asciiCheckerService, ExamOptionsChecker examOptionsChecker) {
        this.csvFile = csvFileReader;
        this.checker = resultChecker;
        this.ioService = ioService;
        this.examOptionsChecker = examOptionsChecker;
    }

    @ShellMethod("Enter hello with your name to start exam")
    public void hello(@ShellOption String username) {
        this.userName = username;
    }

    @ShellMethod("Enter your preferred console language like en or ru")
    public void lang(@ShellOption String language) {
        if (examOptionsChecker.isLangOk(language)) {
            this.consoleLanguage = language.toLowerCase();
        } else {
            ioService.printToConsole("wrongLang");
        }
    }

    @ShellMethod("Starting Quiz with simple questions")
    public void doexam() {
        if (this.userName != null) {
            try {
                if (checker.getQuestions() != null) {
                    ioService.setLocaleLang(consoleLanguage);
                    checker.setMap(csvFile.setCsvFile((csvFilePrefix +
                            "_" + consoleLanguage + ".csv")));

                    if (checker.getQuestions() == null) throw new IOException();
                    ioService.printFToConsole("welcomeQuestions",
                            this.userName, csvFile.getReadedStrsCount());

                    for (int i = 0; i < checker.getQuestions().length; i++) {
                        String question = checker.getQuestions()[i].toString();
                        ioService.printFToConsole("question",
                                i + 1, question);
                        String input = ioService.readFromConsole();
                        checker.checkAnswer(question, input);
                    }
                    ioService.printFToConsole("results", checker.getResult());
                } else {
                    throw new IOException();
                }
            } catch (IOException e) {
                ioService.printToConsole("iowarning");
            }
        } else {
            ioService.printToConsole("sayhellofirst");
        }
    }
}
