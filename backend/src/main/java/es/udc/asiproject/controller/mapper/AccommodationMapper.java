package es.udc.asiproject.controller.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.AccommodationDto;
import es.udc.asiproject.persistence.model.Accommodation;

@Component
public class AccommodationMapper {
	private static ModelMapper mapper;

	@Autowired
	private AccommodationMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static AccommodationDto convertToDto(Accommodation accommodation) {
		return mapper.map(accommodation, AccommodationDto.class);
	}

	public static Set<AccommodationDto> convertToDto(Set<Accommodation> accommodations) {
		return accommodations.stream().map(item -> convertToDto(item)).collect(Collectors.toSet());
	}

	public static List<AccommodationDto> convertToDto(List<Accommodation> accommodations) {
		return accommodations.stream().map(item -> convertToDto(item)).collect(Collectors.toList());
	}

	public static Accommodation convertToEntity(AccommodationDto accommodationDto) {
		return mapper.map(accommodationDto, Accommodation.class);
	}

	public static Set<Accommodation> convertToEntity(Set<AccommodationDto> accommodationDtos) {
		return accommodationDtos.stream().map(item -> convertToEntity(item)).collect(Collectors.toSet());
	}
}
