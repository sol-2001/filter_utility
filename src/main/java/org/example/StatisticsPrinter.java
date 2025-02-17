package org.example;

public class StatisticsPrinter {
    public void printFullStatsInteger(Stats stats) {
        System.out.println("FullIntegerStats:");
        System.out.println("count: " + stats.count);
        System.out.println("maxValueInteger: " + stats.maxValueInteger);
        System.out.println("minValueInteger: " + stats.minValueInteger);
        System.out.println("avgValueInteger: " + stats.avgValueInteger);
        System.out.println("sumValueInteger: " + stats.sumValueInteger);
    }

    public void printFullStatsString(Stats stats) {
        System.out.println("FullStringStats:");
        System.out.println("count: " + stats.count);
        System.out.println("shortestLength: " + stats.shortestLength);
        System.out.println("longestLength: " + stats.longestLength);
    }

    public void printFullStatsFloat(Stats stats) {
        System.out.println("FullFloatStats:");
        System.out.println("count: " + stats.count);
        System.out.println("maxValueFloat: " + stats.maxValueFloat);
        System.out.println("minValueFloat: " + stats.minValueFloat);
        System.out.println("avgValueFloat: " + stats.avgValueFloat);
        System.out.println("sumValueFloat: " + stats.sumValueFloat);
    }

    public void printShortStatsString(Stats stats) {
        System.out.println("ShortStringStats:");
        System.out.println("count: " + stats.count);
    }

    public void printShortStatsInteger(Stats stats) {
        System.out.println("ShortIntegerStats:");
        System.out.println("count: " + stats.count);
    }

    public void printShortStatsFloat(Stats stats) {
        System.out.println("ShortFloatStats:");
        System.out.println("count: " + stats.count);
    }
}
