package es.udc.asiproject.controller.dto;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.dto.validation.UpdateValidation;
import es.udc.asiproject.persistence.model.Sale.SaleState;

public class SaleDto {

	private Long id;
	@NotNull(groups = { UpdateValidation.class })
	private SaleState state;
	@NotNull(groups = { InsertValidation.class })
	@Positive(groups = { InsertValidation.class })
	private BigDecimal price;
	@NotNull(groups = { InsertValidation.class })
	private UserDto agent;
	@NotNull(groups = { InsertValidation.class })
	private UserDto client;
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

	public UserDto getAgent() {
		return agent;
	}

	public void setAgent(UserDto agent) {
		this.agent = agent;
	}

	public UserDto getClient() {
		return client;
	}

	public void setClient(UserDto client) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accommodations == null) ? 0 : accommodations.hashCode());
		result = prime * result + ((activities == null) ? 0 : activities.hashCode());
		result = prime * result + ((agent == null) ? 0 : agent.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((transports == null) ? 0 : transports.hashCode());
		result = prime * result + ((travels == null) ? 0 : travels.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleDto other = (SaleDto) obj;
		if (accommodations == null) {
			if (other.accommodations != null)
				return false;
		} else if (!accommodations.equals(other.accommodations))
			return false;
		if (activities == null) {
			if (other.activities != null)
				return false;
		} else if (!activities.equals(other.activities))
			return false;
		if (agent == null) {
			if (other.agent != null)
				return false;
		} else if (!agent.equals(other.agent))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (state != other.state)
			return false;
		if (transports == null) {
			if (other.transports != null)
				return false;
		} else if (!transports.equals(other.transports))
			return false;
		if (travels == null) {
			if (other.travels != null)
				return false;
		} else if (!travels.equals(other.travels))
			return false;
		return true;
	}

}
