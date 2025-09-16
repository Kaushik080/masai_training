package com.example.EmployeeManagementSystemTest.controller;

import com.example.EmployeeManagementSystemTest.model.Employee;
import com.example.EmployeeManagementSystemTest.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping("/employees")
//    public ResponseEntity<List<Employee>> getAllEmployees(){
//        List<Employee> employees = employeeService.getAllEmployees();
//        return ResponseEntity.ok(employees);
//    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id){
        Optional<Employee> employee = employeeService.getEmployeeByID(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees/{department}")
    public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable String department){
        List<Employee> employee = employeeService.getEmployeeByDepartment(department);
        return ResponseEntity.ok(employee);
    }

}
