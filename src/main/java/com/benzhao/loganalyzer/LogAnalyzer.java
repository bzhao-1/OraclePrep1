package com.benzhao.loganalyzer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogAnalyzer{
    public static void main(String [] args) {
        if (args.length < 2) {
            System.out.println("Usage: java logAnalyzer <logfile> [<keyword>]");
            return;
        }

        String logFile = args[0];
        String keyword = args.length > 1 ? args[1] : null;

        try {
            int[] results = readingFile(logFile, keyword);
            System.out.println("Total matching lines for keyword '" + keyword + "': " + results[0] + 
            "\n" + "Total lines in file:" + results[1] + 
            "\n" + "Percentage of matching lines: " + (results[0] * 100.0 / results[1]) + "%");
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static int[] readingFile(String logFile, String keyword) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            Integer matchingLines = 0;
            Integer totalLines = 0;
            while ((line = reader.readLine()) != null) {
                totalLines++;
                if (line.contains(keyword)) {
                    matchingLines++;
                }
            }
            
            int[] results = new int[] {
                matchingLines, totalLines
            };
            return results;

        } 
    }
}
