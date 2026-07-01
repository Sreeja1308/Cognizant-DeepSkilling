package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.dao.EmployeeDao;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * Get all employees
     */
    public List<Employee> getAllEmployees() {
        LOGGER.info("START - getAllEmployees method");
        List<Employee> employees = employeeDao.getAllEmployees();
        LOGGER.debug("Returning {} employees", employees.size());
        LOGGER.info("END - getAllEmployees method");
        return employees;
    }
}