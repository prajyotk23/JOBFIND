package com.JobFindingPlatform.Service;

import org.springframework.stereotype.Service;

import com.JobFindingPlatform.DTO.ApplicationStatusDTO;
import com.JobFindingPlatform.DTO.JobPostStatusDTO;
import com.JobFindingPlatform.DTO.UserStatusDTO;

@Service
public class DashBoardService {

    public JobPostStatusDTO fetchJobStatus() {
        JobPostStatusDTO jobs = new JobPostStatusDTO();
        jobs.setTotalJobs(120);
        jobs.setTotalInternships(70);
        jobs.setTotalPartTimeJobs(70);
        jobs.setTotalFullTimeJobs(50);
        jobs.setTotalContractualJobs(50);
        jobs.setTotalFreelanceJobs(50);
        return jobs;
    }

    public ApplicationStatusDTO fetchApplicationStatus() {
        ApplicationStatusDTO applicant = new ApplicationStatusDTO();
        applicant.setTotalApplications(500);
        applicant.setTotalShortlisted(100);
        applicant.setTotalRejected(150);
        applicant.setTotalPending(200);
        return applicant;
    }

    public UserStatusDTO fetchUsersStatus() {
        UserStatusDTO users = new UserStatusDTO();
        users.setTotalJobseekers(10000);
        users.setTotalRecruiters(500);
        users.setTotalBlockUsers(1000);
        users.setTotalPaidUsers(500);
        return users;
    }
}
