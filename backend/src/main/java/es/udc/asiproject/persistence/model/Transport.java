package es.udc.asiproject.persistence.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Transport extends Product {
	public Transport() {
	}

	public Transport(Builder builder) {
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
	public String getType() {
		return "Transport";
	}

	@Override
	public String toString() {
		return "Transport [id=" + id + ", name=" + name + ", location=" + location + ", price=" + price + ", hidden="
				+ hidden + "]";
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

		public Transport build() {
			return new Transport(this);
		}
	}
}
