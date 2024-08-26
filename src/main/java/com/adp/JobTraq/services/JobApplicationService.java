package com.adp.JobTraq.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.adp.JobTraq.models.JobApplication;
import com.adp.JobTraq.repositories.JobApplicationRepository;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    // Get all job applications
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    // Get a job application by its ID
    public JobApplication getJobApplicationById(String id) {
        return jobApplicationRepository.findById(id).orElse(null);
    }

    // Get job applications by jobId
    public List<JobApplication> getJobApplicationsByJobId(String jobId) {
        return jobApplicationRepository.findByJobId(jobId);
    }

    // Get job applications by userId
    public List<JobApplication> getJobApplicationsByUserId(String userId) {
        return jobApplicationRepository.findByUserId(userId);
    }

    // Save a new job application
    public JobApplication saveJobApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }


    // Delete a job application by id
    public boolean deleteJobApplicationById(String id) {
        if (jobApplicationRepository.existsById(id)) {
            jobApplicationRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Offer a job to an applicant 
    public boolean updateJobOfferedById(String id, boolean jobOffered) {
        return jobApplicationRepository.findById(id).map(application -> {
            application.setJobOffered(jobOffered);
            jobApplicationRepository.save(application);
            return true;
        }).orElse(false);
    }

    // Applicant side - accept an offer 
    public boolean updateJobAcceptedById(String id, boolean jobAccepted) {
        return jobApplicationRepository.findById(id).map(application -> {
            application.setJobAccepted(jobAccepted);
            jobApplicationRepository.save(application);
            return true;
        }).orElse(false);
    }

    // Manager side - deny an offer 
    public boolean updateJobDeniedById(String id, boolean jobDenied) {
        return jobApplicationRepository.findById(id).map(application -> {
            application.setJobDenied(jobDenied);
            jobApplicationRepository.save(application);
            return true;
        }).orElse(false);
    }
   
}
