package es.udc.asiproject.controller.dto;

import java.util.Objects;

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
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role);
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + "]";
	}
}
