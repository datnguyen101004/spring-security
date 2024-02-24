package com.dat.spring_jwt.service;

import com.dat.spring_jwt.dto.LoginRequest;
import com.dat.spring_jwt.dto.RegisterRequest;
import com.dat.spring_jwt.dto.Reponse;

public interface AuthService {
    Reponse register(RegisterRequest registerRequest);

    Reponse login(LoginRequest loginRequest);
}
