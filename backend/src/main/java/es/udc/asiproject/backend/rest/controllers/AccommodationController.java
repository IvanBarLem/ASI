package es.udc.asiproject.backend.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.backend.model.services.AccommodationService;
import es.udc.asiproject.backend.rest.dtos.AccommodationConversor;
import es.udc.asiproject.backend.rest.dtos.AccommodationDto;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {

	@Autowired
	AccommodationService accommodationService;

	@GetMapping("/findAccommodations")
	public List<AccommodationDto> findAccommodations() {

		return AccommodationConversor.toAccommodationDtos(accommodationService.findAccommodations());
	}

}
