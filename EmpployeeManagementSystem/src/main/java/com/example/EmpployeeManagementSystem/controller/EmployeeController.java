package com.example.EmpployeeManagementSystem.controller;
import com.example.EmpployeeManagementSystem.model.Employee;
import com.example.EmpployeeManagementSystem.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws Throwable {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id ,@RequestBody Employee employee){
        Employee updateEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntitiy.ok(updateEmployee);
    }

    @GetMapping("/get/{department}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVaariable String department){
        List<Employee> employees = eemployeeService.getEmployeeByDepartment(department);
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("salarygreaterthan/{salary}")
    public ResponseEntity<Employee> getSalaryGreaterThan(@PathVariable float salary) {
        Employee employee = employeeService.getSalaryGreaterThan(salary);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Employee>> searchByName(@PathVariable String name) {
        List<Employee> employees = employeeService.searchByName(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/averageSalary")
    public ResponseEntity<Double> getAverageSalary() {
        Double averageSalary = employeeService.getAverageSalary();
        return ResponseEntity.ok(averageSalary);
    }
}