package es.udc.asiproject.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Sale {
	public enum SaleState {
		NORMAL, FREEZE, PAID
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private SaleState state;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal price;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	@ManyToOne
	public User agent;

	@ManyToOne
	public User client;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Accommodation> accommodations;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Activity> activities;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Transport> transports;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Travel> travels;

	public Sale() {
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

	public Set<Accommodation> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(Set<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public Set<Transport> getTransports() {
		return transports;
	}

	public void setTransports(Set<Transport> transports) {
		this.transports = transports;
	}

	public Set<Travel> getTravels() {
		return travels;
	}

	public void setTravels(Set<Travel> travels) {
		this.travels = travels;
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

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, price, state);
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
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
				&& Objects.equals(price, other.price) && state == other.state;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", state=" + state + ", price=" + price + ", createdAt=" + createdAt + "]";
	}
}
