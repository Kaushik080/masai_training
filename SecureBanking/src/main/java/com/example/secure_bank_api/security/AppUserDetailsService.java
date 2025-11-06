package com.example.secure_bank_api.security;

import com.example.secure_bank_api.model.User;
import com.example.secure_bank_api.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public AppUserDetailsService(UserRepository userRepository) { this.userRepository = userRepository; }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found"));
        Collection<? extends GrantedAuthority> auth = List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole()));
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), auth);
    }
}
