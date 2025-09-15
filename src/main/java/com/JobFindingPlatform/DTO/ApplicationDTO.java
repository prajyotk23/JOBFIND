package com.JobFindingPlatform.DTO;

import java.time.LocalDateTime;

import com.JobFindingPlatform.Enum.JobType;
import com.JobFindingPlatform.Enum.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {
	private String jobseekerName;
	private String jobSeekerEmail;
	private String recruiterEmail;
	private Long jobId;
	private String jobTitle;
	private JobType jobType;
	private Status status;
	private LocalDateTime appliedAt;

	public String getJobseekerName() {
		return jobseekerName;
	}

	public void setJobseekerName(String jobseekerName) {
		this.jobseekerName = jobseekerName;
	}

	public String getJobSeekerEmail() {
		return jobSeekerEmail;
	}

	public void setJobSeekerEmail(String jobSeekerEmail) {
		this.jobSeekerEmail = jobSeekerEmail;
	}

	public String getRecruiterEmail() {
		return recruiterEmail;
	}

	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}

}
