package com.adp.JobTraq.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adp.JobTraq.models.JobApplication;

public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {

    List<JobApplication> findByJobId(String jobId);

    List<JobApplication> findByJobIdAndGpaGreaterThanEqual(String jobId, Double gpa);

    List<JobApplication> findByUserId(String userId);
}
