package es.udc.asiproject.backend.controller.dto;

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
}
