package org.example;

import java.util.*;

public class StatisticsCollector{

    private final Stats intStats = new Stats();
    private final Stats floatStats = new Stats();
    private final Stats stringStats = new Stats();

    public Stats getStringFullStats(List<String> lines) {
        stringStats.count = lines.size();
        stringStats.shortestLength = Integer.MAX_VALUE;
        stringStats.longestLength = Integer.MIN_VALUE;

        for (String line : lines) {
            stringStats.longestLength = Math.max(stringStats.longestLength, line.length());
            stringStats.shortestLength = Math.min(stringStats.shortestLength, line.length());
        }
        return stringStats;
    }

    public Stats getIntegerFullStats(List<String> lines) {
        intStats.count = lines.size();
        intStats.minValueInteger = Integer.MAX_VALUE;
        intStats.maxValueInteger = Integer.MIN_VALUE;
        intStats.sumValueInteger = 0;

        for (String line : lines) {
            try {
                int number = Integer.parseInt(line);
                intStats.minValueInteger = Math.min(intStats.minValueInteger, number);
                intStats.maxValueInteger = Math.max(intStats.maxValueInteger, number);
                intStats.sumValueInteger += number;
            } catch (NumberFormatException e) {
                System.err.println("Ошибка парсинга числа: " + line);
            }
        }

        intStats.avgValueInteger = intStats.sumValueInteger / intStats.count;
        return intStats;
    }

    public Stats getFloatFullStats(List<String> line) {
        floatStats.count = line.size();
        floatStats.minValueFloat = Float.MAX_VALUE;
        floatStats.maxValueFloat = Float.MIN_VALUE;
        floatStats.sumValueFloat = 0.0f;

        for (String s : line) {
            float number = Float.parseFloat(s);
            floatStats.minValueFloat = Math.min(floatStats.minValueFloat, number);
            floatStats.maxValueFloat = Math.max(floatStats.maxValueFloat, number);
            floatStats.sumValueFloat += number;
        }

        floatStats.avgValueFloat = floatStats.sumValueFloat / floatStats.count;
        return floatStats;
    }

    public Stats getStringShortStats(List<String> lines) {
        stringStats.count = lines.size();
        return stringStats;
    }

    public Stats getIntegerShortStats(List<String> lines) {
        intStats.count = lines.size();
        return intStats;
    }

    public Stats getFloatShortStats(List<String> lines) {
        floatStats.count = lines.size();
        return floatStats;
    }
}
