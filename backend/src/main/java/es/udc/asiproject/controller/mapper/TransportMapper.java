package es.udc.asiproject.controller.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.TransportDto;
import es.udc.asiproject.persistence.model.Transport;

@Component
public class TransportMapper {
	private static ModelMapper mapper;

	@Autowired
	private TransportMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static TransportDto convertToDto(Transport transport) {
		return mapper.map(transport, TransportDto.class);
	}

	public static Set<TransportDto> convertToDto(Set<Transport> transports) {
		return transports.stream().map(item -> convertToDto(item)).collect(Collectors.toSet());
	}

	public static List<TransportDto> convertToDto(List<Transport> transports) {
		return transports.stream().map(item -> convertToDto(item)).collect(Collectors.toList());
	}

	public static Transport convertToEntity(TransportDto transportDto) {
		return mapper.map(transportDto, Transport.class);
	}

	public static Set<Transport> convertToEntity(Set<TransportDto> transportsDto) {
		return transportsDto.stream().map(item -> convertToEntity(item)).collect(Collectors.toSet());
	}
}
