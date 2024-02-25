package com.dat.spring_jwt;

import com.dat.spring_jwt.entity.Role;
import com.dat.spring_jwt.entity.User;
import com.dat.spring_jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringJwtApplication implements CommandLineRunner {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> user = userRepository.findByRole(Role.ADMIN);
		if (user.isEmpty()){
			User adminUser = new User();
			adminUser.setRole(Role.ADMIN);
			adminUser.setPassword(passwordEncoder.encode("sup3r_adm1n"));
			adminUser.setEmail("admin@gmail.com");
			userRepository.save(adminUser);
		}
	}
}
