package es.udc.asiproject.persistence.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Transport extends Product {
	public Transport(String name, BigDecimal price) {
		super(name, price);
	}

	public Transport() {
		super();
	}

	public Transport(Long id, String name, BigDecimal price, Boolean hidden) {
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

	@Override
	public String toString() {
		return "Transport [id=" + getId() + ", name()=" + getName() + ", price()=" + getPrice() + ", hidden()="
				+ getHidden() + "]";
	}
}
