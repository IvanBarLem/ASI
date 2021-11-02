package es.udc.asiproject.controller.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.udc.asiproject.controller.dto.validation.GetValidation;
import es.udc.asiproject.controller.dto.validation.InsertValidation;

public class ActivityDto {
	@NotNull(groups = { GetValidation.class })
	private Long id;
	@NotBlank(groups = { InsertValidation.class })
	@Size(min = 1, max = 60, groups = { InsertValidation.class })
	private String name;

	public ActivityDto() {
	}

	public ActivityDto(Long id, String name) {
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
		ActivityDto other = (ActivityDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
