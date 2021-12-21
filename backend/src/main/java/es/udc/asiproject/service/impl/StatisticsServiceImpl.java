package es.udc.asiproject.service.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.persistence.dao.AccommodationDao;
import es.udc.asiproject.persistence.dao.ActivityDao;
import es.udc.asiproject.persistence.dao.ProductDao;
import es.udc.asiproject.persistence.dao.SaleDao;
import es.udc.asiproject.persistence.dao.TransportDao;
import es.udc.asiproject.persistence.dao.TravelDao;
import es.udc.asiproject.persistence.dao.UserDao;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Product;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.persistence.model.unrelated.SalesAgent;
import es.udc.asiproject.persistence.model.unrelated.SalesCompany;
import es.udc.asiproject.persistence.model.unrelated.SalesProduct;
import es.udc.asiproject.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccommodationDao accommodationDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private TransportDao transportDao;
    @Autowired
    private TravelDao travelDao;
    @Autowired
    private ProductDao productDao;

    // RF5
    @Override
    @Transactional(readOnly = true)
    public SalesCompany findSalesCompany(Date startDate, Date endDate) {
	Date previousDate = previousDate(startDate, endDate);
	Long currentSales = saleDao.countSalesByCreatedAt(startDate, endDate);
	BigDecimal currentBilling = saleDao.findBillingByCreatedAt(startDate, endDate);
	Long previousSales = saleDao.countSalesByCreatedAt(previousDate, startDate);
	BigDecimal previousBilling = saleDao.findBillingByCreatedAt(previousDate, startDate);
	Long numberOfProductsSold = saleDao.countProductsByCreatedAt(startDate, endDate);

	return new SalesCompany(currentSales, currentBilling, previousSales, previousBilling, numberOfProductsSold);
    }

    // RF5
    @Override
    @Transactional(readOnly = true)
    public Page<SalesAgent> findSalesAgents(String name, Date startDate, Date endDate, Integer pageNumber,
	    Integer pageSize) {
	Date previousDate = previousDate(startDate, endDate);

	Pageable pageable = PageRequest.of(pageNumber, pageSize);
	Page<User> page = userDao.findAgentsByName(name, pageable);

	List<SalesAgent> sales = new ArrayList<SalesAgent>();
	for (User agent : page.getContent()) {
	    Long currentSales = saleDao.countSalesByAgentAndCreatedAt(agent.getId(), startDate, endDate);
	    BigDecimal currentBilling = saleDao.findBillingByAgentAndCreatedAt(agent.getId(), startDate, endDate);
	    Long previousSales = saleDao.countSalesByAgentAndCreatedAt(agent.getId(), previousDate, startDate);
	    BigDecimal previousBilling = saleDao.findBillingByAgentAndCreatedAt(agent.getId(), previousDate, startDate);

	    sales.add(new SalesAgent(agent, currentSales, currentBilling, previousSales, previousBilling));
	}

	return new PageImpl<SalesAgent>(sales, pageable, page.getTotalElements());
    }

    // RF5.1
    @Override
    public Page<SalesProduct> findSalesProducts(String category, String location, Date startDate, Date endDate,
	    Integer pageNumber, Integer pageSize) {
	Date previousDate = previousDate(startDate, endDate);

	Pageable pageable = PageRequest.of(pageNumber, pageSize);

	List<SalesProduct> sales = new ArrayList<SalesProduct>();
	switch (category) {
	case "accommodation":
	    Page<Accommodation> pageAccommodations = accommodationDao.findByLocation(location, pageable);

	    for (Accommodation product : pageAccommodations.getContent()) {
		Long currentSales = saleDao.countSalesByProductAndCreatedAt(product.getId(), startDate, endDate);
		BigDecimal currentBilling = saleDao.findBillingByProductAndCreatedAt(product.getId(), startDate,
			endDate);
		Long previousSales = saleDao.countSalesByProductAndCreatedAt(product.getId(), previousDate, startDate);
		BigDecimal previousBilling = saleDao.findBillingByProductAndCreatedAt(product.getId(), previousDate,
			startDate);

		sales.add(new SalesProduct(product, product.getPrice(), category(product), currentSales, currentBilling,
			previousSales, previousBilling));
	    }

	    return new PageImpl<SalesProduct>(sales, pageable, pageAccommodations.getTotalElements());
	case "activity":
	    Page<Activity> pageActivities = activityDao.findByLocation(location, pageable);

	    for (Activity product : pageActivities.getContent()) {
		Long currentSales = saleDao.countSalesByProductAndCreatedAt(product.getId(), startDate, endDate);
		BigDecimal currentBilling = saleDao.findBillingByProductAndCreatedAt(product.getId(), startDate,
			endDate);
		Long previousSales = saleDao.countSalesByProductAndCreatedAt(product.getId(), previousDate, startDate);
		BigDecimal previousBilling = saleDao.findBillingByProductAndCreatedAt(product.getId(), previousDate,
			startDate);

		sales.add(new SalesProduct(product, product.getPrice(), category(product), currentSales, currentBilling,
			previousSales, previousBilling));
	    }

	    return new PageImpl<SalesProduct>(sales, pageable, pageActivities.getTotalElements());
	case "transport":
	    Page<Transport> pageTransports = transportDao.findByLocation(location, pageable);

	    for (Transport product : pageTransports.getContent()) {
		Long currentSales = saleDao.countSalesByProductAndCreatedAt(product.getId(), startDate, endDate);
		BigDecimal currentBilling = saleDao.findBillingByProductAndCreatedAt(product.getId(), startDate,
			endDate);
		Long previousSales = saleDao.countSalesByProductAndCreatedAt(product.getId(), previousDate, startDate);
		BigDecimal previousBilling = saleDao.findBillingByProductAndCreatedAt(product.getId(), previousDate,
			startDate);

		sales.add(new SalesProduct(product, product.getPrice(), category(product), currentSales, currentBilling,
			previousSales, previousBilling));
	    }

	    return new PageImpl<SalesProduct>(sales, pageable, pageTransports.getTotalElements());
	case "travel":
	    Page<Travel> pageTravels = travelDao.findByLocation(location, pageable);

	    for (Travel product : pageTravels.getContent()) {
		Long currentSales = saleDao.countSalesByProductAndCreatedAt(product.getId(), startDate, endDate);
		BigDecimal currentBilling = saleDao.findBillingByProductAndCreatedAt(product.getId(), startDate,
			endDate);
		Long previousSales = saleDao.countSalesByProductAndCreatedAt(product.getId(), previousDate, startDate);
		BigDecimal previousBilling = saleDao.findBillingByProductAndCreatedAt(product.getId(), previousDate,
			startDate);

		sales.add(new SalesProduct(product, product.getPrice(), category(product), currentSales, currentBilling,
			previousSales, previousBilling));
	    }

	    return new PageImpl<SalesProduct>(sales, pageable, pageTravels.getTotalElements());
	default:
	    Page<Product> pageProducts = productDao.findByLocation(location, pageable);

	    for (Product product : pageProducts.getContent()) {
		Long currentSales = saleDao.countSalesByProductAndCreatedAt(product.getId(), startDate, endDate);
		BigDecimal currentBilling = saleDao.findBillingByProductAndCreatedAt(product.getId(), startDate,
			endDate);
		Long previousSales = saleDao.countSalesByProductAndCreatedAt(product.getId(), previousDate, startDate);
		BigDecimal previousBilling = saleDao.findBillingByProductAndCreatedAt(product.getId(), previousDate,
			startDate);

		sales.add(new SalesProduct(product, product.getPrice(), category(product), currentSales, currentBilling,
			previousSales, previousBilling));
	    }

	    return new PageImpl<SalesProduct>(sales, pageable, pageProducts.getTotalElements());
	}
    }

    private Date previousDate(Date startDate, Date endDate) {
	long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
	long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

	return Date.from(startDate.toInstant().minus(Duration.ofDays(diff)));
    }

    private String category(Product product) {
	if (product instanceof Accommodation) {
	    return "accommodation";
	} else if (product instanceof Activity) {
	    return "activity";
	} else if (product instanceof Transport) {
	    return "transport";
	} else if (product instanceof Travel) {
	    return "travel";
	} else {
	    return "product";
	}
    }
}
