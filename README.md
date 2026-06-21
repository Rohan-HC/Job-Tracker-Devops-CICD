# Job Tracker DevOps CI/CD

A Spring Boot REST API for tracking job applications, delivered through a Jenkins and Docker CI/CD pipeline.

## Architecture

```mermaid
flowchart LR
    GitHub --> Jenkins
    Jenkins --> Tests
    Tests --> Docker
    Docker --> Container
    Container --> Health
