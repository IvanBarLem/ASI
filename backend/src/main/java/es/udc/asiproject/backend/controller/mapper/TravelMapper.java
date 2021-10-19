package es.udc.asiproject.backend.controller.mapper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.backend.controller.dto.TravelDto;
import es.udc.asiproject.backend.persistence.model.Travel;

@Component
public class TravelMapper {
	private static ModelMapper mapper;

	@Autowired
	private TravelMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static TravelDto convertToDto(Travel travel) {
		TravelDto travelDto = mapper.map(travel, TravelDto.class);

		return travelDto;
	}

	public static Set<TravelDto> convertToDto(Set<Travel> travels) {
		Type targetType = new TypeToken<Set<TravelDto>>() {
		}.getType();
		Set<TravelDto> travelsDto = mapper.map(travels, targetType);

		return travelsDto;
	}

	public static List<TravelDto> convertToDto(List<Travel> travels) {
		Type targetType = new TypeToken<List<TravelDto>>() {
		}.getType();
		List<TravelDto> travelsDto = mapper.map(travels, targetType);

		return travelsDto;
	}

	public static Travel convertToEntity(TravelDto travelDto) {
		Travel travel = mapper.map(travelDto, Travel.class);

		return travel;
	}

	public static Set<Travel> convertToEntity(Set<TravelDto> travelsDtos) {
		Type targetType = new TypeToken<Set<Travel>>() {
		}.getType();
		Set<Travel> travels = mapper.map(travelsDtos, targetType);

		return travels;
	}
}
