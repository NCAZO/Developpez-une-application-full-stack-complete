package com.openclassrooms.mddapi.dto.response;

import lombok.Builder;

@Builder
public class AuthResponse {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
