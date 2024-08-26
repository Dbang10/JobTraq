package com.adp.JobTraq.services;

import com.adp.JobTraq.repository.UserRepository;
import com.adp.JobTraq.entity.AuthUser;
import com.adp.JobTraq.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> authUser = userRepository.findByEmail(email.toLowerCase());
        if (!authUser.isPresent()) {
            throw new UsernameNotFoundException(email);
        } else {
            return User.builder()
                    .username(authUser.get().getEmail())
                    .password(authUser.get().getPassword())
                    .disabled(!authUser.get().isActive())
                    .build();
        }
    }
}
