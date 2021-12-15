package es.udc.asiproject.controller.dto;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(serviceToken, userDto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticatedUserDto other = (AuthenticatedUserDto) obj;
		return Objects.equals(serviceToken, other.serviceToken) && Objects.equals(userDto, other.userDto);
	}

	@Override
	public String toString() {
		return "AuthenticatedUserDto [serviceToken=" + serviceToken + ", userDto=" + userDto + "]";
	}
}
