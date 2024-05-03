package com.openclassrooms.mddapi.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
	
	@NotBlank(message = "L'email est obligatoire !")
	private String email;
	
	@NotBlank(message = "Le nom est obligatoire !")
    private String name;
	
	@NotBlank(message = "Le mot de passe est obligatoire !")
    private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	////////// GETTER SETTER

	
}
