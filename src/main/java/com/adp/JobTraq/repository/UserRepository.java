package com.adp.JobTraq.repository;

import com.adp.JobTraq.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <UserModel, String>{
}
