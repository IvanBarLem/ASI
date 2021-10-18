package es.udc.asiproject.backend.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.udc.asiproject.backend.controller.dto.AuthenticatedUserDto;
import es.udc.asiproject.backend.controller.dto.ChangePasswordParamsDto;
import es.udc.asiproject.backend.controller.dto.LoginParamsDto;
import es.udc.asiproject.backend.controller.dto.UserDto;
import es.udc.asiproject.backend.controller.dto.UserDto.AllValidations;
import es.udc.asiproject.backend.controller.dto.validation.UpdateValidation;
import es.udc.asiproject.backend.controller.mapper.UserMapper;
import es.udc.asiproject.backend.controller.security.token.JwtGenerator;
import es.udc.asiproject.backend.controller.security.token.JwtInfo;
import es.udc.asiproject.backend.persistence.model.User;
import es.udc.asiproject.backend.service.UserService;
import es.udc.asiproject.backend.service.exceptions.DuplicateInstanceException;
import es.udc.asiproject.backend.service.exceptions.IncorrectLoginException;
import es.udc.asiproject.backend.service.exceptions.IncorrectPasswordException;
import es.udc.asiproject.backend.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.service.exceptions.PermissionException;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private JwtGenerator jwtGenerator;
	@Autowired
	private UserService userService;

	@PostMapping("/signUp")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AuthenticatedUserDto> signUp(
			@Validated({ AllValidations.class }) @RequestBody UserDto userDto) throws DuplicateInstanceException {
		User user = UserMapper.convertToEntity(userDto);

		userService.signUp(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
				.toUri();

		return ResponseEntity.created(location)
				.body(UserMapper.convertToAuthenticatedUserDto(generateServiceToken(user), user));
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public AuthenticatedUserDto login(@Validated @RequestBody LoginParamsDto params) throws IncorrectLoginException {
		User user = userService.login(params.getEmail(), params.getPassword());

		return UserMapper.convertToAuthenticatedUserDto(generateServiceToken(user), user);
	}

	@PostMapping("/loginFromServiceToken")
	@ResponseStatus(HttpStatus.OK)
	public AuthenticatedUserDto loginFromServiceToken(@RequestAttribute Long userId,
			@RequestAttribute String serviceToken) throws InstanceNotFoundException {
		User user = userService.loginFromId(userId);

		return UserMapper.convertToAuthenticatedUserDto(serviceToken, user);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDto updateProfile(@RequestAttribute Long userId, @PathVariable("id") Long id,
			@Validated({ UpdateValidation.class }) @RequestBody UserDto userDto)
			throws InstanceNotFoundException, PermissionException {
		if (!id.equals(userId)) {
			throw new PermissionException();
		}

		return UserMapper.convertToDto(userService.updateProfile(id, userDto.getFirstName(), userDto.getLastName()));
	}

	@PostMapping("/{id}/changePassword")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(@RequestAttribute Long userId, @PathVariable("id") Long id,
			@Validated @RequestBody ChangePasswordParamsDto params)
			throws PermissionException, InstanceNotFoundException, IncorrectPasswordException {
		if (!id.equals(userId)) {
			throw new PermissionException();
		}

		userService.changePassword(id, params.getOldPassword(), params.getNewPassword());
	}

	private String generateServiceToken(User user) {
		JwtInfo jwtInfo = new JwtInfo(user.getId(), user.getEmail(), user.getRole().toString());

		return jwtGenerator.generate(jwtInfo);
	}
}
