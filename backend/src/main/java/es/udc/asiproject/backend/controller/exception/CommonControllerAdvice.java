package es.udc.asiproject.backend.controller.exception;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.udc.asiproject.backend.controller.dto.ErrorDto;
import es.udc.asiproject.backend.controller.dto.FieldErrorDto;
import es.udc.asiproject.backend.service.exceptions.DuplicateInstanceException;
import es.udc.asiproject.backend.service.exceptions.IncorrectLoginException;
import es.udc.asiproject.backend.service.exceptions.IncorrectPasswordException;
import es.udc.asiproject.backend.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.backend.service.exceptions.PermissionException;

@ControllerAdvice
public class CommonControllerAdvice {
	@Autowired
	private MessageSource messageSource;

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<FieldErrorDto> fieldErrors = exception.getBindingResult().getFieldErrors().stream()
				.map(error -> new FieldErrorDto(error.getField(), error.getDefaultMessage()))
				.collect(Collectors.toList());

		return new ErrorDto(fieldErrors);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DuplicateInstanceException.class)
	public ErrorDto handleDuplicateInstanceException(DuplicateInstanceException exception, Locale locale) {
		String nameMessage = messageSource.getMessage(exception.getName(), null, exception.getName(), locale);
		String errorMessage = messageSource.getMessage(DuplicateInstanceException.class.getSimpleName(),
				new Object[] { nameMessage, exception.getKey().toString() },
				DuplicateInstanceException.class.getSimpleName(), locale);

		return new ErrorDto(errorMessage);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(IncorrectLoginException.class)
	public ErrorDto handleIncorrectLoginException(IncorrectLoginException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(IncorrectLoginException.class.getSimpleName(), null,
				IncorrectLoginException.class.getSimpleName(), locale);

		return new ErrorDto(errorMessage);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(IncorrectPasswordException.class)
	public ErrorDto handleIncorrectPasswordException(IncorrectPasswordException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(IncorrectPasswordException.class.getSimpleName(), null,
				IncorrectPasswordException.class.getSimpleName(), locale);

		return new ErrorDto(errorMessage);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(InstanceNotFoundException.class)
	public ErrorDto handleInstanceNotFoundException(InstanceNotFoundException exception, Locale locale) {
		String nameMessage = messageSource.getMessage(exception.getName(), null, exception.getName(), locale);
		String errorMessage = messageSource.getMessage(InstanceNotFoundException.class.getSimpleName(),
				new Object[] { nameMessage, exception.getKey().toString() },
				InstanceNotFoundException.class.getSimpleName(), locale);

		return new ErrorDto(errorMessage);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(PermissionException.class)
	public ErrorDto handlePermissionException(PermissionException exception, Locale locale) {
		String errorMessage = messageSource.getMessage(PermissionException.class.getSimpleName(), null,
				PermissionException.class.getSimpleName(), locale);

		return new ErrorDto(errorMessage);
	}
}
