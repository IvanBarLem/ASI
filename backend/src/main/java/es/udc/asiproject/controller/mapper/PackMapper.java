package es.udc.asiproject.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.PackDto;
import es.udc.asiproject.persistence.model.Pack;

@Component
public class PackMapper {
	private static ModelMapper mapper;

	@Autowired
	private PackMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static PackDto convertToDto(Pack pack) {
		return mapper.map(pack, PackDto.class);
	}

	public static Pack convertToEntity(PackDto packDto) {
		return mapper.map(packDto, Pack.class);
	}
}
