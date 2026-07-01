package com.example;

import java.util.ArrayList;
import java.util.List;

public class MultiReturnService {
    private final Repository repository;

    public MultiReturnService(Repository repository) {
        this.repository = repository;
    }

    public String getData() {
        return repository.getData();
    }

    public List<String> getMultipleData(int count) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(repository.getData());
        }
        return results;
    }

    public String processWithFallback() {
        try {
            return "Processed " + repository.getData();
        } catch (Exception e) {
            return "Error processing data";
        }
    }
}