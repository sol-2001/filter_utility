package org.example;

import java.util.regex.Pattern;

public class DataClassifier {
    private static final Pattern INTEGER_PATTERN = Pattern.compile("^-?\\d+$");
    private static final Pattern FLOAT_PATTERN = Pattern.compile(
            "^-?\\d+(\\.\\d*)?([eE][+-]?\\d+)?$" +            // 123.45, 123e5
                    "|^-?\\.\\d+([eE][+-]?\\d+)?$" +          // .45, -.67e8
                    "|^-?\\d+[eE][+-]?\\d+$"                  // 123e5, -123E-7
    );

    public enum DataType {
        STRING("strings.txt"),
        INTEGER("integers.txt"),
        FLOAT("floats.txt");

        private final String fileName;

        DataType(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }

    public static DataType classify(String line) {
        if (line == null) return DataType.STRING;

        String trimmed = line.trim();
        if (trimmed.isEmpty()) return DataType.STRING;

        if (INTEGER_PATTERN.matcher(trimmed).matches()) {
            return DataType.INTEGER;
        } else if (FLOAT_PATTERN.matcher(trimmed).matches()) {
            return DataType.FLOAT;
        } else {
            return DataType.STRING;
        }
    }
}
