package es.udc.asiproject.backend.persistence.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
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

@Entity
public class Pack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 60)
	private String title;
	@Column(nullable = false, length = 60)
	private String description;
	@Lob
	@Column(nullable = false)
	private Byte[] image;
	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal price;
	@Column(nullable = false)
	private Short duration;
	@Column(nullable = false, length = 60)
	private String persons;
	@Column(nullable = false, updatable = false)
	private Date createdAt;
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Accommodation> accommodations;
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Activity> activities;
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Transport> transports;
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Travel> travels;

	public Pack() {
	}

	public Pack(String title, String description, Byte[] image, BigDecimal price, Short duration, String persons,
			Date createdAt, Set<Accommodation> accommodations, Set<Activity> activities, Set<Transport> transports,
			Set<Travel> travels) {
		this.title = title;
		this.description = description;
		this.image = image;
		this.price = price;
		this.duration = duration;
		this.persons = persons;
		this.createdAt = createdAt;
		this.accommodations = accommodations;
		this.activities = activities;
		this.transports = transports;
		this.travels = travels;
	}

	public Pack(Long id, String title, String description, Byte[] image, BigDecimal price, Short duration,
			String persons, Date createdAt, Set<Accommodation> accommodations, Set<Activity> activities,
			Set<Transport> transports, Set<Travel> travels) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.image = image;
		this.price = price;
		this.duration = duration;
		this.persons = persons;
		this.createdAt = createdAt;
		this.accommodations = accommodations;
		this.activities = activities;
		this.transports = transports;
		this.travels = travels;
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

	public Short getDuration() {
		return duration;
	}

	public void setDuration(Short duration) {
		this.duration = duration;
	}

	public String getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Objects.hash(accommodations, activities, createdAt, description, duration, id,
				persons, price, title, transports, travels);
		return result;
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
		return Objects.equals(accommodations, other.accommodations) && Objects.equals(activities, other.activities)
				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(description, other.description)
				&& Objects.equals(duration, other.duration) && Objects.equals(id, other.id)
				&& Arrays.equals(image, other.image) && Objects.equals(persons, other.persons)
				&& Objects.equals(price, other.price) && Objects.equals(title, other.title)
				&& Objects.equals(transports, other.transports) && Objects.equals(travels, other.travels);
	}
}
