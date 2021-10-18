package es.udc.asiproject.backend.controller.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import es.udc.asiproject.backend.controller.dto.validation.GetValidation;

public class TransportDto {
	@NotNull(groups = { GetValidation.class })
	private Long id;
	private String name;

	public TransportDto() {
	}

	public TransportDto(Long id, String name) {
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
		this.name = name;
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
		TransportDto other = (TransportDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
