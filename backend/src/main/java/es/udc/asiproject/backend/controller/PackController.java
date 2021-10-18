package es.udc.asiproject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.backend.controller.dto.PackDto;
import es.udc.asiproject.backend.controller.dto.PageDto;
import es.udc.asiproject.backend.controller.dto.validation.GetValidation;
import es.udc.asiproject.backend.controller.dto.validation.InsertValidation;
import es.udc.asiproject.backend.controller.mapper.PackMapper;
import es.udc.asiproject.backend.controller.mapper.PageMapper;
import es.udc.asiproject.backend.persistence.model.Pack;
import es.udc.asiproject.backend.service.PackService;
import es.udc.asiproject.backend.service.exceptions.InstanceNotFoundException;

@RestController
@RequestMapping("/packs")
public class PackController {
	@Autowired
	private PackService packService;

	@PostMapping(path = "/create")
	@ResponseStatus(HttpStatus.CREATED)
	public PackDto createPack(@Validated({ InsertValidation.class }) @RequestBody PackDto packDto)
			throws InstanceNotFoundException {
		Pack pack = PackMapper.convertToEntity(packDto);

		return PackMapper.convertToDto(packService.createPack(pack));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	PageDto<PackDto> getPacks(@Validated({ GetValidation.class }) @RequestBody PageDto<PackDto> pageDto) {
		return PageMapper.convertToDto(packService.findPacks(pageDto.getPageNumber(), pageDto.getPageSize()),
				PackMapper::convertToDto);
	}
}