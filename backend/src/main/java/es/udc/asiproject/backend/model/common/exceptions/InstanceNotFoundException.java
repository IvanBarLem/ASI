package es.udc.asiproject.backend.model.common.exceptions;

@SuppressWarnings("serial")
public class InstanceNotFoundException extends InstanceException {

	public InstanceNotFoundException(String name, Object key) {
		super(name, key);
	}

}
