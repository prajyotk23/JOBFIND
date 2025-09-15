package com.JobFindingPlatform.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import com.JobFindingPlatform.Enum.JobType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jobPosts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPost {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String jobTitle;
	
	@Enumerated(EnumType.STRING)
	private JobType jobType;
	private String jobLocation;
	private String remote;
	private String companyName;
	private String jobCatogory;
	private String jobDescription;
	private String postedBy;
	private LocalDateTime postedAt;
	
	private boolean active = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getRemote() {
		return remote;
	}

	public void setRemote(String remote) {
		this.remote = remote;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobCatogory() {
		return jobCatogory;
	}

	public void setJobCatogory(String jobCatogory) {
		this.jobCatogory = jobCatogory;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	

}

