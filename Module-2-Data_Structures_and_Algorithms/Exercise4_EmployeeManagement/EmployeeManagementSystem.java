package Exercise4_EmployeeManagement;

import java.util.*;

public class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    
    public EmployeeManagementSystem() {
        this.employees = new Employee[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    public void addEmployee(Employee employee) {
        if (size == employees.length) {
            employees = Arrays.copyOf(employees, employees.length * 2);
        }
        employees[size++] = employee;
    }
    
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }
    
    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }
    
    public boolean deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null;
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        
        ems.addEmployee(new Employee(1, "John Doe", "Manager", 75000));
        ems.addEmployee(new Employee(2, "Jane Smith", "Developer", 65000));
        ems.addEmployee(new Employee(3, "Bob Johnson", "Designer", 55000));
        
        System.out.println("All Employees:");
        ems.traverseEmployees();
        
        System.out.println("\nSearch for employee 2:");
        System.out.println(ems.searchEmployee(2));
        
        ems.deleteEmployee(2);
        System.out.println("\nAfter deleting employee 2:");
        ems.traverseEmployees();
    }
}