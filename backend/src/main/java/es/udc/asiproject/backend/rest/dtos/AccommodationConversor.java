package es.udc.asiproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.asiproject.backend.model.entities.Accommodation;

public class AccommodationConversor {

	private AccommodationConversor() {
	}

	public static AccommodationDto toAccommodationDto(Accommodation accommodation) {

		return new AccommodationDto(accommodation.getId(), accommodation.getName());
	}

	public static List<AccommodationDto> toAccommodationDtos(List<Accommodation> accommodations) {
		return accommodations.stream().map(AccommodationConversor::toAccommodationDto).collect(Collectors.toList());
	}

}
