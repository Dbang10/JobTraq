package com.adp.JobTraq.services;

import com.adp.JobTraq.repository.UserRepository;
import com.adp.JobTraq.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel createUser(UserModel newUser) {
        return (UserModel) userRepository.save(newUser);
    }

    public UserModel findById(String id) {
        return (UserModel) userRepository.findById(id).orElse(null);
    }

    public UserModel updateUser(String id, UserModel newUser) {
        return (UserModel) userRepository.findById(id).map(userModel -> {
            userModel.setName(newUser.getName());
            userModel.setEmail(newUser.getEmail());
            userModel.setPassword(newUser.getPassword());
            userModel.setRole(newUser.getRole());

            return userRepository.save(userModel);
        }).orElse(null);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<UserModel> getAllUsers(){
        return (List<UserModel>) userRepository.findAll();
    }
}
