package es.udc.asiproject.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.SalesCompanyDto;
import es.udc.asiproject.persistence.model.unrelated.SalesCompany;

@Component
public class SalesCompanyMapper {
	private static ModelMapper mapper;

	@Autowired
	private SalesCompanyMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static SalesCompanyDto convertToDto(SalesCompany salesCompany) {
		return mapper.map(salesCompany, SalesCompanyDto.class);
	}

	public static SalesCompany convertToEntity(SalesCompanyDto salesCompanyDto) {
		return mapper.map(salesCompanyDto, SalesCompany.class);
	}
}
