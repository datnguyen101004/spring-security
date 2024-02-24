package com.dat.spring_jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private boolean enable;
    @CreationTimestamp
    private Date createAt;
    private Date expiredAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Token(String token, User user){
        this.enable = true;
        this.token = token;
        this.expiredAt = new Date(System.currentTimeMillis()+30*60*1000);
        this.user = user;
    }

    public Token(String token, User user, boolean enable){
        this.enable = enable;
        this.token = token;
        this.expiredAt = new Date(System.currentTimeMillis()+30*60*1000);
        this.user = user;
    }
}
