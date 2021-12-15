package es.udc.asiproject.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.AuthenticatedUserDto;
import es.udc.asiproject.controller.dto.ClientDto;
import es.udc.asiproject.controller.dto.UserDto;
import es.udc.asiproject.persistence.model.User;

@Component
public class UserMapper {
	private static ModelMapper mapper;

	@Autowired
	private UserMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static UserDto convertToDto(User user) {
		UserDto userDto = mapper.map(user, UserDto.class);

		return userDto;
	}
	
	public static ClientDto convertToClientDto(User user) {
		ClientDto userDto = mapper.map(user, ClientDto.class);

		return userDto;
	}

	public static User convertToEntity(UserDto userDto) {
		User user = mapper.map(userDto, User.class);

		return user;
	}

	public static AuthenticatedUserDto convertToAuthenticatedUserDto(String serviceToken, User user) {
		return new AuthenticatedUserDto(serviceToken, convertToDto(user));
	}
}
