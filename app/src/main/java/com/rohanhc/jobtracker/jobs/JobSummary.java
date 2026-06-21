	package com.rohanhc.jobtracker.jobs;

import java.time.LocalDate;

public record JobSummary(
        Long id,
        String company,
        String role,
        String status,
        LocalDate appliedDate
) {
}
