package com.benzhao.loganalyzer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.benzhao.loganalyzer.FileAnalysis;

public class LogAnalyzer{
    public static void main(String [] args) {
        if (args.length < 2) {
            System.out.println("Usage: java logAnalyzer <logfile> [<keyword>]");
            System.exit(1);
        }

        String logFile = args[0];
        String keyword = args.length > 1 ? args[1].trim() : null;

        try {
            FileAnalysis result = analyzeLogFile(logFile, keyword);
            int matches = result.matchingLines();
            int total = result.totalLines();

            System.out.println("Total matching lines for keyword '" + keyword + "': " + matches + 
            "\n" + "Total lines in file: " + total + 
            "\n" + "Percentage of matching lines: " + (matches * 100.0 / total) + "%");
            System.exit(0);
            
        } catch (IOException e) {
            System.err.println("Error reading file: '" + logFile + "' with error: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Analyzes a log file for lines containing a specific keyword.
     *
     * @param logFile the path to the log file, if empty it will throw an IOException, if null it will throw a NullPointerException
     * @param keyword the keyword to search for, can be null or empty to count all lines
     * @return FileAnalysis object containing the count of matching lines and total lines
     * @throws IOException if an error occurs while reading the file
     */
    public static FileAnalysis analyzeLogFile(String logFile, String keyword) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            int matchingLines = 0;
            int totalLines = 0;
            if (keyword != null) {
                keyword = keyword.toLowerCase(); // Normalize keyword to lower case for case-insensitive search
            }
            // This method go through each line of the file. We acknowledge that this is a simple implementation and may not be efficient for very large files.
            // However, it is straightforward and easy to understand.
            while ((line = reader.readLine()) != null) {
                totalLines++;
                if (keyword == null || keyword.isEmpty()) {
                    matchingLines++;
                } else {
                    // Normalize line to lower case for case-insensitive search
                    line = line.toLowerCase();
                    if (line.contains(keyword)) {
                        matchingLines++;
                    }
                }
            }
            return new FileAnalysis(matchingLines, totalLines);
        } 
    }
}
