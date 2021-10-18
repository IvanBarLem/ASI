package es.udc.asiproject.backend.controller.mapper;

import org.modelmapper.ModelMapper;
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

	public static Travel convertToEntity(TravelDto travelDto) {
		Travel travel = mapper.map(travelDto, Travel.class);

		return travel;
	}
}
