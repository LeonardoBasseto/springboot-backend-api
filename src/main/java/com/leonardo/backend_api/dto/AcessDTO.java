package com.leonardo.backend_api.dto;

public class AcessDTO {

	private String token;
	
	public AcessDTO(String token) {
		super();
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}