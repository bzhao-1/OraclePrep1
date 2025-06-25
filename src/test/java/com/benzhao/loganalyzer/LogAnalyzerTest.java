package com.benzhao.loganalyzer;

import java.io.IOException;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogAnalyzerTest {
    @Test
    public void testNonExistentFile() {
        assertThrows(IOException.class,  () -> {
            LogAnalyzer.readingFile("", null);
        });
    }

    @Test
    public void testNullFilePath() {
        assertThrows(NullPointerException.class,  () -> {
            LogAnalyzer.readingFile(null, "test");
        });

    }

    @Test
    public void testEmptyFile() throws IOException {
        String filePath = "/Users/benzhao/Desktop/Fun-Projects-/src/test/resources/empty.txt";
        int[] results = LogAnalyzer.readingFile(filePath, "Ben");
        assertEquals(0, results[0]);
        assertEquals(0, results[1]);
      
    }

    @Test
    public void testNoMatchingLines() throws IOException {
        String filePath = "/Users/benzhao/Desktop/Fun-Projects-/src/test/resources/no_matches.txt";
        int[] results = LogAnalyzer.readingFile(filePath, "Ben");
        assertEquals(0, results[0]);
        assertEquals(5, results[1]);

    }

    @Test
    public void testAllMatchingLines() throws IOException {
        String filePath = "/Users/benzhao/Desktop/Fun-Projects-/src/test/resources/all_matches.txt";
        int[] results = LogAnalyzer.readingFile(filePath, "Ben");
        assertEquals(5, results[0]);
        assertEquals(5, results[1]);
    }

    @Test
    public void testSomeMatchingLines() throws IOException {
        String filePath = "/Users/benzhao/Desktop/Fun-Projects-/src/test/resources/some_matches.txt";
        int[] results = LogAnalyzer.readingFile(filePath, "Ben");
        assertEquals(3, results[0]);
        assertEquals(5, results[1]);

    }

}
