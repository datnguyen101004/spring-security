package com.dat.spring_jwt.service.Impl;

import com.dat.spring_jwt.dto.LoginRequest;
import com.dat.spring_jwt.dto.RegisterRequest;
import com.dat.spring_jwt.dto.Reponse;
import com.dat.spring_jwt.entity.Token;
import com.dat.spring_jwt.entity.User;
import com.dat.spring_jwt.repository.TokenRepository;
import com.dat.spring_jwt.repository.UserRepository;
import com.dat.spring_jwt.service.AuthService;
import com.dat.spring_jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public Reponse register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            return new Reponse(null, "Email is exist");
        }
        User user = new User();
        user.setRole(registerRequest.getRole());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        var jwt = jwtService.generateToken(user);
        userRepository.save(user);
        saveTokenForUser(user, jwt);
        return new Reponse(jwt, "Register successfully");
    }

    @Override
    public Reponse login(LoginRequest loginRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new UsernameNotFoundException("username not correct"));
            revokeAllToken(user, false);
            var jwt = jwtService.generateToken(user);
            saveTokenForUser(user, jwt);
            return new Reponse(jwt, "Login successfully");
        }
        catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("username or password is incorrect");
        }
    }

    private void saveTokenForUser(User user, String jwt) {
        Token token = new Token(jwt, user);
        tokenRepository.save(token);
    }

    private void revokeAllToken(User user, boolean enable){
        List<Token> tokens = tokenRepository.findByUser(user);
        for (Token token:tokens){
            token.setEnable(enable);
            tokenRepository.save(token);
        }
    }
}
