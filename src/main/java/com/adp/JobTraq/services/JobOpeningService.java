package com.adp.JobTraq.services;

import com.adp.JobTraq.models.JobOpening;
import com.adp.JobTraq.repository.JobOpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOpeningService {
    @Autowired
    private JobOpeningRepository repo;

    public JobOpening createJob(JobOpening newJob) {
        return (JobOpening) repo.save(newJob);
    }

    public List<JobOpening> getAllJobs() {
        return (List<JobOpening>) repo.findAll();
    }

    public JobOpening findById(String id) {
        return (JobOpening) repo.findById(id).orElse(null);
    }

    public JobOpening updateJob(String id, JobOpening newJob) {
        return (JobOpening) repo.findById(id).map(jobOpening -> {
            jobOpening.setTitle(newJob.getTitle());
            jobOpening.setDescription(newJob.getDescription());
            jobOpening.setCity(newJob.getCity());
            jobOpening.setCountry(newJob.getCountry());
            jobOpening.setState(newJob.getState());
            jobOpening.setCompanyName(newJob.getCompanyName());
            jobOpening.setStatus(newJob.getStatus());
            jobOpening.setSalary(newJob.getSalary());

            return repo.save(jobOpening);
        }).orElse(null);
    }


    public void deleteJob(String id) {
        repo.deleteById(id);
    }

    public List<JobOpening> searchJobsByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }

    public List<JobOpening> getJobsByStatus(String status) {
        return repo.findByStatus(status);
    }


}
