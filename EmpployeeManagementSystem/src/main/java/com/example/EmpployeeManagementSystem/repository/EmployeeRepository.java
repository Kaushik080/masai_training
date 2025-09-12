package com.example.EmpployeeManagementSystem.repository;

import com.example.EmpployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository {
     List<Employee> findByDepartment(String department);
     List<Employee> findBySalaryGreaterThan(float salary);
     List<Employee> searchByName(String name);
     Double getAverageSalary();
     List<Employee> getAllEmails();
}
