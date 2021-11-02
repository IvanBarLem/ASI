package es.udc.asiproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.controller.dto.AccommodationDto;
import es.udc.asiproject.controller.dto.ActivityDto;
import es.udc.asiproject.controller.dto.TransportDto;
import es.udc.asiproject.controller.dto.TravelDto;
import es.udc.asiproject.controller.mapper.AccommodationMapper;
import es.udc.asiproject.controller.mapper.ActivityMapper;
import es.udc.asiproject.controller.mapper.TransportMapper;
import es.udc.asiproject.controller.mapper.TravelMapper;
import es.udc.asiproject.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/accommodations")
	@ResponseStatus(HttpStatus.OK)
	public List<AccommodationDto> findAccommodations() {
		return AccommodationMapper.convertToDto(productService.findAccommodations());
	}

	@GetMapping("/activities")
	@ResponseStatus(HttpStatus.OK)
	public List<ActivityDto> findActivities() {
		return ActivityMapper.convertToDto(productService.findActivities());
	}

	@GetMapping("/transports")
	@ResponseStatus(HttpStatus.OK)
	public List<TransportDto> findTransports() {
		return TransportMapper.convertToDto(productService.findTransports());
	}

	@GetMapping("/travels")
	@ResponseStatus(HttpStatus.OK)
	public List<TravelDto> findTravels() {
		return TravelMapper.convertToDto(productService.findTravels());
	}
}
