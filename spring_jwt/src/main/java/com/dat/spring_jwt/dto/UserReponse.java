package com.dat.spring_jwt.dto;

import com.dat.spring_jwt.entity.Role;
import com.dat.spring_jwt.entity.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReponse {
    private String email;
    private Role role;
    private Token token;
}
