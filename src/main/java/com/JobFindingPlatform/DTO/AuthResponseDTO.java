package com.JobFindingPlatform.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class AuthResponseDTO {

	public String token;
	public String message;

	public AuthResponseDTO() {
	}

	public AuthResponseDTO(String token, String message) {
		this.token = token;
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
