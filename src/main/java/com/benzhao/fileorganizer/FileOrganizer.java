package com.benzhao.fileorganizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        if (filePath == null || filePath.isEmpty()) {
            throw new IOException("File path cannot be null or empty");
        }
        
        List<FileType> fileTypes = new ArrayList<>();
        File fileObject = new File(filePath);
        if (!fileObject.exists()) {
            throw new IOException("File does not exist: " + filePath);
        }
        if (!fileObject.isDirectory()) {
            throw new IOException("Provided path is not a directory: " + filePath);
        }
        File[] files = fileObject.listFiles();
        if (files.length == 0) {
            throw new IOException("No files found in directory: " + filePath);
        }
        Arrays.sort(files, (f1, f2) -> {
            if (f1.isDirectory() && !f2.isDirectory()) {
                return -1; 
            } else if (!f1.isDirectory() && f2.isDirectory()) {
                return 1;  
            } else {
                return f1.getName().compareToIgnoreCase(f2.getName());
            }
        });
        // File types to account: txt, pdf, folder, movie, pptx, docx, png, jpg, csv, other
        for (File file: files) {
            if (file.isDirectory()) {
                fileTypes.add(new FileType(file.getName(), "folder"));
                continue;
            }
            String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
            switch (fileType) {
                case "txt":
                    fileType = "text";
                    break;
                case "pdf":
                    fileType = "pdf";
                    break;
                case "mov":
                    fileType = "movie";
                    break;
                case "pptx":
                    fileType = "presentation";
                    break;
                case "docx":
                    fileType = "document";
                    break;
                case "png":
                case "jpeg":
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