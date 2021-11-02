package es.udc.asiproject.service.exceptions;

@SuppressWarnings("serial")
public abstract class InstanceException extends Exception {
	private String name;
	private Object key;

	protected InstanceException(String message) {
		super(message);
	}

	public InstanceException(String name, Object key) {
		this.name = name;
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public Object getKey() {
		return key;
	}
}
