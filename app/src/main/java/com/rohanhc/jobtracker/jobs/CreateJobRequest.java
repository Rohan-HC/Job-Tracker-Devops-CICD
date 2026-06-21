package com.rohanhc.jobtracker.jobs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record CreateJobRequest(
        @NotBlank @Size(max = 100) String company,
        @NotBlank @Size(max = 100) String role,
        @NotNull JobStatus status,
        @NotNull @PastOrPresent LocalDate appliedDate
) {
}
