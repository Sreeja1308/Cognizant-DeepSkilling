package com.example.ormlearn;

import com.example.ormlearn.entity.Employee;
import com.example.ormlearn.entity.Skill;
import com.example.ormlearn.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    
    @Autowired
    private EmployeeService employeeService;
    
    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("=== Starting Spring Data JPA Hands-on ===");
        
        // Test 1: HQL - Get all permanent employees
        testGetAllPermanentEmployees();
        
        // Test 2: Native Query
        testGetAllEmployeesNative();
        
        // Test 3: Aggregate Function - Average Salary
        testGetAverageSalary();
        
        LOGGER.info("=== All tests completed ===");
    }
    
    public void testGetAllPermanentEmployees() {
        LOGGER.info("\n=== TEST 1: Get All Permanent Employees (HQL) ===");
        try {
            List<Employee> employees = employeeService.getAllPermanentEmployees();
            LOGGER.info("Found {} permanent employees", employees.size());
            
            // Use distinct to avoid duplicates
            employees.stream().distinct().forEach(e -> {
                LOGGER.info("Employee: {} (ID: {})", e.getName(), e.getId());
                LOGGER.info("  Department: {}", e.getDepartment().getName());
                
                // Format skills properly
                StringBuilder skills = new StringBuilder();
                if (e.getSkillList() != null && !e.getSkillList().isEmpty()) {
                    for (Skill skill : e.getSkillList()) {
                        if (skills.length() > 0) {
                            skills.append(", ");
                        }
                        skills.append(skill.getName());
                    }
                } else {
                    skills.append("No skills");
                }
                LOGGER.info("  Skills: [{}]", skills.toString());
                
                // Format salary with proper decimal places - FIXED
                LOGGER.info("  Salary: ${}", String.format("%.2f", e.getSalary()));
                LOGGER.info("  Permanent: {}", e.isPermanent());
            });
        } catch (Exception e) {
            LOGGER.error("Error fetching permanent employees: {}", e.getMessage());
        }
    }
    
    public void testGetAllEmployeesNative() {
        LOGGER.info("\n=== TEST 2: Get All Employees (Native Query) ===");
        try {
            List<Employee> employees = employeeService.getAllEmployeesNative();
            LOGGER.info("Found {} employees using native query", employees.size());
            
            employees.forEach(e -> {
                String deptName = e.getDepartment() != null ? e.getDepartment().getName() : "No Dept";
                // Format salary with proper decimal places - FIXED
                LOGGER.info("  - {} ({}), Permanent: {}, Salary: ${}", 
                    e.getName(), 
                    deptName, 
                    e.isPermanent(),
                    String.format("%.2f", e.getSalary())
                );
            });
        } catch (Exception e) {
            LOGGER.error("Error fetching employees with native query: {}", e.getMessage());
        }
    }
    
    public void testGetAverageSalary() {
        LOGGER.info("\n=== TEST 3: Average Salary by Department ===");
        try {
            // Test for department ID 1 (IT department)
            double avgSalaryIT = employeeService.getAverageSalary(1);
            LOGGER.info("Average salary for Department ID 1 (IT): ${}", String.format("%.2f", avgSalaryIT));
            
            // Test for department ID 2 (HR department)
            double avgSalaryHR = employeeService.getAverageSalary(2);
            LOGGER.info("Average salary for Department ID 2 (HR): ${}", String.format("%.2f", avgSalaryHR));
            
            // Test for department ID 3 (Finance department)
            double avgSalaryFinance = employeeService.getAverageSalary(3);
            LOGGER.info("Average salary for Department ID 3 (Finance): ${}", String.format("%.2f", avgSalaryFinance));
        } catch (Exception e) {
            LOGGER.error("Error calculating average salary: {}", e.getMessage());
        }
    }
}