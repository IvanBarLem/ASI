package es.udc.asiproject.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.SalesProductDto;
import es.udc.asiproject.persistence.model.unrelated.SalesProduct;

@Component
public class SalesProductMapper {
	private static ModelMapper mapper;

	@Autowired
	private SalesProductMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static SalesProductDto convertToDto(SalesProduct salesProduct) {
		return mapper.map(salesProduct, SalesProductDto.class);
	}

	public static SalesProduct convertToEntity(SalesProductDto salesProductDto) {
		return mapper.map(salesProductDto, SalesProduct.class);
	}
}
