package com.leonardo.backend_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationDTO {

    @JsonProperty("login")
    private String login;

    @JsonProperty("senha")
    private String senha;
    
	public String getlogin() {
		return login;
	}
	public void setlogin(String login) {
		this.login = login;
	}
	public String getsenha() {
		return senha;
	}
	public void setsenha(String senha) {
		this.senha = senha;
	}
	
}
