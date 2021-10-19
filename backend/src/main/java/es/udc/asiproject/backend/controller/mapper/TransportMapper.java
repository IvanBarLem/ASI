package es.udc.asiproject.backend.controller.mapper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

	public static Set<TransportDto> convertToDto(Set<Transport> transports) {
		Type targetType = new TypeToken<Set<TransportDto>>() {
		}.getType();
		Set<TransportDto> transportsDto = mapper.map(transports, targetType);

		return transportsDto;
	}

	public static List<TransportDto> convertToDto(List<Transport> transports) {
		Type targetType = new TypeToken<List<TransportDto>>() {
		}.getType();
		List<TransportDto> transportsDto = mapper.map(transports, targetType);

		return transportsDto;
	}

	public static Transport convertToEntity(TransportDto transportDto) {
		Transport transport = mapper.map(transportDto, Transport.class);

		return transport;
	}

	public static Set<Transport> convertToEntity(Set<TransportDto> transportsDto) {
		Type targetType = new TypeToken<Set<Transport>>() {
		}.getType();
		Set<Transport> transports = mapper.map(transportsDto, targetType);

		return transports;
	}
}
