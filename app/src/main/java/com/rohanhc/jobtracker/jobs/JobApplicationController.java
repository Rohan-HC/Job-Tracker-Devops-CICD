package com.rohanhc.jobtracker.jobs;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobApplicationController {

    @GetMapping
    public List<JobSummary> getAllJobs() {
        return List.of();
    }
}
