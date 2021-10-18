package es.udc.asiproject.backend.service.exceptions;

@SuppressWarnings("serial")
public class DuplicateInstanceException extends InstanceException {
	public DuplicateInstanceException(String name, Object key) {
		super(name, key);
	}
}
