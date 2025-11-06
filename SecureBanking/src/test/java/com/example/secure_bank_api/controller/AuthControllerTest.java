package com.example.secure_bank_api.controller;

import com.example.secure_bank_api.dto.AuthRequest;
import com.example.secure_bank_api.model.User;
import com.example.secure_bank_api.security.JwtUtil;
import com.example.secure_bank_api.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void loginOk() throws Exception {
        User u = new User();
        u.setUsername("alice");

        Mockito.when(userService.authenticate(Mockito.eq("alice"), Mockito.eq("pw"))).thenReturn(u);
        Mockito.when(jwtUtil.generateToken("alice")).thenReturn("t");

        String body = "{\"username\":\"alice\",\"password\":\"pw\"}";

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("t"));
    }

    @Test
    void registerOk() throws Exception {
        User u = new User();
        u.setUsername("bob");

        Mockito.when(userService.register(Mockito.any(AuthRequest.class))).thenReturn(u);
        Mockito.when(jwtUtil.generateToken("bob")).thenReturn("t2");

        String body = "{\"username\":\"bob\",\"password\":\"pw\"}";

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("t2"));
    }
}
