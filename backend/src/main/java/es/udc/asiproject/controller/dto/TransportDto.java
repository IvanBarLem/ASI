package es.udc.asiproject.controller.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.dto.validation.UpdateValidation;

public class TransportDto {
	@NotNull(groups = { UpdateValidation.class })
	private Long id;
	@NotBlank(groups = { InsertValidation.class, UpdateValidation.class })
	@Size(min = 1, max = 60, groups = { InsertValidation.class, UpdateValidation.class })
	private String name;
	@NotNull(groups = { UpdateValidation.class })
	private Boolean hidden;

	public TransportDto() {
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

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hidden, id, name);
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
		return Objects.equals(hidden, other.hidden) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
