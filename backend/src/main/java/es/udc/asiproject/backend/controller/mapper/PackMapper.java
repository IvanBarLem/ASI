package es.udc.asiproject.backend.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.backend.controller.dto.PackDto;
import es.udc.asiproject.backend.persistence.model.Pack;

@Component
public class PackMapper {
	private static ModelMapper mapper;

	@Autowired
	private PackMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static PackDto convertToDto(Pack pack) {
		PackDto packDto = mapper.map(pack, PackDto.class);

		return packDto;
	}

	public static Pack convertToEntity(PackDto packDto) {
		Pack pack = mapper.map(packDto, Pack.class);

		return pack;
	}
}
