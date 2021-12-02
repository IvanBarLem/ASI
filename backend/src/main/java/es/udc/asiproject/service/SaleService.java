package es.udc.asiproject.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.udc.asiproject.controller.dto.CreateSaleParamsDto;
import es.udc.asiproject.persistence.model.Sale;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.InvalidOperationException;
import es.udc.asiproject.service.exceptions.PermissionException;

@Service
public interface SaleService {
	Sale createSale(CreateSaleParamsDto createSaleParamsDto, Long userId)
			throws InstanceNotFoundException, InvalidOperationException;

	Sale updateSale(Sale sale) throws InstanceNotFoundException, InvalidOperationException;

	Page<Sale> findSales(Long userId, Long clientFilterId, Long agentFilterId, Integer pageNumber, Integer pageSize);

	void deleteSale(Long userId, Sale sale) throws InstanceNotFoundException, PermissionException;

	void freezeSale(Long userId, Long saleId) throws InstanceNotFoundException, PermissionException;

	void paySale(Long userId, Long saleId) throws InstanceNotFoundException, PermissionException;

//	ByteArrayResource generateBill(Long userId, Long saleId)
//			throws InstanceNotFoundException, PermissionException, FileNotFoundException, IOException;

}
