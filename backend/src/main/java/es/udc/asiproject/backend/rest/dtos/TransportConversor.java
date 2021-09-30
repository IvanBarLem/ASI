package es.udc.asiproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.asiproject.backend.model.entities.Transport;

public class TransportConversor {
    private TransportConversor() {
    }

    public static IdDto toIdDto(Long id) {
	return new IdDto(id);
    }

    public static TransportDto toTransportDto(Transport transport) {
	return new TransportDto(transport.getId(), transport.getType().getId(), transport.getDate().toString(),
		transport.getOrigin(), transport.getDestination());
    }

    public static List<TransportDto> toTransportDtos(List<Transport> transports) {
	return transports.stream().map(TransportConversor::toTransportDto).collect(Collectors.toList());
    }
}
