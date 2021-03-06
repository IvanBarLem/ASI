package es.udc.asiproject.controller.dto;

import javax.validation.constraints.NotNull;

import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.dto.validation.SaleValidation;

public class AccommodationSaleDto {
	@NotNull(groups = { InsertValidation.class })
	private Long id;
	@NotNull(groups = { InsertValidation.class, SaleValidation.class })
	private Integer quantity;

	public AccommodationSaleDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
