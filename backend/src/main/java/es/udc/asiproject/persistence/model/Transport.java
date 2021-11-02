package es.udc.asiproject.persistence.model;

import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Transport extends Product {
	public Transport() {
	}

	public Transport(String name) {
		super(name);
	}

	public Transport(Long id, String name) {
		super(id, name);
	}

	public Transport(Long id, String name, Boolean hidden, Set<Pack> packs) {
		super(id, name, hidden, packs);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
