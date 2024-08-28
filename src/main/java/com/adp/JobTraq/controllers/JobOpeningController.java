package com.adp.JobTraq.controllers;

import com.adp.JobTraq.models.JobOpening;
import com.adp.JobTraq.services.JobOpeningService;
import com.adp.JobTraq.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/jobs")
public class JobOpeningController {
    private final JobOpeningService jobOpeningService;

    public JobOpeningController(JobOpeningService jobOpeningService, UserService userService) {
        this.jobOpeningService = jobOpeningService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createJobOpening(@RequestBody JobOpening jobOpening) {
        try {
            JobOpening savedJob = jobOpeningService.createJob(jobOpening);
            return ResponseEntity.ok(savedJob);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create job");
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllJobs() {
        try {
            List<JobOpening> jobs = jobOpeningService.getAllJobs();
            return ResponseEntity.ok(jobs);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJob(@PathVariable String id) {
        try {
            JobOpening job = jobOpeningService.findById(id);
            if (job != null) {
                return ResponseEntity.ok(job);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateJob(@PathVariable String id,@RequestBody JobOpening updatedJob) {
        try {
            JobOpening job = jobOpeningService.updateJob(id, updatedJob);
            if (job != null) {
                return ResponseEntity.ok(job);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable String id) {
        try {
            jobOpeningService.deleteJob(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchJobOpeningsByTitle(@RequestParam String title) {
        try {
            List<JobOpening> jobOpenings = jobOpeningService.searchJobsByTitle(title);
            return ResponseEntity.ok(jobOpenings);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> getJobOpeningsByStatus(@RequestParam String status) {
        try {
            List<JobOpening> jobOpenings = jobOpeningService.getJobsByStatus(status);
            return ResponseEntity.ok(jobOpenings);
        } catch(Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().body("Failed to retrieve jobs: " + e.getMessage());
        }
    }


}
