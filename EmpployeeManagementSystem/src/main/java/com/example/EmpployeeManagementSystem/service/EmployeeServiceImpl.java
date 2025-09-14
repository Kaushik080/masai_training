package com.example.EmpployeeManagementSystem.service;

import com.example.EmpployeeManagementSystem.model.Employee;
import com.example.EmpployeeManagementSystem.repository.EmployeeRepository;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee saveEmployee(Employee employee) {
        return (Employee) employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) throws Throwable {
        return (Employee) employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return (Employee) employeeRepository.findByEmail(email);
    }

    @Override
    public Employee getSalaryGreaterThan(float salary) {
        return (Employee) employeeRepository.findBySalaryGreaterThan(salary);
    }

    @Override
    public List<Employee> searchByName(String name) {
        return List.of();
    }

    @Override
    public Double getAverageSalary() {
        return 0.0;
    }

    @Override
    public List<Employee> getAllEmails() {
        return List.of();
    }
}
