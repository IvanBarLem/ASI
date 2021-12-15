package es.udc.asiproject.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.controller.dto.PageDto;
import es.udc.asiproject.controller.dto.SalesAgentDto;
import es.udc.asiproject.controller.dto.SalesCompanyDto;
import es.udc.asiproject.controller.dto.SalesProductDto;
import es.udc.asiproject.controller.mapper.PageMapper;
import es.udc.asiproject.controller.mapper.SalesAgentMapper;
import es.udc.asiproject.controller.mapper.SalesCompanyMapper;
import es.udc.asiproject.controller.mapper.SalesProductMapper;
import es.udc.asiproject.service.StatisticsService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;

	@GetMapping("/company")
	@ResponseStatus(HttpStatus.OK)
	public SalesCompanyDto findSalesCompany(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
		return SalesCompanyMapper.convertToDto(statisticsService.findSalesCompany(startDate, endDate));
	}

	@GetMapping("/agents")
	@ResponseStatus(HttpStatus.OK)
	public PageDto<SalesAgentDto> findSalesAgents(@RequestParam(defaultValue = "") String name,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int pageSize)
			throws InstanceNotFoundException {
		return PageMapper.convertToDto(statisticsService.findSalesAgents(name, startDate, endDate, page, pageSize),
				SalesAgentMapper::convertToDto);
	}

	@GetMapping("/products")
	@ResponseStatus(HttpStatus.OK)
	public PageDto<SalesProductDto> findSalesProducts(@RequestParam(defaultValue = "all") String category,
			@RequestParam(defaultValue = "") String location,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
		return PageMapper.convertToDto(
				statisticsService.findSalesProducts(category, location, startDate, endDate, page, pageSize),
				SalesProductMapper::convertToDto);
	}
}
