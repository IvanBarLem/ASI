package es.udc.asiproject.service;

import java.util.Date;

import org.springframework.data.domain.Page;

import es.udc.asiproject.persistence.model.unrelated.SalesAgent;
import es.udc.asiproject.persistence.model.unrelated.SalesCompany;
import es.udc.asiproject.persistence.model.unrelated.SalesProduct;

public interface StatisticsService {
	SalesCompany findSalesCompany(Date startDate, Date endDate);

	Page<SalesAgent> findSalesAgents(String name, Date startDate, Date endDate, Integer pageNumber, Integer pageSize);

	Page<SalesProduct> findSalesProducts(String category, String location, Date startDate, Date endDate,
			Integer pageNumber, Integer pageSize);
}
