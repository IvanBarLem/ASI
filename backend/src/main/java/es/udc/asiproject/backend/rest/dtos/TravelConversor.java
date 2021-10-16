package es.udc.asiproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.asiproject.backend.model.entities.Travel;

public class TravelConversor {

	private TravelConversor() {
	}

	public static TravelDto toTravelDto(Travel travel) {

		return new TravelDto(travel.getId(), travel.getName());

	}

	public static List<TravelDto> toTravelDtos(List<Travel> travels) {

		return travels.stream().map(TravelConversor::toTravelDto).collect(Collectors.toList());

  }

}
