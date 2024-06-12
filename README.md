# Interactive Trails Project

Welcome to the Interactive Trails Project! This application enhances the experience of exploring towns and supporting local businesses through interactive and engaging trails.

## Features

- **Checkpoint Scanning**: Users can scan QR codes to collect checkpoints and complete trails.
- **Medals and Achievements**: Earn medals for completing trails and tracking progress.
- **User Profiles**: Each user has a profile showcasing their information and progression for each trail started.
- **Leaderboards**: View the top-performing users on the leaderboard.

## Deployment Pipeline

My deployment pipeline ensures a robust CI/CD process, using the following steps:

1. **Terraform Instance Allocation**: Allocate instances on OpenStack for deploying a Jenkins instance.
2. **Automated Acceptance Testing**: 
   - **Integration, Functional, Unit, and System Tests**: Conducted using JUnit and WebDriver.
   - **Selenium Functional Tests**: Executed using Selenium Side Runner.
   - **Linting Tests**: Automated linting with Checkstyle.
3. **Docker Integration**:
   - **Image Creation and Versioning**: Automated creation and versioning of Docker images.
   - **Repository Upload**: Upload images to the Docker repository.
4. **Terraform Deployment**: Deploy to a separate OpenStack instance upon test approval.
5. **Performance Testing**: Conduct JMeter performance tests on the live website.

## Collaboration

This project was developed for VZTA. For more information, visit their [website](https://www.vzta.com/).
