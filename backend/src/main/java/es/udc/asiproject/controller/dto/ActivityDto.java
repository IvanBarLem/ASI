package es.udc.asiproject.controller.dto;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.dto.validation.UpdateValidation;

public class ActivityDto {
	@NotNull(groups = { UpdateValidation.class })
	private Long id;
	@NotBlank(groups = { InsertValidation.class, UpdateValidation.class })
	@Size(min = 1, max = 60, groups = { InsertValidation.class, UpdateValidation.class })
	private String name;
	@NotBlank(groups = { InsertValidation.class, UpdateValidation.class })
	@Size(min = 1, max = 60, groups = { InsertValidation.class, UpdateValidation.class })
	private String location;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	@Positive(groups = { InsertValidation.class, UpdateValidation.class })
	private BigDecimal price;
	@NotNull(groups = { UpdateValidation.class })
	private Boolean hidden;

	public ActivityDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hidden, id, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityDto other = (ActivityDto) obj;
		return Objects.equals(hidden, other.hidden) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price);
	}

	@Override
	public String toString() {
		return "ActivityDto [id=" + id + ", name=" + name + ", location=" + location + ", price=" + price + ", hidden="
				+ hidden + "]";
	}
}
