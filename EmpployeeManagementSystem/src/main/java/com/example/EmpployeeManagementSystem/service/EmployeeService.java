package com.example.EmpployeeManagementSystem.service;

import com.example.EmpployeeManagementSystem.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id) throws Throwable;
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    Employee getEmployeeByEmail(String email);
    Employee getSalaryGreaterThan(float salary);
    List<Employee> searchByName(String name);
    Double getAverageSalary();
    List<Employee> getAllEmails();
}
