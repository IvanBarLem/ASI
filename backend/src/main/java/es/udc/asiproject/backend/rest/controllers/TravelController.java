package es.udc.asiproject.backend.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.backend.model.services.TravelService;
import es.udc.asiproject.backend.rest.dtos.TravelConversor;
import es.udc.asiproject.backend.rest.dtos.TravelDto;

@RestController
@RequestMapping("/travels")
public class TravelController {

	@Autowired
	private TravelService travelService;

	@GetMapping("/findTravels")
	public List<TravelDto> findTravels() {

		return TravelConversor.toTravelDtos(travelService.findTravels());
    
	}

}
