package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Exercise 3: Derived Query Methods
    Optional<Department> findByName(String name);

    List<Department> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);

    // Exercise 5: Custom Query with @Query
    @Query("SELECT d FROM Department d WHERE d.name LIKE %:keyword%")
    List<Department> searchDepartments(@Param("keyword") String keyword);

    @Query("SELECT COUNT(e) FROM Department d JOIN d.employees e WHERE d.id = :deptId")
    long countEmployeesInDepartment(@Param("deptId") Long deptId);

    // Exercise 5: Native Query
    @Query(value = "SELECT * FROM departments d WHERE d.name ILIKE %:keyword%", nativeQuery = true)
    List<Department> searchDepartmentsNative(@Param("keyword") String keyword);
}