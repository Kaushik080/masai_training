package com.example.EmployeeManagementSystemTest;

import com.example.EmployeeManagementSystemTest.model.Employee;
import com.example.EmployeeManagementSystemTest.repository.EmployeeRepository;
import com.example.EmployeeManagementSystemTest.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployeeById_returnsCorrectEmployee() {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("John");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));
        Employee result = employeeService.getEmployeeById(1L);
        Assertions.assertEquals("John", result.getName());
        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    void getEmployeeById_throwsExceptionWhenNotFound() {
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());
        Assertions.assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(2L));
    }

    @Test
    void getEmployeesByDepartment_returnsCorrectList() {
        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setDepartment("IT");
        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setDepartment("IT");
        List<Employee> employees = Arrays.asList(emp1, emp2);
        when(employeeRepository.findByDepartment("IT")).thenReturn(employees);
        List<Employee> result = employeeService.getEmployeesByDepartment("IT");
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("IT", result.get(0).getDepartment());
    }
}

