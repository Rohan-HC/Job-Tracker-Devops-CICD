package com.rohanhc.jobtracker.jobs;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public List<JobSummary> getAllJobs() {
        return service.getAllJobs();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobSummary createJob(@Valid @RequestBody CreateJobRequest request) {
        return service.createJob(request);
    }
}
