package es.udc.asiproject.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import es.udc.asiproject.persistence.model.Sale;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.PermissionException;

@Service
public interface SaleService {

	Page<Sale> findSales(Long userId, Long clientFilterId, Long agentFilterId, Integer pageNumber, Integer pageSize);

	void deleteSale(Long userId, Sale sale) throws InstanceNotFoundException, PermissionException;

	void paySale(Long userId, Long saleId) throws InstanceNotFoundException, PermissionException;

//	ByteArrayResource generateBill(Long userId, Long saleId)
//			throws InstanceNotFoundException, PermissionException, FileNotFoundException, IOException;

}
