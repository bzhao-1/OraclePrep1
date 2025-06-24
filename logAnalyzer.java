import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class logAnalyzer{
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
        // TODO: Count the number of lines that contain the keyword
        // and the total number of lines in the file.
        // Return an array with two elements: [matchingLines, totalLines]
        throw new UnsupportedOperationException("Unimplemented method 'readingFile'");
    }
}
