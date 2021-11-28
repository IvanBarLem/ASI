package es.udc.asiproject.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.udc.asiproject.persistence.dao.SaleDao;
import es.udc.asiproject.persistence.dao.UserDao;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Sale;
import es.udc.asiproject.persistence.model.Sale.SaleState;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.persistence.model.User.RoleType;
import es.udc.asiproject.service.SaleService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.PermissionException;

@Service
public class SaleServiceImpl implements SaleService {

	@Autowired
	UserDao userDao;

	@Autowired
	SaleDao saleDao;

	@Override
	public Page<Sale> findSales(Long userId, Long clientFilterId, Long agentFilterId, Integer pageNumber,
			Integer pageSize) {

		User user = userDao.getById(userId);

		Page<Sale> sales = null;

		switch (user.getRole()) {
		case AGENTE:
			sales = saleDao.findByAgentId(userId, PageRequest.of(pageNumber, pageSize));

		case INFORMATICO:
			sales = saleDao.findByAgentId(userId, PageRequest.of(pageNumber, pageSize));

		case USER:
			sales = saleDao.findByClientId(userId, PageRequest.of(pageNumber, pageSize));

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

			if (saleToDelete.get().getAgent().getId() == userId) {

				saleDao.delete(saleToDelete.get());

			} else
				throw new PermissionException();

		} else
			throw new InstanceNotFoundException(Sale.class.getSimpleName(), sale.getId());

	}

	@Override
	public void paySale(Long userId, Long saleId) throws InstanceNotFoundException, PermissionException {

		Optional<Sale> saleToPay = saleDao.findById(saleId);

		if (saleToPay.isPresent()) {

			if (saleToPay.get().getClient().getId() == userId) {

				saleToPay.get().setState(SaleState.PAID);
				saleDao.save(saleToPay.get());

			} else
				throw new PermissionException();

		} else
			throw new InstanceNotFoundException(Sale.class.getSimpleName(), saleId);

	}

	@Override
	public void generateBill(Long userId, Long saleId)
			throws InstanceNotFoundException, PermissionException, FileNotFoundException {

		Optional<Sale> OptionalSaleToGenerateBill = saleDao.findById(saleId);

		Optional<User> OptionalUser = userDao.findById(userId);

		if (OptionalSaleToGenerateBill.isPresent() && OptionalUser.isPresent()
				&& OptionalSaleToGenerateBill.get().getState() == SaleState.PAID) {

			Sale saleToGenerateBill = OptionalSaleToGenerateBill.get();
			User user = OptionalUser.get();

			if (user.getId() == saleToGenerateBill.getAgent().getId()
					|| user.getId() == saleToGenerateBill.getClient().getId() || user.getRole() == RoleType.GERENTE) {

				String ruta = "C:\\Users\\jaime\\Desktop\\Universidad\\MUEI\\ASI\\Prácticas\\Bills\bill.txt";

				PrintWriter file = new PrintWriter((new FileOutputStream(ruta, false)));

				file.println("Estado: " + saleToGenerateBill.getState());
				file.println("Precio: " + saleToGenerateBill.getPrice());
				file.println("Fecha: " + saleToGenerateBill.getCreatedAt());
				file.println("Agente: " + saleToGenerateBill.getAgent().getFirstName() + " "
						+ saleToGenerateBill.getAgent().getLastName());
				file.println("Cliente: " + saleToGenerateBill.getClient().getFirstName() + " "
						+ saleToGenerateBill.getClient().getLastName());
				file.println("");
				file.println("Alojamientos incluídos: ");
				for (Accommodation accommodation : saleToGenerateBill.getAccommodations()) {

					file.println(accommodation.getName());
				}
				file.println("");
				file.println("Actividades incluídas: ");
				for (Activity activity : saleToGenerateBill.getActivities()) {

					file.println(activity.getName());
				}
				file.println("");
				file.println("Transportes incluídos: ");
				for (Transport transport : saleToGenerateBill.getTransports()) {

					file.println(transport.getName());
				}
				file.println("");
				file.println("Viajes incluídos: ");
				for (Travel travel : saleToGenerateBill.getTravels()) {

					file.println(travel.getName());
				}

			}

		} else
			throw new InstanceNotFoundException(Sale.class.getSimpleName(), saleId);
	}

}
