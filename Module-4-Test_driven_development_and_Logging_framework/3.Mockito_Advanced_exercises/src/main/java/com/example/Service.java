package com.example;

public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        String data = repository.getData();
        if (data == null) {
            return "No data available";
        }
        return "Processed " + data;
    }

    public String findById(int id) {
        String result = repository.findById(id);
        return result != null ? "Found: " + result : "Not found";
    }

    public void saveData(String data) {
        repository.save(data);
    }

    public int getCount() {
        return repository.count();
    }
}