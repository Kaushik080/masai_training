package com.example.EmployeeManagementSystemTest.repository;

import com.example.EmployeeManagementSystemTest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(String Department);
}
