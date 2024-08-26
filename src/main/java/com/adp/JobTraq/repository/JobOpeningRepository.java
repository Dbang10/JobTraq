package com.adp.JobTraq.repository;

import com.adp.JobTraq.models.JobOpening;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobOpeningRepository extends MongoRepository<JobOpening, String> {
    List<JobOpening> findByTitleContainingIgnoreCase(String title);
    List<JobOpening> findByStatus(String status);
}
