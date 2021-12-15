package es.udc.asiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.controller.dto.CreateSaleParamsDto;
import es.udc.asiproject.controller.dto.IdDto;
import es.udc.asiproject.controller.dto.PageDto;
import es.udc.asiproject.controller.dto.SaleDto;
import es.udc.asiproject.controller.dto.validation.InsertValidation;
import es.udc.asiproject.controller.mapper.PageMapper;
import es.udc.asiproject.controller.mapper.SaleMapper;
import es.udc.asiproject.service.SaleService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.InvalidOperationException;
import es.udc.asiproject.service.exceptions.PermissionException;

@RestController
@RequestMapping("/sales")
public class SaleController {
	@Autowired
	private SaleService saleService;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public IdDto createSale(@RequestAttribute Long userId,
			@Validated({ InsertValidation.class }) @RequestBody CreateSaleParamsDto createSaleParamsDto)
			throws InstanceNotFoundException, InvalidOperationException {
		IdDto idDto = new IdDto();

		idDto.setId(saleService.createSale(createSaleParamsDto, userId).getId());

		return idDto;
	}

	@GetMapping("/findSales")
	@ResponseStatus(HttpStatus.OK)
	public PageDto<SaleDto> findSales(@RequestAttribute Long userId, @RequestParam(defaultValue = "") String agentName,
			@RequestParam(defaultValue = "") String clientName, @RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize) throws InstanceNotFoundException {
		return PageMapper.convertToDto(saleService.findSales(userId, agentName, clientName, pageNumber, pageSize),
				SaleMapper::convertToDto);
	}

	@PostMapping("/freezeSale/{saleId}")
	@ResponseStatus(HttpStatus.OK)
	public Long freezeSale(@RequestAttribute Long userId, @PathVariable Long saleId)
			throws InstanceNotFoundException, PermissionException {
		saleService.freezeSale(userId, saleId);
		return saleId;
	}

	@PostMapping("/paySale/{saleId}")
	@ResponseStatus(HttpStatus.OK)
	public Long paySale(@RequestAttribute Long userId, @PathVariable Long saleId)
			throws InstanceNotFoundException, PermissionException {
		saleService.paySale(userId, saleId);
		return saleId;
	}
}
