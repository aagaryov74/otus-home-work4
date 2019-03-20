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
import ru.otus.agaryov.dz4.service.IOService;
import ru.otus.agaryov.dz4.service.LocalizatorService;

import java.io.IOException;

@ShellComponent
public class ExamExecutorImpl implements ExamExecutor {
    private final CsvFileReader csvFileReader;
    private final ResultChecker resultChecker;
    private final IOService ioService;
    private final LocalizatorService localizatorService;
    private String userName;

    @NonNull
    @Value("${config.csvfile}")
    private String csvFilePrefix;

    @Autowired
    public ExamExecutorImpl(@Qualifier("csvFileReaderImpl") CsvFileReader csvFileReader,
                            ResultChecker resultChecker,
                            IOService ioService, LocalizatorService localizatorService) {
        this.csvFileReader = csvFileReader;
        this.resultChecker = resultChecker;
        this.ioService = ioService;
        this.localizatorService = localizatorService;
    }

    @ShellMethod("Enter hello with your name to start exam")
    public void hello(@ShellOption String username) {
        this.userName = username;
        ioService.printFToConsole("welcome", userName);
    }

    @ShellMethod("Enter your preferred console language like en or ru")
    public void lang(@ShellOption String language) {
        if (localizatorService.setLanguage(language)) {
            ioService.printFToConsole("welcomeLocale", userName);
        } else {
            ioService.printToConsole("wrongLang");
        }
    }

    @ShellMethod("Starting Quiz with simple questions")
    private void doexam() {
        if (this.userName != null) {
            try {
                resultChecker.setMap(csvFileReader.readCsvIntoMap());
                if (resultChecker.getQuestions() != null) {
                    if (resultChecker.getQuestions() == null) throw new IOException();
                    ioService.printFToConsole("welcomeQuestions",
                            this.userName, csvFileReader.getReadedStrsCount());

                    for (int i = 0; i < resultChecker.getQuestions().length; i++) {
                        String question = resultChecker.getQuestions()[i].toString();
                        ioService.printFToConsole("question",
                                i + 1, question);
                        String input = ioService.readFromConsole();
                        resultChecker.checkAnswer(question, input);
                    }
                    ioService.printFToConsole("results", resultChecker.getResult());
                } else {
                    throw new IOException();
                }
            } catch (IOException e) {
                ioService.printToConsole("ioWarning");
            }
        } else {
            ioService.printToConsole("sayhellofirst");
        }
    }

    @Override
    public void doExam() {
        doexam();
    }
}
