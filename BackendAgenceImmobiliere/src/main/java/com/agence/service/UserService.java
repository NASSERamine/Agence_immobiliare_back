package com.agence.service;

import com.agence.dto.UserDTO;
import com.agence.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO getUserByUsername(String username);
    UserDTO getUserByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
} 