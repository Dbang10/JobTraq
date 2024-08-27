package com.adp.JobTraq.controllers;

import com.adp.JobTraq.models.UserModel;
import com.adp.JobTraq.services.UserService;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
        try {
            UserModel savedUser = userService.createUser(user);
            return ResponseEntity.ok(savedUser);
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create user");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id,@RequestBody UserModel updatedUser) {
        try {
            UserModel user = userService.updateUser(id, updatedUser);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            UserModel user = userService.findById(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserModel> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(users);
            }
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to get all Users");
        }
    }

}
