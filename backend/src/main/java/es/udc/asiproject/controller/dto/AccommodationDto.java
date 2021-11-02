package es.udc.asiproject.controller.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import es.udc.asiproject.controller.dto.validation.GetValidation;

public class AccommodationDto {
	@NotNull(groups = { GetValidation.class })
	private Long id;
	private String name;

	public AccommodationDto() {
	}

	public AccommodationDto(Long id, String name) {
		this.id = id;
		this.name = name.trim();
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
		this.name = name.trim();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccommodationDto other = (AccommodationDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
