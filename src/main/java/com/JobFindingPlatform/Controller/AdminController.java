package com.JobFindingPlatform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.JobFindingPlatform.DTO.AdminDTO;
import com.JobFindingPlatform.Entity.Admin;
import com.JobFindingPlatform.Enum.Action;
import com.JobFindingPlatform.Enum.UserStatus;
import com.JobFindingPlatform.Entity.User;
import com.JobFindingPlatform.Repository.UserRepository;
import com.JobFindingPlatform.Service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/action")
    public ResponseEntity<Admin> performAction(@RequestBody AdminDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();

        if (dto.getAction() == Action.BLOCK) {
            user.setUserStatus(UserStatus.BLOCKED);
        } else if (dto.getAction() == Action.UNBLOCK) {
            user.setUserStatus(UserStatus.ACTIVE);
        }

        userRepository.save(user);
        return ResponseEntity.ok(adminService.performanceAction(dto));
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Admin>> getLogByAdmin(@PathVariable Long adminId) {
        return ResponseEntity.ok(adminService.getactionsByAdmin(adminId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Admin>> getLogByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.getActionsByUser(userId));
    }
}
