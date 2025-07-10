package com.benzhao.fileorganizer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FileOrganizerTest {
    @Test
    public void testNullFilePath() {
        IOException exception = assertThrows(IOException.class, () -> {
            FileOrganizer.organizeFilePath(null);
        });

        assertEquals("File path cannot be null or empty", exception.getMessage());
    }


    @Test
    public void testNonExistentFilePath() {
        String fakePath = "/this/path/does/not/exist";
        IOException exception = assertThrows(IOException.class, () -> {
            FileOrganizer.organizeFilePath(fakePath);
        });
        assertEquals("File does not exist: " + fakePath, exception.getMessage());
    }

    @Test
    public void testEmptyFilePath() {
        IOException exception = assertThrows(IOException.class, () -> {
            FileOrganizer.organizeFilePath("");
        });
        assertEquals("File path cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testInvalidFilePath() {
        String file_path = getClass().getClassLoader().getResource("all_matches.txt").getPath();
        IOException exception = assertThrows(IOException.class, () -> {
            FileOrganizer.organizeFilePath(file_path);
        });
        assertEquals("Provided path is not a directory: " + file_path, exception.getMessage());
        
    }

    @Test
    public void testValidFilePath() throws IOException {
        String filePath = getClass().getClassLoader().getResource("FileOrganizer").getPath();
        List<FileType> files = FileOrganizer.organizeFilePath(filePath);
        assertEquals(11, files.size(), "Expected 11 files in the directory");
        assertEquals("folder", files.get(0).filetype(), "Expected first file type to be directory");
        assertEquals("presentation", files.get(1).filetype(), "Expected second file type to be presentation");
        assertEquals("presentation", files.get(2).filetype(), "Expected third file type to be presentation");
        assertEquals("pdf", files.get(3).filetype(), "Expected fourth file type to be pdf");
        assertEquals("image", files.get(4).filetype(), "Expected fifth file type to be image");
        assertEquals("image", files.get(5).filetype(), "Expected sixth file type to be image");
        assertEquals("other", files.get(6).filetype(), "Expected seventh file type to be python file");
        assertEquals("other", files.get(7).filetype(), "Expected eighth file type to be python file");
        assertEquals("image", files.get(8).filetype(), "Expected ninth file type to be image");
        assertEquals("movie", files.get(9).filetype(), "Expected tenth file type to be movie");
        assertEquals("csv", files.get(10).filetype(), "Expected eleventh file type to be csv");
    }
}


