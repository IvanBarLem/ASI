package es.udc.asiproject.backend.model.services;

@SuppressWarnings("serial")
public class InvalidUserException extends Exception{
	private Long id;

	public InvalidUserException(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
}
