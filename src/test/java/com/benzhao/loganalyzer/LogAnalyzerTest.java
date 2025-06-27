package com.benzhao.loganalyzer;

import java.io.IOException;
import com.benzhao.loganalyzer.FileAnalysis;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LogAnalyzerTest {
    @Test
    public void testNonExistentFile() {
        assertThrows(IOException.class,  () -> {
            LogAnalyzer.analyzeLogFile("", null);
        });
    }

    @Test
    public void testNullFilePath() {
        assertThrows(NullPointerException.class,  () -> {
            LogAnalyzer.analyzeLogFile(null, "test");
        });

    }

    @Test
    public void testEmptyFile() throws IOException {
        String filePath = getClass().getClassLoader().getResource("empty.txt").getPath();
        FileAnalysis results = LogAnalyzer.analyzeLogFile(filePath, "Ben");
        int matches = results.matchingLines();
        int total = results.totalLines();
        assertEquals(0, matches);
        assertEquals(0, total);
      
    }

    @Test
    public void testValidFileNoKeyword() throws IOException {
        String filePath = getClass().getClassLoader().getResource("no_matches.txt").getPath();
        FileAnalysis results = LogAnalyzer.analyzeLogFile(filePath, "");
        int matches = results.matchingLines();
        int total = results.totalLines();
        assertEquals(5, matches);
        assertEquals(5, total);
    }

    @Test
    public void testValidFileNullKeyword () throws IOException {
        String filePath = getClass().getClassLoader().getResource("no_matches.txt").getPath();
        FileAnalysis results = LogAnalyzer.analyzeLogFile(filePath, null);
        int matches = results.matchingLines();
        int total = results.totalLines();
        assertEquals(5, matches);
        assertEquals(5, total);
    }

    @Test
    public void testNoLinesMatchKeyword() throws IOException {
        String filePath = getClass().getClassLoader().getResource("no_matches.txt").getPath();
        FileAnalysis results = LogAnalyzer.analyzeLogFile(filePath, "Ben");
        int matches = results.matchingLines();
        int total = results.totalLines();
        assertEquals(0, matches);
        assertEquals(5, total);

    }

    @Test
    public void testAllLinesMatchKeyword() throws IOException {
        String filePath = getClass().getClassLoader().getResource("all_matches.txt").getPath();
        FileAnalysis results = LogAnalyzer.analyzeLogFile(filePath, "Ben");
        int matches = results.matchingLines();
        int total = results.totalLines();
        assertEquals(5, matches);
        assertEquals(5, total);
    }

    @Test
    public void testSomeLinesMatchKeyword() throws IOException {
        String filePath = getClass().getClassLoader().getResource("some_matches.txt").getPath();
        FileAnalysis results = LogAnalyzer.analyzeLogFile(filePath, "Ben");
        int matches = results.matchingLines();
        int total = results.totalLines();
        assertEquals(3, matches);
        assertEquals(5, total);

    }

}
