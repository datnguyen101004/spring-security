package com.dat.spring_jwt.service.Impl;

import com.dat.spring_jwt.dto.UserReponse;
import com.dat.spring_jwt.entity.Token;
import com.dat.spring_jwt.entity.User;
import com.dat.spring_jwt.repository.TokenRepository;
import com.dat.spring_jwt.repository.UserRepository;
import com.dat.spring_jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Username is not exist"));
            }
        };
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
