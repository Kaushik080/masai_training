package com.example.EmployeeManagementSystemTest.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    private Long id;
    private String name;
    private String department;

//    public Employee(Long id, String name, String department) {
//        this.id = id;
//        this.name = name;
//        this.department = department;
//    }
}
