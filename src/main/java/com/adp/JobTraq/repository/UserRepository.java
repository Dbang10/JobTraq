package com.adp.JobTraq.repository;

import com.adp.JobTraq.models.UserModel;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository <UserModel, String>{
   Optional<UserModel> findByEmail(String email);
}
