package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryRepository countryRepository;
    private static StockRepository stockRepository;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;

    public static void main(String[] args) throws ParseException {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

        countryRepository = context.getBean(CountryRepository.class);
        stockRepository = context.getBean(StockRepository.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);

        LOGGER.info("===============================================");
        LOGGER.info("HANDS-ON 1: QUERY METHODS ON COUNTRY TABLE");
        LOGGER.info("===============================================");
        testCountryQueryMethods();

        LOGGER.info("\n===============================================");
        LOGGER.info("HANDS-ON 2: QUERY METHODS ON STOCK TABLE");
        LOGGER.info("===============================================");
        testStockQueryMethods();

        LOGGER.info("\n===============================================");
        LOGGER.info("HANDS-ON 4: MANY TO ONE RELATIONSHIP");
        LOGGER.info("===============================================");
        testGetEmployee();
        testAddEmployee();
        testUpdateEmployee();

        LOGGER.info("\n===============================================");
        LOGGER.info("HANDS-ON 5: ONE TO MANY RELATIONSHIP");
        LOGGER.info("===============================================");
        testGetDepartment();

        LOGGER.info("\n===============================================");
        LOGGER.info("HANDS-ON 6: MANY TO MANY RELATIONSHIP");
        LOGGER.info("===============================================");
        testGetEmployeeWithSkills();
        testAddSkillToEmployee();
    }

    // HANDS-ON 1: Country Query Methods
    private static void testCountryQueryMethods() {
        // 1.1 Search by containing text
        LOGGER.info("1.1 Countries containing 'ou':");
        List<Country> countriesContaining = countryRepository.findByNameContaining("ou");
        countriesContaining.forEach(c -> LOGGER.info("{} {}", c.getCode(), c.getName()));

        // 1.2 Search by containing text with ascending order
        LOGGER.info("\n1.2 Countries containing 'ou' (ascending order):");
        List<Country> countriesContainingOrdered = countryRepository.findByNameContainingOrderByNameAsc("ou");
        countriesContainingOrdered.forEach(c -> LOGGER.info("{} {}", c.getCode(), c.getName()));

        // 1.3 Search by starting text
        LOGGER.info("\n1.3 Countries starting with 'Z':");
        List<Country> countriesStartingWith = countryRepository.findByNameStartingWith("Z");
        countriesStartingWith.forEach(c -> LOGGER.info("{} {}", c.getCode(), c.getName()));
    }

    // HANDS-ON 2: Stock Query Methods
    private static void testStockQueryMethods() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 2.1 Facebook stocks in September 2019
        LOGGER.info("2.1 Facebook stocks in September 2019:");
        Date startDate = sdf.parse("2019-09-01");
        Date endDate = sdf.parse("2019-09-30");
        List<Stock> fbStocks = stockRepository.findByCodeAndDateBetween("FB", startDate, endDate);
        fbStocks.forEach(s -> LOGGER.info("{} {} {} {} {}", s.getCode(), s.getDate(), 
                                          s.getOpen(), s.getClose(), s.getVolume()));

        // 2.2 Google stocks > 1250
        LOGGER.info("\n2.2 Google stocks > 1250:");
        List<Stock> googleStocks = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", 1250.00);
        googleStocks.forEach(s -> LOGGER.info("{} {} {} {} {}", s.getCode(), s.getDate(), 
                                              s.getOpen(), s.getClose(), s.getVolume()));

        // 2.3 Top 3 dates with highest volume
        LOGGER.info("\n2.3 Top 3 dates with highest volume:");
        List<Stock> topVolumeStocks = stockRepository.findTop3ByOrderByVolumeDesc();
        topVolumeStocks.forEach(s -> LOGGER.info("{} {} {} {} {}", s.getCode(), s.getDate(), 
                                                 s.getOpen(), s.getClose(), s.getVolume()));

        // 2.4 Three dates when Netflix stocks were lowest
        LOGGER.info("\n2.4 Three dates when Netflix stocks were lowest:");
        List<Stock> lowestNetflix = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
        lowestNetflix.forEach(s -> LOGGER.info("{} {} {} {} {}", s.getCode(), s.getDate(), 
                                               s.getOpen(), s.getClose(), s.getVolume()));
    }

    // HANDS-ON 4: Many to One Relationship
    private static void testGetEmployee() {
        LOGGER.info("Start testGetEmployee");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Department: {}", employee.getDepartment());
        LOGGER.info("End testGetEmployee");
    }

    private static void testAddEmployee() {
        LOGGER.info("Start testAddEmployee");
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setSalary(75000.00);
        employee.setPermanent(true);
        employee.setDateOfBirth(new Date());
        employee.setDepartment(departmentService.get(1));
        employeeService.save(employee);
        LOGGER.debug("Employee saved: {}", employee);
        LOGGER.info("End testAddEmployee");
    }

    private static void testUpdateEmployee() {
        LOGGER.info("Start testUpdateEmployee");
        Employee employee = employeeService.get(1);
        employee.setDepartment(departmentService.get(2));
        employeeService.save(employee);
        LOGGER.debug("Employee updated: {}", employee);
        LOGGER.info("End testUpdateEmployee");
    }

    // HANDS-ON 5: One to Many Relationship
    private static void testGetDepartment() {
        LOGGER.info("Start testGetDepartment");
        com.cognizant.ormlearn.model.Department department = departmentService.get(1);
        LOGGER.debug("Department: {}", department);
        LOGGER.debug("Employee List: {}", department.getEmployeeList());
        LOGGER.info("End testGetDepartment");
    }

    // HANDS-ON 6: Many to Many Relationship
    private static void testGetEmployeeWithSkills() {
        LOGGER.info("Start testGetEmployeeWithSkills");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Department: {}", employee.getDepartment());
        LOGGER.debug("Skills: {}", employee.getSkillList());
        LOGGER.info("End testGetEmployeeWithSkills");
    }

    private static void testAddSkillToEmployee() {
        LOGGER.info("Start testAddSkillToEmployee");
        Employee employee = employeeService.get(1);
        Skill skill = skillService.get(1);
        employee.getSkillList().add(skill);
        employeeService.save(employee);
        LOGGER.debug("Skill added to Employee: {}", employee);
        LOGGER.info("End testAddSkillToEmployee");
    }
}