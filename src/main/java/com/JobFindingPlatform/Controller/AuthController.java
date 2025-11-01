package com.JobFindingPlatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.JobFindingPlatform.DTO.AuthResponseDTO;
import com.JobFindingPlatform.DTO.LoginRequestDTO;
import com.JobFindingPlatform.DTO.UserDTO;
import com.JobFindingPlatform.Entity.User;
import com.JobFindingPlatform.Enum.UserStatus;
import com.JobFindingPlatform.Service.AuthService;
import com.JobFindingPlatform.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO dto) {
        User user = userRepository.findByUserEmail(dto.getUserEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getUserStatus() == UserStatus.BLOCKED) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Your account is blocked. Please contact support.");
        }

        return ResponseEntity.ok(authService.login(dto));
    }
}
