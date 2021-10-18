package es.udc.asiproject.backend.controller.dto;

import java.util.List;

public class ErrorDto {
	private String globalError;
	private List<FieldErrorDto> fieldErrors;

	public ErrorDto(String globalError) {
		this.globalError = globalError;
	}

	public ErrorDto(List<FieldErrorDto> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public String getGlobalError() {
		return globalError;
	}

	public void setGlobalError(String globalError) {
		this.globalError = globalError;
	}

	public List<FieldErrorDto> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorDto> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
