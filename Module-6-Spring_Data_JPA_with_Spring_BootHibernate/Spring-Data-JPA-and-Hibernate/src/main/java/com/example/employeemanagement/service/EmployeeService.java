package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.dto.EmployeeProjection;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    // ========== Exercise 4: CRUD Operations - Create ==========
    public Employee createEmployee(Employee employee) {
        log.info("Creating new employee: {}", employee.getName());
        
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
            employee.setDepartment(dept);
        }
        
        return employeeRepository.save(employee);
    }

    // ========== Exercise 4: CRUD Operations - Read (All) ==========
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    // ========== Exercise 4: CRUD Operations - Read (By ID) ==========
    public Employee getEmployeeById(Long id) {
        log.info("Fetching employee with id: {}", id);
        return employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    // ========== Exercise 4: CRUD Operations - Update ==========
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        log.info("Updating employee with id: {}", id);
        
        Employee existingEmployee = getEmployeeById(id);
        
        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setPhoneNumber(employeeDetails.getPhoneNumber());
        existingEmployee.setPosition(employeeDetails.getPosition());
        existingEmployee.setSalary(employeeDetails.getSalary());
        
        if (employeeDetails.getDepartment() != null && employeeDetails.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(employeeDetails.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
            existingEmployee.setDepartment(dept);
        }
        
        return employeeRepository.save(existingEmployee);
    }

    // ========== Exercise 4: CRUD Operations - Delete ==========
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    // ========== Exercise 5: Custom Query Methods ==========
    public List<Employee> findEmployeesByDepartment(Long departmentId) {
        log.info("Finding employees by department id: {}", departmentId);
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public List<Employee> searchEmployeesByName(String name) {
        log.info("Searching employees by name containing: {}", name);
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public Employee findEmployeeByEmail(String email) {
        log.info("Finding employee by email: {}", email);
        return employeeRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
    }

    // ========== Exercise 6: Pagination and Sorting ==========
    public Page<Employee> getEmployeesByDepartment(Long departmentId, Pageable pageable) {
        log.info("Fetching employees with pagination for department: {}", departmentId);
        return employeeRepository.findByDepartmentId(departmentId, pageable);
    }

    public Page<Employee> searchEmployeesWithPagination(String name, Pageable pageable) {
        log.info("Searching employees with pagination: {}", name);
        return employeeRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    // ========== Exercise 8: Projections ==========
    public List<EmployeeProjection> getEmployeeProjectionsByDepartment(Long departmentId) {
        log.info("Getting employee projections for department: {}", departmentId);
        return employeeRepository.findEmployeeProjectionsByDepartment(departmentId);
    }

    public List<EmployeeProjection> getAllEmployeeProjections() {
        log.info("Getting all employee projections");
        return employeeRepository.findAllEmployeeProjections();
    }

    public List<EmployeeDTO> getAllEmployeeDTOs() {
        log.info("Getting all employee DTOs");
        return employeeRepository.findAllEmployeeDTOs();
    }

    // ========== Exercise 10: Batch Processing ==========
    @Transactional
    public int updateSalariesByDepartment(Long departmentId, Double percentage) {
        log.info("Updating salaries by {}% for department: {}", percentage, departmentId);
        return employeeRepository.updateSalaryByPercentage(departmentId, percentage);
    }

    @Transactional
    public int deleteEmployeesByDepartment(Long departmentId) {
        log.info("Deleting all employees in department: {}", departmentId);
        return employeeRepository.deleteEmployeesByDepartment(departmentId);  // This now exists!
    }
}