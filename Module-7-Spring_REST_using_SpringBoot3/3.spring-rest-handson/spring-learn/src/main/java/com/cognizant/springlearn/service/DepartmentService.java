package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Department;
import com.cognizant.springlearn.dao.DepartmentDao;

@Service
public class DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * Get all departments
     */
    public List<Department> getAllDepartments() {
        LOGGER.info("START - getAllDepartments method");
        List<Department> departments = departmentDao.getAllDepartments();
        LOGGER.debug("Returning {} departments", departments.size());
        LOGGER.info("END - getAllDepartments method");
        return departments;
    }
}