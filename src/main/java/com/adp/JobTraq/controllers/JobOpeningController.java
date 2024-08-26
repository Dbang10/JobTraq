package com.adp.JobTraq.controllers;

import com.adp.JobTraq.models.JobOpening;
import com.adp.JobTraq.models.UserModel;
import com.adp.JobTraq.services.JobOpeningService;
import com.adp.JobTraq.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
public class JobOpeningController {
    private final JobOpeningService jobOpeningService;

    public JobOpeningController(JobOpeningService jobOpeningService, UserService userService) {
        this.jobOpeningService = jobOpeningService;
    }

    @PostMapping("/create")
    public ResponseEntity<JobOpening> createJobOpening(@RequestBody JobOpening jobOpening) {
        JobOpening savedJob = jobOpeningService.createJob(jobOpening);
        return ResponseEntity.ok(savedJob);
    }

    @GetMapping("/getall")
    public List<JobOpening> getAllJobs() {
        return jobOpeningService.getAllJobs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOpening> getJob(@PathVariable String id) {
        JobOpening job = jobOpeningService.findById(id);
        if(job != null) {
            return ResponseEntity.ok(job);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JobOpening> updateJob(@PathVariable String id,@RequestBody JobOpening updatedJob) {
        JobOpening job = jobOpeningService.updateJob(id, updatedJob);
        if (job != null) {
            return ResponseEntity.ok(job);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteJob(@PathVariable String id) {
        jobOpeningService.deleteJob(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobOpening>> searchJobOpeningsByTitle(@RequestParam String title) {
        List<JobOpening> jobOpenings = jobOpeningService.searchJobsByTitle(title);
        return ResponseEntity.ok(jobOpenings);
    }

    @GetMapping("/status")
    public ResponseEntity<List<JobOpening>> getJobOpeningsByStatus(@RequestParam String status) {
        List<JobOpening> jobOpenings = jobOpeningService.getJobsByStatus(status);
        return ResponseEntity.ok(jobOpenings);
    }

}
