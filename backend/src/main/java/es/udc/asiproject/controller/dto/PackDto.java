package es.udc.asiproject.controller.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.dto.validation.UpdateValidation;

public class PackDto {
	private Long id;
	@NotBlank(groups = { InsertValidation.class, UpdateValidation.class })
	@Size(min = 1, max = 60, groups = { InsertValidation.class, UpdateValidation.class })
	private String title;
	@NotBlank(groups = { InsertValidation.class, UpdateValidation.class })
	@Size(min = 1, max = 500, groups = { InsertValidation.class, UpdateValidation.class })
	private String description;
	@NotEmpty(groups = { InsertValidation.class, UpdateValidation.class })
	private String image;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	@Positive(groups = { InsertValidation.class, UpdateValidation.class })
	private BigDecimal price;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	@Positive(groups = { InsertValidation.class, UpdateValidation.class })
	private Short duration;
	@NotBlank(groups = { InsertValidation.class, UpdateValidation.class })
	@Size(min = 1, max = 60, groups = { InsertValidation.class, UpdateValidation.class })
	private String persons;
	@NotNull(groups = { UpdateValidation.class })
	private Boolean outstanding;
	@NotNull(groups = { UpdateValidation.class })
	private Boolean hidden;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<AccommodationDto> accommodations;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<ActivityDto> activities;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<TransportDto> transports;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<TravelDto> travels;

	public PackDto() {
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
		return Objects.hash(accommodations, activities, description, duration, hidden, id, image, outstanding, persons,
				price, title, transports, travels);
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
				&& Objects.equals(hidden, other.hidden) && Objects.equals(id, other.id)
				&& Objects.equals(image, other.image) && Objects.equals(outstanding, other.outstanding)
				&& Objects.equals(persons, other.persons) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title) && Objects.equals(transports, other.transports)
				&& Objects.equals(travels, other.travels);
	}

	@Override
	public String toString() {
		return "PackDto [id=" + id + ", title=" + title + ", description=" + description + ", image=" + image
				+ ", price=" + price + ", duration=" + duration + ", persons=" + persons + ", outstanding="
				+ outstanding + ", hidden=" + hidden + ", accommodations=" + accommodations + ", activities="
				+ activities + ", transports=" + transports + ", travels=" + travels + "]";
	}
}
