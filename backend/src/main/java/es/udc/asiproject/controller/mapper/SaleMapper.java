package es.udc.asiproject.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.asiproject.controller.dto.SaleDto;
import es.udc.asiproject.persistence.model.Sale;

public class SaleMapper {
	private static ModelMapper mapper;

	@Autowired
	private SaleMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static SaleDto convertToDto(Sale sale) {
		SaleDto saleDto = mapper.map(sale, SaleDto.class);

		return saleDto;
	}

	public static Sale convertToEntity(SaleDto saleDto) {
		Sale sale = mapper.map(saleDto, Sale.class);

		return sale;
	}

}
