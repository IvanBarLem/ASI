package es.udc.asiproject.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.controller.dto.AccommodationSaleDto;
import es.udc.asiproject.controller.dto.ActivitySaleDto;
import es.udc.asiproject.controller.dto.CreateSaleParamsDto;
import es.udc.asiproject.controller.dto.TransportSaleDto;
import es.udc.asiproject.controller.dto.TravelSaleDto;
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
import es.udc.asiproject.persistence.model.Sale;
import es.udc.asiproject.persistence.model.SaleProduct;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.persistence.model.enums.RoleType;
import es.udc.asiproject.persistence.model.enums.SaleState;
import es.udc.asiproject.service.SaleService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.InvalidOperationException;
import es.udc.asiproject.service.exceptions.PermissionException;

@Service
public class SaleServiceImpl implements SaleService {
	@Autowired
	AccommodationDao accommodationDao;
	@Autowired
	ActivityDao activityDao;
	@Autowired
	TransportDao transportDao;
	@Autowired
	TravelDao travelDao;
	@Autowired
	UserDao userDao;
	@Autowired
	SaleDao saleDao;
	@Autowired
	ProductDao productDao;

	private void validateSale(Sale sale) throws InvalidOperationException, InstanceNotFoundException {
		if (sale.getProducts().isEmpty()) {
			throw new InvalidOperationException("EmptySale");
		}

		Set<SaleProduct> saleProducts = new HashSet<>();
		saleProducts = sale.getProducts();

		for (SaleProduct saleProduct : saleProducts) {
			if (!productDao.existsById(saleProduct.getProduct().getId())) {
				throw new InstanceNotFoundException(Product.class.getSimpleName(), saleProduct.getProduct().getId());
			}
		}
	}

	@Override
	@Transactional
	public Sale createSale(CreateSaleParamsDto saleParams, Long userId)
			throws InstanceNotFoundException, InvalidOperationException {
		User agent = userDao.findById(userId)
				.orElseThrow(() -> new InstanceNotFoundException(User.class.getSimpleName(), userId));
		User client = userDao.findById(saleParams.getClientId())
				.orElseThrow(() -> new InstanceNotFoundException(User.class.getSimpleName(), saleParams.getClientId()));

		Sale sale = Sale.builder().state(SaleState.NORMAL).price(saleParams.getPrice()).createdAt(new Date())
				.agent(agent).client(client).build();
		saleDao.save(sale);

		Set<SaleProduct> saleProducts = new HashSet<SaleProduct>();

		for (AccommodationSaleDto accommodationSaleDto : saleParams.getAccommodations()) {
			Optional<Accommodation> accommodation = accommodationDao.findById(accommodationSaleDto.getId());
			if (accommodation.isPresent()) {
				saleProducts.add(new SaleProduct(sale, accommodation.get(), accommodationSaleDto.getQuantity()));
			} else {
				throw new InstanceNotFoundException(Accommodation.class.getSimpleName(), accommodationSaleDto.getId());
			}
		}
		for (ActivitySaleDto activitySaleDto : saleParams.getActivities()) {
			Optional<Activity> activity = activityDao.findById(activitySaleDto.getId());
			if (activity.isPresent()) {
				saleProducts.add(new SaleProduct(sale, activity.get(), activitySaleDto.getQuantity()));
			} else {
				throw new InstanceNotFoundException(Activity.class.getSimpleName(), activitySaleDto.getId());
			}
		}
		for (TransportSaleDto transportSaleDto : saleParams.getTransports()) {
			Optional<Transport> transport = transportDao.findById(transportSaleDto.getId());
			if (transport.isPresent()) {
				saleProducts.add(new SaleProduct(sale, transport.get(), transportSaleDto.getQuantity()));
			} else {
				throw new InstanceNotFoundException(Transport.class.getSimpleName(), transportSaleDto.getId());
			}
		}
		for (TravelSaleDto travelSaleDto : saleParams.getTravels()) {
			Optional<Travel> travel = travelDao.findById(travelSaleDto.getId());
			if (travel.isPresent()) {
				saleProducts.add(new SaleProduct(sale, travel.get(), travelSaleDto.getQuantity()));
			} else {
				throw new InstanceNotFoundException(Travel.class.getSimpleName(), travelSaleDto.getId());
			}
		}
		sale.setProducts(saleProducts);
		validateSale(sale);

		return saleDao.save(sale);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Sale> findSales(Long userId, String agentName, String clientName, Integer pageNumber, Integer pageSize)
			throws InstanceNotFoundException {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Direction.DESC, "createdAt");

		User user = userDao.findById(userId)
				.orElseThrow(() -> new InstanceNotFoundException(User.class.getSimpleName(), userId));
		switch (user.getRole()) {
		case USER:
			return saleDao.findByClientName(clientName, pageable);
		case GERENTE:
			return saleDao.findByAgentNameAndClientName(agentName, clientName, pageable);
		default:
			return saleDao.findByAgentName(agentName, pageable);
		}
	}

	@Override
	@Transactional
	public void freezeSale(Long userId, Long saleId) throws InstanceNotFoundException, PermissionException {
		Sale sale = saleDao.findById(saleId)
				.orElseThrow(() -> new InstanceNotFoundException(Sale.class.getSimpleName(), saleId));
		User agent = userDao.findById(userId)
				.orElseThrow(() -> new InstanceNotFoundException(User.class.getSimpleName(), userId));

		if (sale.getAgent().getId() == userId || agent.getRole() == RoleType.GERENTE) {
			sale.setState(SaleState.FREEZE);
		} else {
			throw new PermissionException();
		}
	}

	@Override
	@Transactional
	public void paySale(Long userId, Long saleId) throws InstanceNotFoundException, PermissionException {
		Sale sale = saleDao.findById(saleId)
				.orElseThrow(() -> new InstanceNotFoundException(Sale.class.getSimpleName(), saleId));
		User agent = userDao.findById(userId)
				.orElseThrow(() -> new InstanceNotFoundException(User.class.getSimpleName(), userId));

		if (sale.getAgent().getId() == userId || agent.getRole() == RoleType.GERENTE) {
			sale.setState(SaleState.PAID);
		} else {
			throw new PermissionException();
		}
	}
}
