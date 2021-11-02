package es.udc.asiproject.controller.mapper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
		AccommodationDto accommodationDto = mapper.map(accommodation, AccommodationDto.class);

		return accommodationDto;
	}

	public static Set<AccommodationDto> convertToDto(Set<Accommodation> accommodations) {
		Type targetType = new TypeToken<Set<AccommodationDto>>() {
		}.getType();
		Set<AccommodationDto> accommodationsDto = mapper.map(accommodations, targetType);

		return accommodationsDto;
	}

	public static List<AccommodationDto> convertToDto(List<Accommodation> accommodations) {
		Type targetType = new TypeToken<List<AccommodationDto>>() {
		}.getType();
		List<AccommodationDto> accommodationsDto = mapper.map(accommodations, targetType);

		return accommodationsDto;
	}

	public static Accommodation convertToEntity(AccommodationDto accommodationDto) {
		Accommodation accommodation = mapper.map(accommodationDto, Accommodation.class);

		return accommodation;
	}

	public static Set<Accommodation> convertToEntity(Set<AccommodationDto> accommodationDtos) {
		Type targetType = new TypeToken<Set<Accommodation>>() {
		}.getType();
		Set<Accommodation> accommodations = mapper.map(accommodationDtos, targetType);

		return accommodations;
	}
}
