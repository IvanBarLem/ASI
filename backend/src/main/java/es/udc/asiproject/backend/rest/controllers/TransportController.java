package es.udc.asiproject.backend.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.backend.model.services.TransportService;
import es.udc.asiproject.backend.rest.dtos.TransportConversor;
import es.udc.asiproject.backend.rest.dtos.TransportDto;

@RestController
@RequestMapping("/transports")
public class TransportController {

	@Autowired
	private TransportService transportService;

	@GetMapping("/findTransports")
	public List<TransportDto> findTransports() {

		return TransportConversor.toTransportDtos(transportService.findTransports());
    
	}

}
