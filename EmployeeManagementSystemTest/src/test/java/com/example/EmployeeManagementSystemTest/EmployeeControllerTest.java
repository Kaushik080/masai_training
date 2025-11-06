package com.example.EmployeeManagementSystemTest.controller;

import com.example.EmployeeManagementSystemTest.model.Employee;
import com.example.EmployeeManagementSystemTest.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployeeById_returnsValidJson() throws Exception {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("John");
        emp.setDepartment("IT");
        when(employeeService.getEmployeeById(1L)).thenReturn(emp);
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.department").value("IT"));
    }

    @Test
    void getEmployeeById_returnsErrorWhenNotFound() throws Exception {
        when(employeeService.getEmployeeById(2L)).thenThrow(new RuntimeException("Not found"));
        mockMvc.perform(get("/employees/2"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getEmployeesByDepartment_returnsCorrectList() throws Exception {
        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setName("John");
        emp1.setDepartment("IT");
        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setName("Jane");
        emp2.setDepartment("IT");
        List<Employee> employees = Arrays.asList(emp1, emp2);
        when(employeeService.getEmployeesByDepartment("IT")).thenReturn(employees);
        mockMvc.perform(get("/employees/department/IT"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[0].department").value("IT"))
                .andExpect(jsonPath("$[1].department").value("IT"));
    }
}
