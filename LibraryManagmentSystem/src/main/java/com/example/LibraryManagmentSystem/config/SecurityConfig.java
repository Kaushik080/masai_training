package com.example.LibraryManagmentSystem.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN", "LIBRARIAN", "STUDENT")
                .build();

        UserDetails librarian = User.builder()
                .username("librarian")
                .password(passwordEncoder().encode("lib123"))
                .roles("LIBRARIAN", "STUDENT")
                .build();

        UserDetails student = User.builder()
                .username("student")
                .password(passwordEncoder().encode("student123"))
                .roles("STUDENT")
                .build();

        UserDetails guest = User.builder()
                .username("guest")
                .password(passwordEncoder().encode("guest123"))
                .roles("GUEST")
                .build();

        return new InMemoryUserDetailsManager(admin,librarian,student,guest);
    }

    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/about", "/login", "/books/public", "/css/**").permitAll()
                        .requestMatchers("/books", "/books/*").hasRole("STUDENT")
                        .requestMatchers("/books/reserve/**").hasRole("STUDENT")
                        .requestMatchers("/books/add", "/books/edit/**", "/reservations/**").hasRole("LIBRARIAN")
                        .requestMatchers("/admin/**", "/users").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/books", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                )
                .rememberMe(remember -> remember
                        .key("library-remember-me")
                        .tokenValiditySeconds(86400)
                );

        return http.build();
    }
}
