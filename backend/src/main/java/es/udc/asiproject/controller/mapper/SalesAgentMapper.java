package es.udc.asiproject.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.SalesAgentDto;
import es.udc.asiproject.persistence.model.unrelated.SalesAgent;

@Component
public class SalesAgentMapper {
	private static ModelMapper mapper;

	@Autowired
	private SalesAgentMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static SalesAgentDto convertToDto(SalesAgent salesAgent) {
		return mapper.map(salesAgent, SalesAgentDto.class);
	}

	public static SalesAgent convertToEntity(SalesAgentDto salesAgentDto) {
		return mapper.map(salesAgentDto, SalesAgent.class);
	}
}
