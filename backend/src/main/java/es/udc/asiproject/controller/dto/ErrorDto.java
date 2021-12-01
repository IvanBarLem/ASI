package es.udc.asiproject.controller.dto;

import java.util.List;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(fieldErrors, globalError);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorDto other = (ErrorDto) obj;
		return Objects.equals(fieldErrors, other.fieldErrors) && Objects.equals(globalError, other.globalError);
	}

	@Override
	public String toString() {
		return "ErrorDto [globalError=" + globalError + ", fieldErrors=" + fieldErrors + "]";
	}
}
