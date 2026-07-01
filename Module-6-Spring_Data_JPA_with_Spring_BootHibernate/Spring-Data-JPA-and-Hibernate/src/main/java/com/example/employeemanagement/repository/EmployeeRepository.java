package com.example.employeemanagement.repository;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.dto.EmployeeProjection;
import com.example.employeemanagement.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // ========== Exercise 3: Derived Query Methods ==========
    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByDepartmentName(String departmentName);

    Optional<Employee> findByEmail(String email);

    List<Employee> findBySalaryGreaterThan(Double salary);

    List<Employee> findByPosition(String position);

    // ========== Exercise 6: Pagination and Sorting ==========
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);

    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // ========== Exercise 5: Custom Query with @Query ==========
    @Query("SELECT e FROM Employee e WHERE e.department.id = :deptId AND e.salary >= :minSalary")
    List<Employee> findEmployeesByDepartmentAndMinSalary(
        @Param("deptId") Long deptId,
        @Param("minSalary") Double minSalary
    );

    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName ORDER BY e.name")
    List<Employee> findEmployeesByDepartmentSorted(@Param("deptName") String deptName);

    // ========== Exercise 8: Projections - Interface-based ==========
    @Query("SELECT e.id as id, e.name as name, e.email as email, e.position as position, d.name as departmentName " +
           "FROM Employee e LEFT JOIN e.department d WHERE e.department.id = :deptId")
    List<EmployeeProjection> findEmployeeProjectionsByDepartment(@Param("deptId") Long deptId);

    @Query("SELECT e.id as id, e.name as name, e.email as email, e.position as position, d.name as departmentName " +
           "FROM Employee e LEFT JOIN e.department d")
    List<EmployeeProjection> findAllEmployeeProjections();

    // ========== Exercise 8: Projections - Class-based ==========
    @Query("SELECT new com.example.employeemanagement.dto.EmployeeDTO(e.id, e.name, e.email, e.position, e.department.name) FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTOs();

    @Query("SELECT e.id as id, e.name as name, e.email as email FROM Employee e")
    List<EmployeeProjection> findAllEmployeeBasicInfo();

    // ========== Exercise 5: Named Query ==========
    @Query(name = "Employee.findByEmail")
    Optional<Employee> findByEmailNamed(@Param("email") String email);

    @Query(name = "Employee.findByDepartmentName")
    List<Employee> findByDepartmentNameNamed(@Param("deptName") String deptName);

    // ========== Exercise 7: Audit queries ==========
    @Query("SELECT e FROM Employee e WHERE e.createdBy = :createdBy")
    List<Employee> findEmployeesCreatedBy(@Param("createdBy") String createdBy);

    // ========== Exercise 10: Hibernate-specific - Batch Processing ==========
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.salary = e.salary * :percentage WHERE e.department.id = :deptId")
    int updateSalaryByPercentage(@Param("deptId") Long deptId, @Param("percentage") Double percentage);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.department.id = :deptId")
    int deleteEmployeesByDepartment(@Param("deptId") Long deptId);  // ← This was missing!
}