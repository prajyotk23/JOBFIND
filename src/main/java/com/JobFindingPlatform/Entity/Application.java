package com.JobFindingPlatform.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import com.JobFindingPlatform.Enum.JobType;
import com.JobFindingPlatform.Enum.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String jobSeekerName;
	@Column(unique = true)
	private String jobSeekerEmail;
	private Long jobId;
	private String jobTitle;
	
	@Enumerated(EnumType.STRING)
	private JobType jobType;
	@Column(unique = true)
	private String recruiterEmail;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	private LocalDateTime appliedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobSeekerName() {
		return jobSeekerName;
	}

	public void setJobSeekerName(String jobSeekerName) {
		this.jobSeekerName = jobSeekerName;
	}

	public String getJobSeekerEmail() {
		return jobSeekerEmail;
	}

	public void setJobSeekerEmail(String jobSeekerEmail) {
		this.jobSeekerEmail = jobSeekerEmail;
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

	public String getRecruiterEmail() {
		return recruiterEmail;
	}

	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
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
