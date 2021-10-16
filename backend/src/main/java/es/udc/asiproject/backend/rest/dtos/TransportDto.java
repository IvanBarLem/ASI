package es.udc.asiproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TransportDto {

	public interface AllValidations {
	}

	public interface UpdateValidations {
	}

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

	@NotNull(groups = { AllValidations.class, UpdateValidations.class })
	@Size(min = 1, max = 60, groups = { AllValidations.class, UpdateValidations.class })
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
