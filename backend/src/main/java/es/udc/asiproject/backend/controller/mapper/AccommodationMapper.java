package es.udc.asiproject.backend.controller.mapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.backend.controller.dto.AccommodationDto;
import es.udc.asiproject.backend.persistence.model.Accommodation;

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
}
