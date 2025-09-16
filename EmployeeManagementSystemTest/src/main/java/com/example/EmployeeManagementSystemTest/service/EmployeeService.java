package com.example.EmployeeManagementSystemTest.service;

import com.example.EmployeeManagementSystemTest.model.Employee;
import com.example.EmployeeManagementSystemTest.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeByDepartment(String department){
        return employeeRepository.findByDepartment(department);
    }

    public Optional<Employee> getEmployeeByID(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
