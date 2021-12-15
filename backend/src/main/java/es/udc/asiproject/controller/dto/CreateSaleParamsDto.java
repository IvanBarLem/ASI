package es.udc.asiproject.controller.dto;

import java.math.BigDecimal;
import java.util.HashSet;
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

	@SuppressWarnings("serial")
	public void setAccommodation(Long id, Integer quantity) {
		AccommodationSaleDto accommodationSaleDto = new AccommodationSaleDto();
		accommodationSaleDto.setId(id);
		accommodationSaleDto.setQuantity(quantity);

		this.accommodations = new HashSet<AccommodationSaleDto>() {
			{
				add(accommodationSaleDto);
			}
		};
	}

	public Set<ActivitySaleDto> getActivities() {
		return activities;
	}

	public void setActivities(Set<ActivitySaleDto> activities) {
		this.activities = activities;
	}

	@SuppressWarnings("serial")
	public void setActivity(Long id, Integer quantity) {
		ActivitySaleDto activitiesSaleDto = new ActivitySaleDto();
		activitiesSaleDto.setId(id);
		activitiesSaleDto.setQuantity(quantity);

		this.activities = new HashSet<ActivitySaleDto>() {
			{
				add(activitiesSaleDto);
			}
		};
	}

	public Set<TransportSaleDto> getTransports() {
		return transports;
	}

	public void setTransports(Set<TransportSaleDto> transports) {
		this.transports = transports;
	}

	@SuppressWarnings("serial")
	public void setTransport(Long id, Integer quantity) {
		TransportSaleDto transportSaleDto = new TransportSaleDto();
		transportSaleDto.setId(id);
		transportSaleDto.setQuantity(quantity);

		this.transports = new HashSet<TransportSaleDto>() {
			{
				add(transportSaleDto);
			}
		};
	}

	public Set<TravelSaleDto> getTravels() {
		return travels;
	}

	public void setTravels(Set<TravelSaleDto> travels) {
		this.travels = travels;
	}

	@SuppressWarnings("serial")
	public void setTravel(Long id, Integer quantity) {
		TravelSaleDto travelSaleDto = new TravelSaleDto();
		travelSaleDto.setId(id);
		travelSaleDto.setQuantity(quantity);

		this.travels = new HashSet<TravelSaleDto>() {
			{
				add(travelSaleDto);
			}
		};
	}
}
