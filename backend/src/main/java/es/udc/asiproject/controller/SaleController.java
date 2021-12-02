package es.udc.asiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import es.udc.asiproject.controller.dto.validation.UpdateValidation;
import es.udc.asiproject.controller.mapper.PageMapper;
import es.udc.asiproject.controller.mapper.SaleMapper;
import es.udc.asiproject.persistence.model.Sale;
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

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public SaleDto updateSale(@Validated({ UpdateValidation.class }) @RequestBody SaleDto saleDto)
			throws InstanceNotFoundException, InvalidOperationException {
		Sale sale = SaleMapper.convertToEntity(saleDto);

		return SaleMapper.convertToDto(saleService.updateSale(sale));
	}

	@GetMapping("/findSales")
	@ResponseStatus(HttpStatus.OK)
	public PageDto<SaleDto> findSales(@RequestAttribute Long userId,
			@RequestParam(required = false) Long clientFilterId, @RequestParam(required = false) Long agentFilterId,
			@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {

		return PageMapper.convertToDto(
				saleService.findSales(userId, clientFilterId, agentFilterId, pageNumber, pageSize),
				SaleMapper::convertToDto);

	}

	@PutMapping("/paySale/{saleId}")
	@ResponseStatus(HttpStatus.OK)
	public void paySale(@RequestAttribute Long userId, @PathVariable Long saleId)
			throws InstanceNotFoundException, PermissionException {

		saleService.paySale(userId, saleId);
	}

	/*
	 * @GetMapping("/generateBill/{saleId}")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public void generateBill(@RequestAttribute
	 * Long userId, @PathVariable Long saleId) throws InstanceNotFoundException,
	 * PermissionException, FileNotFoundException, IOException {
	 * 
	 * ByteArrayResource resource = saleService.generateBill(userId, saleId); }
	 */

	@PostMapping("/freezeSale/{saleId}")
	@ResponseStatus(HttpStatus.OK)
	public void freezeSale(@RequestAttribute Long userId, @PathVariable Long saleId)
			throws InstanceNotFoundException, PermissionException {

		saleService.freezeSale(userId, saleId);
	}

}
