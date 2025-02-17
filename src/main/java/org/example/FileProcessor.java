package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileProcessor {

    private final List<File> files;
    private final String prefix;
    private final Path outputPath;
    private final Map<DataClassifier.DataType, ArrayList<String>> map = new HashMap<>();
    private final boolean appendMode;
    private final boolean shortStats;
    private final boolean fullStats;

    FileProcessor(List<File> files, Path outputPath, String prefix, boolean appendMode, boolean shortStats, boolean fullStats) {
        this.files = files;
        this.outputPath = outputPath;
        this.prefix = prefix;
        this.appendMode = appendMode;
        this.shortStats = shortStats;
        this.fullStats = fullStats;
        
        
        map.put(DataClassifier.DataType.STRING, new ArrayList<>());
        map.put(DataClassifier.DataType.INTEGER, new ArrayList<>());
        map.put(DataClassifier.DataType.FLOAT, new ArrayList<>());
    }

    public void process() {
        try {
            Files.createDirectories(outputPath);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать директорию: " + e.getMessage());
        }
        readInputFiles();
        writeOutputFiles();
    }

    private void readInputFiles() {
        for (File file : files) {
            try (BufferedReader bufferedReader = Files.newBufferedReader(file.toPath())
            ) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    //if (line.isEmpty()){continue;} //пустын строки
                    DataClassifier.DataType type = DataClassifier.classify(line);
                    map.get(type).add(line);
                }
            } catch (IOException e) {
                System.err.println("Ошибка чтения файла " + file + ": " + e.getMessage());
            }

        }
    }

    private void writeOutputFiles() {
        for (DataClassifier.DataType type : map.keySet()) {
            List<String> lines = map.get(type);
            if (!lines.isEmpty()) {
                StatisticsCollector statisticsCollector = new StatisticsCollector();
                StatisticsPrinter statisticsPrinter = new StatisticsPrinter();

                Path outputFile = outputPath.resolve(getFullPath(type));
                writeToFile(outputFile, lines);
                if (fullStats) {
                    switch (type) {
                        case STRING ->
                                statisticsPrinter.printFullStatsString(statisticsCollector.getStringFullStats(lines));
                        case INTEGER ->
                                statisticsPrinter.printFullStatsInteger(statisticsCollector.getIntegerFullStats(lines));
                        case FLOAT ->
                                statisticsPrinter.printFullStatsFloat(statisticsCollector.getFloatFullStats(lines));
                    }
                } else if (shortStats) {
                    switch (type) {
                        case STRING ->
                                statisticsPrinter.printShortStatsString(statisticsCollector.getStringShortStats(lines));
                        case INTEGER ->
                                statisticsPrinter.printShortStatsInteger(statisticsCollector.getIntegerShortStats(lines));
                        case FLOAT ->
                                statisticsPrinter.printShortStatsFloat(statisticsCollector.getFloatShortStats(lines));
                    }
                }
            }
        }
    }

    private void writeToFile(Path outputFile, List<String> lines) {
        try {
            Files.createDirectories(outputFile.getParent());
        } catch (IOException e) {
            System.err.println("Ошибка создания поддиректории: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.toFile(), appendMode))) { // Посмотреть потом как через nio
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + outputFile + ": " + e.getMessage());
        }
    }

    private Path getFullPath(DataClassifier.DataType type) {
        String filename = prefix + type.getFileName();
        return outputPath.resolve(filename);    }
}
