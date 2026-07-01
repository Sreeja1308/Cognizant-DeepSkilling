package com.example.ormlearn.repository;

import com.example.ormlearn.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    // Hands-on 2: Get all permanent employees with fetch
    @Query(value = "SELECT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = 1")
    List<Employee> getAllPermanentEmployees();
    
    // Hands-on 4: Get average salary
    @Query(value = "SELECT AVG(e.salary) FROM Employee e where e.department.id = :id")
    double getAverageSalary(@Param("id") int id);
    
    // Hands-on 5: Native Query
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}