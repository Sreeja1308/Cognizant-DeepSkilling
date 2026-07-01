package com.cognizant.springlearn.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.exception.EmployeeNotFoundException;

@Repository
public class EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);
    private static List<Employee> EMPLOYEE_LIST;

    public EmployeeDao() {
        LOGGER.info("Start");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        @SuppressWarnings("unchecked")
        List<Employee> employeeList = (List<Employee>) context.getBean("employeeList");
        EMPLOYEE_LIST = employeeList;
        LOGGER.info("End");
    }

    public List<Employee> getAllEmployees() {
        LOGGER.info("Start");
        LOGGER.info("End");
        return EMPLOYEE_LIST;
    }

    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        Employee existingEmployee = EMPLOYEE_LIST.stream()
                .filter(e -> e.getId() == employee.getId())
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setPermanent(employee.isPermanent());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSkills(employee.getSkills());
        LOGGER.info("End");
        return existingEmployee;
    }

    public void deleteEmployee(int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        Employee employeeToRemove = EMPLOYEE_LIST.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        EMPLOYEE_LIST.remove(employeeToRemove);
        LOGGER.info("End");
    }
}