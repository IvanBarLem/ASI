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
	private Set<AccommodationSaleDto> accommodations;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<ActivitySaleDto> activities;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<TransportSaleDto> transports;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<TravelSaleDto> travels;

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

	public Set<AccommodationSaleDto> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(Set<AccommodationSaleDto> accommodations) {
		this.accommodations = accommodations;
	}

	public Set<ActivitySaleDto> getActivities() {
		return activities;
	}

	public void setActivities(Set<ActivitySaleDto> activities) {
		this.activities = activities;
	}

	public Set<TransportSaleDto> getTransports() {
		return transports;
	}

	public void setTransports(Set<TransportSaleDto> transports) {
		this.transports = transports;
	}

	public Set<TravelSaleDto> getTravels() {
		return travels;
	}

	public void setTravels(Set<TravelSaleDto> travels) {
		this.travels = travels;
	}

}
