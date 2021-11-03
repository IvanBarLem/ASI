package es.udc.asiproject.persistence.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Travel extends Product {
	public Travel() {
	}

	public Travel(String name, BigDecimal price) {
		super(name, price);
	}

	public Travel(Long id, String name, BigDecimal price, Boolean hidden) {
		super(id, name, price, hidden);
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
