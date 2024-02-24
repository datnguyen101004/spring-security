package com.dat.spring_jwt.service.Impl;

import com.dat.spring_jwt.entity.Token;
import com.dat.spring_jwt.entity.User;
import com.dat.spring_jwt.repository.TokenRepository;
import com.dat.spring_jwt.repository.UserRepository;
import com.dat.spring_jwt.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    public String deleteUserById(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("id is not exist"));
            deleteTokenOfUser(user);
            userRepository.delete(user);
            return "User delete successfully";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    private void deleteTokenOfUser(User user){
        List<Token> tokens = tokenRepository.findByUser(user);
        for (Token token : tokens){
            tokenRepository.delete(token);
        }
    }
}
