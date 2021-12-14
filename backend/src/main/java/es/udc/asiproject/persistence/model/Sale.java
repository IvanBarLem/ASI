package es.udc.asiproject.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.udc.asiproject.persistence.model.enums.SaleState;
import es.udc.asiproject.utils.Sets;

@Entity
public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private SaleState state;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal price;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdAt;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "agent_id", nullable = false)
	public User agent;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	public User client;

	@OneToMany(mappedBy = "sale", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SaleProduct> products = new HashSet<SaleProduct>();

	public Sale() {
	}

	public Sale(Builder builder) {
		this.id = builder.id;
		this.state = builder.state;
		this.price = builder.price;
		this.createdAt = builder.createdAt;
		this.agent = builder.agent;
		this.client = builder.client;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SaleState getState() {
		return state;
	}

	public void setState(SaleState state) {
		this.state = state;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Set<SaleProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<SaleProduct> products) {
		for (SaleProduct saleProduct : Sets.difference(this.products, products)) {
			this.products.remove(saleProduct);
			saleProduct.getProduct().getSales().remove(saleProduct);
		}
		for (SaleProduct saleProduct : Sets.difference(products, this.products)) {
			this.products.add(saleProduct);
			saleProduct.getProduct().getSales().add(saleProduct);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", state=" + state + ", price=" + price + ", createdAt=" + createdAt + ", agent="
				+ agent + ", client=" + client + ", products=" + products + "]";
	}

	public static class Builder {
		private Long id;
		private SaleState state;
		private BigDecimal price;
		private Date createdAt;
		private User agent;
		private User client;

		public Builder() {
		}

		public Builder id(Long id) {
			this.id = id;
			return Builder.this;
		}

		public Builder state(SaleState state) {
			this.state = state;
			return Builder.this;
		}

		public Builder price(BigDecimal price) {
			this.price = price;
			return Builder.this;
		}

		public Builder agent(User agent) {
			this.agent = agent;
			return Builder.this;
		}

		public Builder client(User client) {
			this.client = client;
			return Builder.this;
		}

		public Builder createdAt(Date createdAt) {
			this.createdAt = createdAt;
			return Builder.this;
		}

		public Sale build() {
			return new Sale(this);
		}
	}

}
