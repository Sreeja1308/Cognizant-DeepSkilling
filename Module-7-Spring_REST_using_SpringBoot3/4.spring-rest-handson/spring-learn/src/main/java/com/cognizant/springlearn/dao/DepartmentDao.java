package com.cognizant.springlearn.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.Department;

@Repository
public class DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDao.class);

    private static List<Department> DEPARTMENT_LIST;

    public DepartmentDao() {
        LOGGER.info("START - DepartmentDao Constructor");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        @SuppressWarnings("unchecked")
        List<Department> departmentList = (List<Department>) context.getBean("departmentList");
        DEPARTMENT_LIST = departmentList;
        LOGGER.debug("Loaded {} departments from XML", DEPARTMENT_LIST.size());
        LOGGER.info("END - DepartmentDao Constructor");
    }

    public List<Department> getAllDepartments() {
        LOGGER.info("START - getAllDepartments method");
        LOGGER.debug("Returning {} departments", DEPARTMENT_LIST.size());
        LOGGER.info("END - getAllDepartments method");
        return DEPARTMENT_LIST;
    }
}