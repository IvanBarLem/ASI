package es.udc.asiproject.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.udc.asiproject.controller.dto.validation.UpdateValidation;

public class UserDto {
	public interface AllValidations {
	}

	private Long id;
	@NotNull(groups = { AllValidations.class })
	@Size(min = 1, max = 60, groups = { AllValidations.class })
	private String password;
	@NotNull(groups = { AllValidations.class, UpdateValidation.class })
	@Size(min = 1, max = 60, groups = { AllValidations.class, UpdateValidation.class })
	private String firstName;
	@NotNull(groups = { AllValidations.class, UpdateValidation.class })
	@Size(min = 1, max = 60, groups = { AllValidations.class, UpdateValidation.class })
	private String lastName;
	@NotNull(groups = { AllValidations.class })
	@Size(min = 1, max = 60, groups = { AllValidations.class })
	@Email(groups = { AllValidations.class })
	private String email;
	private String role;

	public UserDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName.trim();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
