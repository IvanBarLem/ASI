package es.udc.asiproject.backend.model.services;

@SuppressWarnings("serial")
public class IncorrectLoginException extends Exception {

	private String email;
	private String password;

	public IncorrectLoginException(String email, String password) {

		this.email = email;
		this.password = password;

	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
