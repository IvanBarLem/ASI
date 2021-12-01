package es.udc.asiproject.controller.dto;

import java.util.Objects;

public class FieldErrorDto {
	private String fieldName;
	private String message;

	public FieldErrorDto(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fieldName, message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldErrorDto other = (FieldErrorDto) obj;
		return Objects.equals(fieldName, other.fieldName) && Objects.equals(message, other.message);
	}

	@Override
	public String toString() {
		return "FieldErrorDto [fieldName=" + fieldName + ", message=" + message + "]";
	}
}
