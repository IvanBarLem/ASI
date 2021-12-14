package es.udc.asiproject.persistence.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Accommodation extends Product {
	public Accommodation() {
	}

	public Accommodation(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.location = builder.location;
		this.price = builder.price;
		this.hidden = builder.hidden;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Override
	public String toString() {
		return "Accommodation [id=" + id + ", name=" + name + ", location=" + location + ", price=" + price
				+ ", hidden=" + hidden + "]";
	}

	public static class Builder {
		private Long id;
		private String name;
		private String location;
		private BigDecimal price;
		private Boolean hidden;

		public Builder() {
		}

		public Builder id(Long id) {
			this.id = id;
			return Builder.this;
		}

		public Builder name(String name) {
			this.name = name;
			return Builder.this;
		}

		public Builder location(String location) {
			this.location = location;
			return Builder.this;
		}

		public Builder price(BigDecimal price) {
			this.price = price;
			return Builder.this;
		}

		public Builder hidden(Boolean hidden) {
			this.hidden = hidden;
			return Builder.this;
		}

		public Accommodation build() {
			return new Accommodation(this);
		}
	}

	@Override
	public String getType() {

		return "Accommodation";
	}
}
