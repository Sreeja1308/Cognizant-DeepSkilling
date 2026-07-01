package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Exercise 3: Mocking File I/O Operations")
public class FileServiceTest {

    @Mock
    private FileReader mockFileReader;

    @Mock
    private FileWriter mockFileWriter;

    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService(mockFileReader, mockFileWriter);
    }

    @Test
    @DisplayName("Should process file content")
    void testServiceWithMockFileIO() {
        // 1. Create a mock file reader and writer using Mockito (already done with @Mock)
        // 2. Stub the file reader and writer methods to simulate file operations
        when(mockFileReader.read()).thenReturn("Mock File Content");

        // 3. Write a test to verify the service logic using the mocked file reader and writer
        String result = fileService.processFile();

        assertEquals("Processed Mock File Content", result);
        verify(mockFileReader).read();
        verify(mockFileWriter).write("Processed Mock File Content");
    }

    @Test
    @DisplayName("Should handle empty file content")
    void testProcessEmptyFile() {
        when(mockFileReader.read()).thenReturn("");

        String result = fileService.processFile();

        assertEquals("File is empty", result);
        verify(mockFileReader).read();
        verify(mockFileWriter, never()).write(anyString());
    }

    @Test
    @DisplayName("Should handle null file content")
    void testProcessNullFile() {
        when(mockFileReader.read()).thenReturn(null);

        String result = fileService.processFile();

        assertEquals("File is empty", result);
        verify(mockFileReader).read();
        verify(mockFileWriter, never()).write(anyString());
    }

    @Test
    @DisplayName("Should read specific line from file")
    void testReadLine() {
        when(mockFileReader.readLine(2)).thenReturn("Line 2 content");

        String result = fileService.readLine(2);

        assertEquals("Line 2 content", result);
        verify(mockFileReader).readLine(2);
    }

    @Test
    @DisplayName("Should check if file exists")
    void testFileExists() {
        when(mockFileReader.exists()).thenReturn(true);

        boolean exists = fileService.fileExists();

        assertTrue(exists);
        verify(mockFileReader).exists();
    }

    @Test
    @DisplayName("Should get file size")
    void testFileSize() {
        when(mockFileReader.size()).thenReturn(1024L);

        long size = fileService.getFileSize();

        assertEquals(1024L, size);
        verify(mockFileReader).size();
    }

    @Test
    @DisplayName("Should append content to file")
    void testAppendToFile() {
        doNothing().when(mockFileWriter).append(anyString());

        fileService.appendToFile("Additional content");

        verify(mockFileWriter).append("Additional content");
    }

    @Test
    @DisplayName("Should delete file")
    void testDeleteFile() {
        doNothing().when(mockFileWriter).delete();

        fileService.deleteFile();

        verify(mockFileWriter).delete();
    }
}