package com.JobFindingPlatform.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JobFindingPlatform.DTO.AuthResponseDTO;
import com.JobFindingPlatform.DTO.LoginRequestDTO;
import com.JobFindingPlatform.DTO.UserDTO;
import com.JobFindingPlatform.Entity.User;
import com.JobFindingPlatform.Enum.UserStatus;
import com.JobFindingPlatform.Repository.UserRepository;
import com.JobFindingPlatform.Security.JWTUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    public AuthResponseDTO register(UserDTO dto) {
        String normalizedEmail = dto.getUserEmail().trim().toLowerCase();

        System.out.println("Checking if user exists: " + normalizedEmail);

        Optional<User> existingUser = userRepository.findByUserEmail(normalizedEmail);

        if (existingUser.isPresent()) {
            System.out.println("User already exists: " + normalizedEmail);
            return new AuthResponseDTO(null, "User already registered");
        }

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserEmail(normalizedEmail);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setUserStatus(UserStatus.ACTIVE);

        
        userRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthResponseDTO(token, "User registered successfully");
    }







    public String login(LoginRequestDTO dto) {
        User user = userRepository.findByUserEmail(dto.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user);
    }
}
