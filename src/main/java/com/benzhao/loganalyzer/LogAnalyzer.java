package com.benzhao.loganalyzer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.benzhao.loganalyzer.FileAnalysis;

public class LogAnalyzer{
    public static void main(String [] args) {
        if (args.length < 2) {
            System.out.println("Usage: java logAnalyzer <logfile> [<keyword>]");
            return;
        }

        String logFile = args[0];
        String keyword = args.length > 1 ? args[1] : null;

        try {
            FileAnalysis result = analyzeLogFile(logFile, keyword);
            int matches = result.matchingLines();
            int total = result.totalLines();

            System.out.println("Total matching lines for keyword '" + keyword + "': " + matches + 
            "\n" + "Total lines in file:" + total + 
            "\n" + "Percentage of matching lines: " + (matches * 100.0 / total) + "%");
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + logFile + "with error: " + e.getMessage());
        }
    }

    public static FileAnalysis analyzeLogFile(String logFile, String keyword) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            int matchingLines = 0;
            int totalLines = 0;
            while ((line = reader.readLine()) != null) {
                totalLines++;
                if (line.contains(keyword)) {
                    matchingLines++;
                }
            }
            
           
            return new FileAnalysis(matchingLines, totalLines);

        } 
    }
}
