package es.udc.asiproject.controller.dto;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.dto.validation.UpdateValidation;

public class CreateSaleParamsDto {

	@NotNull(groups = { UpdateValidation.class })
	@Positive(groups = { InsertValidation.class })
	private BigDecimal price;
	@NotNull(groups = { InsertValidation.class })
	private Long clientId;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<AccommodationDto> accommodations;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<ActivityDto> activities;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<TransportDto> transports;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<TravelDto> travels;

	public CreateSaleParamsDto() {
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

}
