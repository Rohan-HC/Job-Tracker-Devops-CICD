package com.rohanhc.jobtracker.jobs;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public List<JobSummary> getAllJobs() {
        return repository.findAll()
                .stream()
                .map(this::toSummary)
                .toList();
    }

    public JobSummary createJob(CreateJobRequest request) {
        JobApplication job = new JobApplication(
                request.company(),
                request.role(),
                request.status(),
                request.appliedDate()
        );

        return toSummary(repository.save(job));
    }

    private JobSummary toSummary(JobApplication job) {
        return new JobSummary(
                job.getId(),
                job.getCompany(),
                job.getRole(),
                job.getStatus().name(),
                job.getAppliedDate()
        );
    }
}
