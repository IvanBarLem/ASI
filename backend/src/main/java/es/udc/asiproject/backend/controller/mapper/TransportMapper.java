package es.udc.asiproject.backend.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.backend.controller.dto.TransportDto;
import es.udc.asiproject.backend.persistence.model.Transport;

@Component
public class TransportMapper {
	private static ModelMapper mapper;

	@Autowired
	private TransportMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static TransportDto convertToDto(Transport transport) {
		TransportDto transportDto = mapper.map(transport, TransportDto.class);

		return transportDto;
	}

	public static Transport convertToEntity(TransportDto transportDto) {
		Transport transport = mapper.map(transportDto, Transport.class);

		return transport;
	}
}
