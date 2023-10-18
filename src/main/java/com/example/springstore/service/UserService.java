package com.example.springstore.service;

import com.example.springstore.dto.UserDTO;

import com.example.springstore.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);
    void save(User user);

    List<UserDTO> findAll();

    User findByName(String name);

    void updateProfile(UserDTO userDTO);
}
