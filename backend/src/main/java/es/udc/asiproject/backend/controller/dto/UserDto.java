package es.udc.asiproject.backend.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.udc.asiproject.backend.controller.dto.validation.UpdateValidation;

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
	@NotNull(groups = { AllValidations.class, UpdateValidation.class })
	@Size(min = 1, max = 60, groups = { AllValidations.class, UpdateValidation.class })
	@Email(groups = { AllValidations.class, UpdateValidation.class })
	private String email;
	private String role;

	public UserDto() {
	}

	public UserDto(Long id, String firstName, String lastName, String email, String role) {
		this.id = id;
		this.firstName = firstName.trim();
		this.lastName = lastName.trim();
		this.email = email != null ? email.trim() : null;
		this.role = role;
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
