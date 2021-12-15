package es.udc.asiproject.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	    throw new InvalidOperationException("Empty Sale Error");
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
    public Sale createSale(CreateSaleParamsDto createSaleParamsDto, Long userId)
	    throws InstanceNotFoundException, InvalidOperationException {

	Optional<User> agent = userDao.findById(userId);
	Optional<User> client = userDao.findById(createSaleParamsDto.getClientId());

	if (agent.isPresent() && client.isPresent()) {

	    Sale sale = new Sale();
	    sale.setPrice(createSaleParamsDto.getPrice());
	    sale.setAgent(agent.get());
	    sale.setClient(client.get());
	    sale.setCreatedAt(new Date());
	    sale.setState(SaleState.NORMAL);
	    saleDao.save(sale);

	    Set<SaleProduct> saleProducts = new HashSet<SaleProduct>();

	    for (AccommodationSaleDto accommodationSaleDto : createSaleParamsDto.getAccommodations()) {
		Optional<Accommodation> accommodation = accommodationDao.findById(accommodationSaleDto.getId());
		if (accommodation.isPresent()) {
		    saleProducts.add(new SaleProduct(sale, accommodation.get(), accommodationSaleDto.getQuantity()));
		} else
		    throw new InstanceNotFoundException(Accommodation.class.getSimpleName(),
			    accommodationSaleDto.getId());

	    }
	    for (ActivitySaleDto activitySaleDto : createSaleParamsDto.getActivities()) {
		Optional<Activity> activity = activityDao.findById(activitySaleDto.getId());
		if (activity.isPresent()) {
		    saleProducts.add(new SaleProduct(sale, activity.get(), activitySaleDto.getQuantity()));
		} else
		    throw new InstanceNotFoundException(Activity.class.getSimpleName(), activitySaleDto.getId());

	    }
	    for (TransportSaleDto transportSaleDto : createSaleParamsDto.getTransports()) {
		Optional<Transport> transport = transportDao.findById(transportSaleDto.getId());
		if (transport.isPresent()) {
		    saleProducts.add(new SaleProduct(sale, transport.get(), transportSaleDto.getQuantity()));
		} else
		    throw new InstanceNotFoundException(Transport.class.getSimpleName(), transportSaleDto.getId());
	    }
	    for (TravelSaleDto travelSaleDto : createSaleParamsDto.getTravels()) {
		Optional<Travel> travel = travelDao.findById(travelSaleDto.getId());
		if (travel.isPresent()) {
		    saleProducts.add(new SaleProduct(sale, travel.get(), travelSaleDto.getQuantity()));
		} else
		    throw new InstanceNotFoundException(Travel.class.getSimpleName(), travelSaleDto.getId());
	    }
	    sale.setProducts(saleProducts);

	    validateSale(sale);

	    return saleDao.save(sale);

	} else
	    throw new InstanceNotFoundException(User.class.getSimpleName(), createSaleParamsDto.getClientId());

    }

//	@Override
//	@Transactional
//	public Sale updateSale(Sale sale) throws InstanceNotFoundException, InvalidOperationException {
//		Sale oldSale = saleDao.findById(sale.getId())
//				.orElseThrow(() -> new InstanceNotFoundException(Sale.class.getSimpleName(), sale.getId()));
//
//		validateSale(sale);
//
//		oldSale.setState(sale.getState());
//		oldSale.setPrice(sale.getPrice());
//		oldSale.setAgent(sale.getAgent());
//		oldSale.setClient(sale.getClient());
//
//		return oldSale;
//	}

    @Override
    public Page<Sale> findSales(Long userId, Long clientFilterId, Long agentFilterId, Integer pageNumber,
	    Integer pageSize) {

	User user = userDao.getById(userId);

	Page<Sale> sales = null;

	switch (user.getRole()) {
	case AGENTE:
	    sales = saleDao.findByAgentId(userId, PageRequest.of(pageNumber, pageSize));
	    break;

	case INFORMATICO:
	    sales = saleDao.findByAgentId(userId, PageRequest.of(pageNumber, pageSize));
	    break;

	case USER:
	    sales = saleDao.findByClientId(userId, PageRequest.of(pageNumber, pageSize));
	    break;

	case GERENTE:
	    if (clientFilterId == null && agentFilterId == null) {

		sales = saleDao.findAll(PageRequest.of(pageNumber, pageSize));
	    }

	    else if (clientFilterId != null && agentFilterId == null) {

		sales = saleDao.findByClientId(clientFilterId, PageRequest.of(pageNumber, pageSize));
	    }

	    else if (clientFilterId == null && agentFilterId != null) {
		sales = saleDao.findByAgentId(agentFilterId, PageRequest.of(pageNumber, pageSize));
	    }

	}

	return sales;
    }

    @Override
    public void deleteSale(Long userId, Sale sale) throws InstanceNotFoundException, PermissionException {

	Optional<Sale> saleToDelete = saleDao.findById(sale.getId());

	if (saleToDelete.isPresent()) {

	    if (saleToDelete.get().getAgent().getId() == userId && saleToDelete.get().getState() == SaleState.NORMAL) {

		saleDao.delete(saleToDelete.get());

	    } else
		throw new PermissionException();

	} else
	    throw new InstanceNotFoundException(Sale.class.getSimpleName(), sale.getId());

    }

    @Override
    public void paySale(Long userId, Long saleId) throws InstanceNotFoundException, PermissionException {

	Optional<Sale> saleToPay = saleDao.findById(saleId);
	Optional<User> userToPay = userDao.findById(userId);

	if (saleToPay.isPresent() && userToPay.isPresent() && saleToPay.get().getState() == SaleState.FREEZE) {

	    if (saleToPay.get().getClient().getId() == userId || userToPay.get().getRole() == RoleType.GERENTE) {

		saleToPay.get().setState(SaleState.PAID);
		saleDao.save(saleToPay.get());

	    } else
		throw new PermissionException();

	} else
	    throw new InstanceNotFoundException(Sale.class.getSimpleName(), saleId);

    }

//	@Override
//	public ByteArrayResource generateBill(Long userId, Long saleId)
//			throws InstanceNotFoundException, PermissionException, FileNotFoundException, IOException {
//
//		Optional<Sale> OptionalSaleToGenerateBill = saleDao.findById(saleId);
//
//		Optional<User> OptionalUser = userDao.findById(userId);
//
//		if (OptionalSaleToGenerateBill.isPresent() && OptionalUser.isPresent()
//				&& OptionalSaleToGenerateBill.get().getState() == SaleState.PAID) {
//
//			Sale saleToGenerateBill = OptionalSaleToGenerateBill.get();
//			User user = OptionalUser.get();
//
//			if (user.getId() == saleToGenerateBill.getAgent().getId()
//					|| user.getId() == saleToGenerateBill.getClient().getId() || user.getRole() == RoleType.GERENTE) {
//
//				String ruta = "../src/main/resources/bill.txt";
//
//				PrintWriter file = new PrintWriter((new FileOutputStream(ruta, false)));
//
//				file.println("Estado: " + saleToGenerateBill.getState());
//				file.println("Precio: " + saleToGenerateBill.getPrice());
//				file.println("Fecha: " + saleToGenerateBill.getCreatedAt());
//				file.println("Agente: " + saleToGenerateBill.getAgent().getFirstName() + " "
//						+ saleToGenerateBill.getAgent().getLastName());
//				file.println("Cliente: " + saleToGenerateBill.getClient().getFirstName() + " "
//						+ saleToGenerateBill.getClient().getLastName());
//				file.println("");
//				file.println("Alojamientos incluídos: ");
//				for (Accommodation accommodation : saleToGenerateBill.getAccommodations()) {
//
//					file.println(accommodation.getName());
//				}
//				file.println("");
//				file.println("Actividades incluídas: ");
//				for (Activity activity : saleToGenerateBill.getActivities()) {
//
//					file.println(activity.getName());
//				}
//				file.println("");
//				file.println("Transportes incluídos: ");
//				for (Transport transport : saleToGenerateBill.getTransports()) {
//
//					file.println(transport.getName());
//				}
//				file.println("");
//				file.println("Viajes incluídos: ");
//				for (Travel travel : saleToGenerateBill.getTravels()) {
//
//					file.println(travel.getName());
//				}
//
//				Path path = Paths.get(ruta);
//				ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//				return resource;
//			}
//
//		} else
//			throw new InstanceNotFoundException(Sale.class.getSimpleName(), saleId);
//
//		return null;
//	}

    @Override
    public void freezeSale(Long userId, Long saleId) throws InstanceNotFoundException, PermissionException {

	Optional<Sale> saleToFreeze = saleDao.findById(saleId);
	Optional<User> agentToFreeze = userDao.findById(userId);

	if (saleToFreeze.isPresent() && agentToFreeze.isPresent()) {
	    if (saleToFreeze.get().getAgent().getId() == userId || agentToFreeze.get().getRole() == RoleType.GERENTE) {
		saleToFreeze.get().setState(SaleState.FREEZE);
		saleDao.save(saleToFreeze.get());
	    } else
		throw new PermissionException();
	} else
	    throw new InstanceNotFoundException(Sale.class.getSimpleName(), saleId);
    }

}
