package com.adp.JobTraq.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adp.JobTraq.models.JobApplication;
import com.adp.JobTraq.repositories.JobApplicationRepository;
import com.adp.JobTraq.services.JobApplicationService;

@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobApplicationService jobApplicationService;

    // Get all job applications (Not actually used)
    @GetMapping
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    // Get a job application by its ID (Both maybe)
    @GetMapping("/{id}")
    public JobApplication getJobApplicationById(@PathVariable String id) {
        return jobApplicationRepository.findById(id).orElse(null);
    }

    // Get job applications by jobId (Manager Side)
    @GetMapping("/job/{jobId}")
    public List<JobApplication> getJobApplicationsByJobId(@PathVariable String jobId,
            @RequestParam(value = "minGpa", required = false) Double minGpa) {
        if (minGpa != null){
            return jobApplicationRepository.findByJobIdAndGpaGreaterThanEqual(jobId, minGpa);
        }
        else{
            return jobApplicationRepository.findByJobId(jobId);
        }
    }


    //Get job application by user_id (Applicant Side to see all of their applications)
    @GetMapping("/user/{userId}")
    public List<JobApplication> getJobApplicationsByUserId(@PathVariable String userId) {
        return jobApplicationRepository.findByUserId(userId);
    }

     // Create a new job application (Applicant Side)
    @PostMapping
    public JobApplication createJobApplication(@RequestBody JobApplication jobApplication) {
        return jobApplicationService.saveJobApplication(jobApplication);
    }

    // Delete a job application by id (Applicant Side)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobApplicationById(@PathVariable String id) {
        boolean isDeleted = jobApplicationService.deleteJobApplicationById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Job Application deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Offer a job to a candidate (Manager Side)
    @PatchMapping("/{id}/offer")
    public ResponseEntity<?> updateJobOfferedById(@PathVariable String id, @RequestBody boolean jobOffered) {
        boolean isUpdated = jobApplicationService.updateJobOfferedById(id, jobOffered);
        if (isUpdated) {
            return ResponseEntity.ok("Job Application updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Accept an offer (User Side)
    @PatchMapping("/{id}/accept")
    public ResponseEntity<?> updateJobAcceptedById(@PathVariable String id, @RequestBody boolean jobAccepted) {
        boolean isUpdated = jobApplicationService.updateJobAcceptedById(id, jobAccepted);
        if (isUpdated) {
            return ResponseEntity.ok("Job Application updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deny an Applicant (manager Side)
    @PatchMapping("/{id}/deny")
    public ResponseEntity<?> updateJobDeniedById(@PathVariable String id, @RequestBody boolean jobDenied) {
        boolean isUpdated = jobApplicationService.updateJobDeniedById(id, jobDenied);
        if (isUpdated) {
            return ResponseEntity.ok("Job Application updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}