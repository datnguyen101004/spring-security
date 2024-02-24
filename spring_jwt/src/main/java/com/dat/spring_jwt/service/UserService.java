package com.dat.spring_jwt.service;

import com.dat.spring_jwt.dto.UserReponse;
import com.dat.spring_jwt.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<User> getAllUsers();
}
