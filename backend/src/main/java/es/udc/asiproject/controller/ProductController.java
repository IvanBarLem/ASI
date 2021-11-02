package es.udc.asiproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.controller.dto.AccommodationDto;
import es.udc.asiproject.controller.dto.ActivityDto;
import es.udc.asiproject.controller.dto.TransportDto;
import es.udc.asiproject.controller.dto.TravelDto;
import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.mapper.AccommodationMapper;
import es.udc.asiproject.controller.mapper.ActivityMapper;
import es.udc.asiproject.controller.mapper.TransportMapper;
import es.udc.asiproject.controller.mapper.TravelMapper;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping(path = "/accommodations/create")
	@ResponseStatus(HttpStatus.CREATED)
	public AccommodationDto createAccommodation(
			@Validated({ InsertValidation.class }) @RequestBody AccommodationDto accommodationDto) {
		Accommodation accommodation = AccommodationMapper.convertToEntity(accommodationDto);

		return AccommodationMapper.convertToDto(productService.createAccommodation(accommodation));
	}

	@GetMapping("/accommodations")
	@ResponseStatus(HttpStatus.OK)
	public List<AccommodationDto> findAccommodations() {
		return AccommodationMapper.convertToDto(productService.findAccommodations());
	}

	@PostMapping(path = "/activities/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ActivityDto createActivity(@Validated({ InsertValidation.class }) @RequestBody ActivityDto activityDto) {
		Activity activity = ActivityMapper.convertToEntity(activityDto);

		return ActivityMapper.convertToDto(productService.createActivity(activity));
	}

	@GetMapping("/activities")
	@ResponseStatus(HttpStatus.OK)
	public List<ActivityDto> findActivities() {
		return ActivityMapper.convertToDto(productService.findActivities());
	}

	@PostMapping(path = "/transports/create")
	@ResponseStatus(HttpStatus.CREATED)
	public TransportDto createTransport(@Validated({ InsertValidation.class }) @RequestBody TransportDto transportDto) {
		Transport transport = TransportMapper.convertToEntity(transportDto);

		return TransportMapper.convertToDto(productService.createTransport(transport));
	}

	@GetMapping("/transports")
	@ResponseStatus(HttpStatus.OK)
	public List<TransportDto> findTransports() {
		return TransportMapper.convertToDto(productService.findTransports());
	}

	@PostMapping(path = "/travels/create")
	@ResponseStatus(HttpStatus.CREATED)
	public TravelDto createTravel(@Validated({ InsertValidation.class }) @RequestBody TravelDto travelDto) {
		Travel travel = TravelMapper.convertToEntity(travelDto);

		return TravelMapper.convertToDto(productService.createTravel(travel));
	}

	@GetMapping("/travels")
	@ResponseStatus(HttpStatus.OK)
	public List<TravelDto> findTravels() {
		return TravelMapper.convertToDto(productService.findTravels());
	}
}
