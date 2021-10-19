package es.udc.asiproject.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.backend.controller.dto.AccommodationDto;
import es.udc.asiproject.backend.controller.mapper.AccommodationMapper;
import es.udc.asiproject.backend.service.AccommodationService;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {
	@Autowired
	AccommodationService accommodationService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AccommodationDto> findAccommodations() {
		return AccommodationMapper.convertToDto(accommodationService.findAccommodations());
	}
}
