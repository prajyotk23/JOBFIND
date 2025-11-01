package com.JobFindingPlatform.Entity;

import jakarta.persistence.*;

import com.JobFindingPlatform.Enum.Action;
import com.JobFindingPlatform.Enum.Role;
import com.JobFindingPlatform.Enum.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userName;
	
	@Column(unique = true)
	private String userEmail;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;
	@Column(nullable = false)
	private boolean blocked = false;




	public boolean isBlocked() {
	    return blocked;
	}
	public void setBlocked(boolean blocked) {
	    this.blocked = blocked;
	}




	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	

}
