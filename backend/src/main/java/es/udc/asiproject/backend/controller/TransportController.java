package es.udc.asiproject.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.backend.controller.dto.TransportDto;
import es.udc.asiproject.backend.controller.mapper.TransportMapper;
import es.udc.asiproject.backend.service.TransportService;

@RestController
@RequestMapping("/transports")
public class TransportController {
	@Autowired
	private TransportService transportService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<TransportDto> findTransports() {
		return TransportMapper.convertToDto(transportService.findTransports());
	}
}
