package es.udc.asiproject.controller.dto;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.dto.validation.UpdateValidation;
import es.udc.asiproject.persistence.model.enums.SaleState;

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
	private Set<ProductDto> products;

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

	public Set<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductDto> products) {
		this.products = products;
	}

}
