package es.udc.asiproject.controller.dto;

import javax.validation.constraints.NotNull;

import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.dto.validation.SaleValidation;
import es.udc.asiproject.controller.dto.validation.UpdateValidation;

public class TravelSaleDto {
	@NotNull(groups = { UpdateValidation.class })
	private Long id;
	@NotNull(groups = { InsertValidation.class, SaleValidation.class })
	private Integer quantity;

	public TravelSaleDto() {
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
