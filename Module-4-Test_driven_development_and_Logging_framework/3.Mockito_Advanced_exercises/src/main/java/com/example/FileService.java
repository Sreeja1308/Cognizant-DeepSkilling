package com.example;

public class FileService {
    private final FileReader fileReader;
    private final FileWriter fileWriter;

    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        if (content == null || content.isEmpty()) {
            return "File is empty";
        }
        String processed = "Processed " + content;
        fileWriter.write(processed);
        return processed;
    }

    public String readLine(int lineNumber) {
        return fileReader.readLine(lineNumber);
    }

    public boolean fileExists() {
        return fileReader.exists();
    }

    public long getFileSize() {
        return fileReader.size();
    }

    public void appendToFile(String content) {
        fileWriter.append(content);
    }

    public void deleteFile() {
        fileWriter.delete();
    }
}