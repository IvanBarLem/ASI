package es.udc.asiproject.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.backend.controller.dto.TravelDto;
import es.udc.asiproject.backend.controller.mapper.TravelMapper;
import es.udc.asiproject.backend.service.TravelService;

@RestController
@RequestMapping("/travels")
public class TravelController {
	@Autowired
	private TravelService travelService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<TravelDto> findTravels() {
		return TravelMapper.convertToDto(travelService.findTravels());
	}
}
