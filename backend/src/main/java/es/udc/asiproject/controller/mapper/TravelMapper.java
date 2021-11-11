package es.udc.asiproject.controller.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.TravelDto;
import es.udc.asiproject.persistence.model.Travel;

@Component
public class TravelMapper {
	private static ModelMapper mapper;

	@Autowired
	private TravelMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static TravelDto convertToDto(Travel travel) {
		return mapper.map(travel, TravelDto.class);
	}

	public static Set<TravelDto> convertToDto(Set<Travel> travels) {
		return travels.stream().map(item -> convertToDto(item)).collect(Collectors.toSet());
	}

	public static List<TravelDto> convertToDto(List<Travel> travels) {
		return travels.stream().map(item -> convertToDto(item)).collect(Collectors.toList());
	}

	public static Travel convertToEntity(TravelDto travelDto) {
		return mapper.map(travelDto, Travel.class);
	}

	public static Set<Travel> convertToEntity(Set<TravelDto> travelsDtos) {
		return travelsDtos.stream().map(item -> convertToEntity(item)).collect(Collectors.toSet());
	}
}
