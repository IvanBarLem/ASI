package es.udc.asiproject.backend.rest.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class LoginParamsDto {

	private String email;
	private String password;

	public LoginParamsDto() {
	}

	@NotNull
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	@NotNull
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
