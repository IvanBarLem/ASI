package es.udc.asiproject.controller.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordParamsDto {
	@NotNull
	private String oldPassword;
	@NotNull
	@Size(min = 1, max = 60)
	private String newPassword;

	public ChangePasswordParamsDto() {
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(newPassword, oldPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangePasswordParamsDto other = (ChangePasswordParamsDto) obj;
		return Objects.equals(newPassword, other.newPassword) && Objects.equals(oldPassword, other.oldPassword);
	}

	@Override
	public String toString() {
		return "ChangePasswordParamsDto [oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}

}
