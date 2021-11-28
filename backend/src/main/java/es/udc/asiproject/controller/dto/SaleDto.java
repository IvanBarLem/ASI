package es.udc.asiproject.controller.dto;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.dto.validation.UpdateValidation;
import es.udc.asiproject.persistence.model.Sale.SaleState;
import es.udc.asiproject.persistence.model.User;

public class SaleDto {

	@NotNull(groups = { InsertValidation.class })
	@Size(min = 1, max = 60, groups = { InsertValidation.class })

	private Long id;
	private SaleState state;
	@NotNull(groups = { InsertValidation.class })
	@Positive(groups = { InsertValidation.class, UpdateValidation.class })
	private BigDecimal price;
	@NotNull(groups = { InsertValidation.class })
	private User agent;
	@NotNull(groups = { InsertValidation.class })
	private User client;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<AccommodationDto> accommodations;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<ActivityDto> activities;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<TransportDto> transports;
	@NotNull(groups = { InsertValidation.class, UpdateValidation.class })
	private Set<TravelDto> travels;

	public SaleDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SaleState getState() {
		return state;
	}

	public void setState(SaleState state) {
		this.state = state;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
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
