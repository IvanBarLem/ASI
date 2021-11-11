package es.udc.asiproject.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticatedUserDto {
	private String serviceToken;
	@JsonProperty("user")
	private UserDto userDto;

	public AuthenticatedUserDto() {
	}

	public AuthenticatedUserDto(String serviceToken, UserDto userDto) {
		this.serviceToken = serviceToken;
		this.userDto = userDto;
	}

	public String getServiceToken() {
		return serviceToken;
	}

	public void setServiceToken(String serviceToken) {
		this.serviceToken = serviceToken;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
}
