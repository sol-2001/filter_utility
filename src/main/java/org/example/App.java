package org.example;


import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CommandLine.Command(name = "App", mixinStandardHelpOptions = true, version = "1.0",
        description = "Приложение")
public class App implements Runnable {


    @CommandLine.Parameters(arity = "1..*")
    private List<File> inputFiles;

    // путь для выходных файлов
    @CommandLine.Option(names = {"-o"})
    private Path outputPath = Paths.get(".");

    //префикс имен
    @CommandLine.Option(names = {"-p"})
    private String prefix;

    // по умолчанию перезаписывается, если флаг передан, то добавляем к старым
    @CommandLine.Option(names = {"-a"}, description = "Включить запись к существующим")
    private boolean appendMode = false;

    // краткая статистика
    @CommandLine.Option(names = {"-s"})
    private boolean shortStats;

    //полная
    @CommandLine.Option(names = {"-f"})
    private boolean fullStats;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.out.println(exitCode);
    }

    //public List<File> file = new ArrayList<>(List.of(new File("text.txt")));
    @Override
    public void run() {
        try {
            if (shortStats && fullStats) {
                throw new IllegalArgumentException("Опции -s и -f нельзя использовать одновременно");
            }

            FileProcessor fileProcessor = new FileProcessor(inputFiles, outputPath, prefix, appendMode, shortStats, fullStats);
            fileProcessor.process();
            System.out.println("OK");
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            throw new CommandLine.ExecutionException(new CommandLine(this), e.getMessage());
        }
    }
}
