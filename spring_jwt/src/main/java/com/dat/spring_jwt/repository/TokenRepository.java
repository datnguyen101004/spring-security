package com.dat.spring_jwt.repository;

import com.dat.spring_jwt.entity.Token;
import com.dat.spring_jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findByUser(User user);
    Token findByEnableAndUser(boolean enable, User user);
}
