package com.JobFindingPlatform.DTO;

public class UserStatusDTO {

	public int totalJobseekers;
	public int totalRecruiters;
	public int totalBlockUsers;
	public int totalPaidUsers;
	
	public UserStatusDTO() {}
	public UserStatusDTO(int totalJobseekers, int totalRecruiters, int totalBlockUsers) {
	this.totalJobseekers = totalJobseekers;
	this.totalRecruiters = totalRecruiters;
	this.totalBlockUsers = totalBlockUsers;
	this.totalJobseekers = totalPaidUsers;
	
	}
	public int getTotalJobseekers() {
		return totalJobseekers;
	}
	public void setTotalJobseekers(int totalJobseekers) {
		this.totalJobseekers = totalJobseekers;
	}
	public int getTotalRecruiters() {
		return totalRecruiters;
	}
	public void setTotalRecruiters(int totalRecruiters) {
		this.totalRecruiters = totalRecruiters;
	}
	public int getTotalBlockUsers() {
		return totalBlockUsers;
	}
	public void setTotalBlockUsers(int totalBlockUsers) {
		this.totalBlockUsers = totalBlockUsers;
	}
	public int getTotalPaidUser() {
		return totalPaidUsers;
	}
	public void setTotalPaidUsers(int totalPaidUser) {
		this.totalPaidUsers = totalPaidUser;
	}
	
	
}
