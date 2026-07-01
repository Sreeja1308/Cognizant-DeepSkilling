package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // Exercise 4: CRUD Operations - Create
    public Department createDepartment(Department department) {
        log.info("Creating new department: {}", department.getName());
        return departmentRepository.save(department);
    }

    // Exercise 4: CRUD Operations - Read (All)
    public List<Department> getAllDepartments() {
        log.info("Fetching all departments");
        return departmentRepository.findAll();
    }

    // Exercise 4: CRUD Operations - Read (By ID)
    public Department getDepartmentById(Long id) {
        log.info("Fetching department with id: {}", id);
        return departmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    // Exercise 4: CRUD Operations - Update
    public Department updateDepartment(Long id, Department departmentDetails) {
        log.info("Updating department with id: {}", id);
        
        Department existingDepartment = getDepartmentById(id);
        existingDepartment.setName(departmentDetails.getName());
        existingDepartment.setDescription(departmentDetails.getDescription());
        
        return departmentRepository.save(existingDepartment);
    }

    // Exercise 4: CRUD Operations - Delete
    public void deleteDepartment(Long id) {
        log.info("Deleting department with id: {}", id);
        Department department = getDepartmentById(id);
        
        // Check if department has employees
        if (department.getEmployees() != null && !department.getEmployees().isEmpty()) {
            throw new RuntimeException("Cannot delete department with existing employees");
        }
        
        departmentRepository.delete(department);
    }

    // Exercise 5: Custom Query Methods
    public Department findDepartmentByName(String name) {
        log.info("Finding department by name: {}", name);
        return departmentRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Department not found with name: " + name));
    }

    public List<Department> searchDepartments(String keyword) {
        log.info("Searching departments with keyword: {}", keyword);
        return departmentRepository.searchDepartments(keyword);
    }

    public long countEmployeesInDepartment(Long departmentId) {
        log.info("Counting employees in department: {}", departmentId);
        return departmentRepository.countEmployeesInDepartment(departmentId);
    }
}