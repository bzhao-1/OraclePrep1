package com.benzhao.fileorganizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.benzhao.fileorganizer.FileType;

public class FileOrganizer {
    public static void main(String [] args) {
        if (args.length < 1) {
            System.out.println("Usage: java FileOrganizer <filePath>");
            System.exit(1);
        }

        String filePath = args[0];

        try {
           List<FileType> files = organizeFilePath(filePath);
            
            System.out.println("The files for filepath: " + filePath + " are " + files.toString());
        } catch (IOException e) {
            System.err.println("Error reading filepath: '" + filePath + "' with error: " + e.getMessage());
            System.exit(1);
        }
    }

    public static List<FileType> organizeFilePath(String filePath) throws IOException{
        List<FileType> fileTypes = new ArrayList<>();
        File fileObject = new File(filePath);

        if (!fileObject.exists()) {
            throw new IOException("File does not exist: " + filePath);
        }
        if (!fileObject.isDirectory()) {
            throw new IOException("Provided path is not a directory: " + filePath);
        }
        File[] files = fileObject.listFiles();
        if (files == null || files.length == 0) {
            throw new IOException("No files found in directory: " + filePath);
        }
        // File types to account: txt, pdf, folder, movie, pptx, docx, png, jpg, csv, other
        for (File file: files) {
            // Todo: Add logic to determine file type based on file extension
            String fileType = "other";
            switch (fileType) {
                case "txt":
                    fileType = "text";
                    break;
                case "pdf":
                    fileType = "pdf";
                    break;
                case "folder":
                    fileType = "folder";
                    break;
                case "movie":
                    fileType = "movie";
                    break;
                case "pptx":
                    fileType = "presentation";
                    break;
                case "docx":
                    fileType = "document";
                    break;
                case "png":
                case "jpg":
                    fileType = "image";
                    break;
                case "csv":
                    fileType = "csv";
                    break;
                default:
                    fileType = "other";
            }
            fileTypes.add(new FileType(file.getName(), fileType));

        }




        return fileTypes;
    }
    

    
}