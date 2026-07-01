package com.example.employeemanagement.dto;

public interface EmployeeProjection {
    Long getId();
    String getName();
    String getEmail();
    String getPosition();
    String getDepartmentName(); // This will come from the query
    
    // Computed property
    default String getDisplayName() {
        return getName() + " (" + getEmail() + ")";
    }
}