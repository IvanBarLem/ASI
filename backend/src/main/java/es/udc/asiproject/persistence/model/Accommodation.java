package es.udc.asiproject.persistence.model;

import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Accommodation extends Product {
	public Accommodation() {
	}

	public Accommodation(String name) {
		super(name);
	}

	public Accommodation(Long id, String name, Set<Pack> packs) {
		super(id, name, packs);
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
