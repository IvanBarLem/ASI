package es.udc.asiproject.persistence.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.udc.asiproject.utils.Sets;

@Entity
public class Pack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 60)
	private String title;
	@Column(nullable = false, length = 500)
	private String description;
	@Lob
	@Column(nullable = false)
	private Byte[] image;
	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal price;
	@Column(nullable = false)
	private Integer duration;
	@Column(nullable = false, length = 60)
	private String persons;
	@Column(nullable = false)
	private Boolean outstanding;
	@Column(nullable = false)
	private Boolean hidden;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdAt;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet<Product>();

	public Pack() {
	}

	public Pack(Builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.description = builder.description;
		this.image = builder.image;
		this.price = builder.price;
		this.duration = builder.duration;
		this.persons = builder.persons;
		this.outstanding = builder.outstanding;
		this.hidden = builder.hidden;
		this.createdAt = builder.createdAt;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

	public Boolean getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(Boolean outstanding) {
		this.outstanding = outstanding;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		for (Product product : Sets.difference(this.products, products)) {
			this.products.remove(product);
			product.getPacks().remove(this);
		}
		for (Product product : Sets.difference(products, this.products)) {
			this.products.add(product);
			product.getPacks().add(this);
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
		Pack other = (Pack) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Pack [id=" + id + ", title=" + title + ", description=" + description + ", image="
				+ Arrays.toString(image) + ", price=" + price + ", duration=" + duration + ", persons=" + persons
				+ ", outstanding=" + outstanding + ", hidden=" + hidden + ", createdAt=" + createdAt + ", products="
				+ products + "]";
	}

	public static class Builder {
		private Long id;
		private String title;
		private String description;
		private Byte[] image;
		private BigDecimal price;
		private Integer duration;
		private String persons;
		private Boolean outstanding;
		private Boolean hidden;
		private Date createdAt;

		public Builder() {
		}

		public Builder id(Long id) {
			this.id = id;
			return Builder.this;
		}

		public Builder title(String title) {
			this.title = title;
			return Builder.this;
		}

		public Builder description(String description) {
			this.description = description;
			return Builder.this;
		}

		public Builder image(Byte[] image) {
			this.image = image;
			return Builder.this;
		}

		public Builder price(BigDecimal price) {
			this.price = price;
			return Builder.this;
		}

		public Builder duration(Integer duration) {
			this.duration = duration;
			return Builder.this;
		}

		public Builder persons(String persons) {
			this.persons = persons;
			return Builder.this;
		}

		public Builder outstanding(Boolean outstanding) {
			this.outstanding = outstanding;
			return Builder.this;
		}

		public Builder hidden(Boolean hidden) {
			this.hidden = hidden;
			return Builder.this;
		}

		public Builder createdAt(Date createdAt) {
			this.createdAt = createdAt;
			return Builder.this;
		}

		public Pack build() {
			return new Pack(this);
		}
	}
}
