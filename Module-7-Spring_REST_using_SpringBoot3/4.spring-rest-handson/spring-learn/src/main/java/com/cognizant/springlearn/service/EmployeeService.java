package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.exception.EmployeeNotFoundException;

@Service
public class EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeDao.getAllEmployees();
        LOGGER.info("End");
        return employees;
    }

    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        Employee updatedEmployee = employeeDao.updateEmployee(employee);
        LOGGER.info("End");
        return updatedEmployee;
    }

    public void deleteEmployee(int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeDao.deleteEmployee(id);
        LOGGER.info("End");
    }
}