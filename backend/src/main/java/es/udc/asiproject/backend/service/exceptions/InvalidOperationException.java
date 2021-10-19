package es.udc.asiproject.backend.service.exceptions;

@SuppressWarnings("serial")
public class InvalidOperationException extends Exception {
	private String code;

	public InvalidOperationException(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
