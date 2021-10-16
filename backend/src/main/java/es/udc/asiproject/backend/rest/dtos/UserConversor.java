package es.udc.asiproject.backend.rest.dtos;

import es.udc.asiproject.backend.daos.entities.User;

public class UserConversor {

	private UserConversor() {
	}

	public final static UserDto toUserDto(User user) {
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getRole().toString());
	}

	public final static User toUser(UserDto userDto) {

		return new User(userDto.getPassword(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
	}

	public final static AuthenticatedUserDto toAuthenticatedUserDto(String serviceToken, User user) {

		return new AuthenticatedUserDto(serviceToken, toUserDto(user));

	}

}
