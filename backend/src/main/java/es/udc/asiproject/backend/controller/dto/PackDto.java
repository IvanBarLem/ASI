package es.udc.asiproject.backend.controller.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import es.udc.asiproject.backend.controller.dto.validation.InsertValidation;

public class PackDto {
	private Long id;
	@NotBlank(groups = { InsertValidation.class })
	@Size(min = 1, max = 60, groups = { InsertValidation.class })
	private String title;
	@NotBlank(groups = { InsertValidation.class })
	@Size(min = 1, max = 60, groups = { InsertValidation.class })
	private String description;
	@NotEmpty(groups = { InsertValidation.class })
	private String image;
	@NotNull(groups = { InsertValidation.class })
	@Positive(groups = { InsertValidation.class })
	private BigDecimal price;
	@NotNull(groups = { InsertValidation.class })
	@Positive(groups = { InsertValidation.class })
	private Short duration;
	@NotBlank(groups = { InsertValidation.class })
	@Size(min = 1, max = 60, groups = { InsertValidation.class })
	private String persons;
	@NotNull(groups = { InsertValidation.class })
	private Set<AccommodationDto> accommodations;
	@NotNull(groups = { InsertValidation.class })
	private Set<ActivityDto> activities;
	@NotNull(groups = { InsertValidation.class })
	private Set<TransportDto> transports;
	@NotNull(groups = { InsertValidation.class })
	private Set<TravelDto> travels;

	public PackDto() {
	}

	public PackDto(Long id, String title, String description, String image, BigDecimal price, Short duration,
			String persons, Set<AccommodationDto> accommodations, Set<ActivityDto> activities,
			Set<TransportDto> transports, Set<TravelDto> travels) {
		this.id = id;
		this.title = title.trim();
		this.description = description.trim();
		this.image = image;
		this.price = price;
		this.duration = duration;
		this.persons = persons.trim();
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
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

	public Set<AccommodationDto> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(Set<AccommodationDto> accommodations) {
		this.accommodations = accommodations;
	}

	public Set<ActivityDto> getActivities() {
		return activities;
	}

	public void setActivities(Set<ActivityDto> activities) {
		this.activities = activities;
	}

	public Set<TransportDto> getTransports() {
		return transports;
	}

	public void setTransports(Set<TransportDto> transports) {
		this.transports = transports;
	}

	public Set<TravelDto> getTravels() {
		return travels;
	}

	public void setTravels(Set<TravelDto> travels) {
		this.travels = travels;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accommodations, activities, description, duration, id, image, persons, price, title,
				transports, travels);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PackDto other = (PackDto) obj;
		return Objects.equals(accommodations, other.accommodations) && Objects.equals(activities, other.activities)
				&& Objects.equals(description, other.description) && Objects.equals(duration, other.duration)
				&& Objects.equals(id, other.id) && Objects.equals(image, other.image)
				&& Objects.equals(persons, other.persons) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title) && Objects.equals(transports, other.transports)
				&& Objects.equals(travels, other.travels);
	}
}
