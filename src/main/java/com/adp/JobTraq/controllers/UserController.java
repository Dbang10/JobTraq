package com.adp.JobTraq.controllers;

import com.adp.JobTraq.models.UserModel;
import com.adp.JobTraq.services.UserService;
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
