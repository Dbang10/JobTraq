package com.adp.JobTraq.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.adp.JobTraq.repository.UserRepository;
import com.adp.JobTraq.models.UserModel;
import com.adp.JobTraq.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
        this.userRepository = null;
        this.passwordEncoder = null;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserModel user){
        try {
            if (userRepository.findByEmail(user.getName()).isPresent())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already taken. Please try again");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserModel save = userRepository.save(user);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        UserModel savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable String id,@RequestBody UserModel updatedUser) {
        UserModel user = userService.updateUser(id, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable String id) {
        UserModel user = userService.findById(id);
        if(user != null) {
            return ResponseEntity.ok(user);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getall")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }
}
