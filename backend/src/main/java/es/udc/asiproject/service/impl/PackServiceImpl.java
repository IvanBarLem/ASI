package es.udc.asiproject.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.asiproject.persistence.dao.PackDao;
import es.udc.asiproject.persistence.dao.ProductDao;
import es.udc.asiproject.persistence.model.Accommodation;
import es.udc.asiproject.persistence.model.Activity;
import es.udc.asiproject.persistence.model.Pack;
import es.udc.asiproject.persistence.model.Product;
import es.udc.asiproject.persistence.model.Transport;
import es.udc.asiproject.persistence.model.Travel;
import es.udc.asiproject.service.PackService;
import es.udc.asiproject.service.exceptions.InstanceNotFoundException;
import es.udc.asiproject.service.exceptions.InvalidOperationException;

@Service
public class PackServiceImpl implements PackService {
	@Autowired
	ProductDao productDao;
	@Autowired
	PackDao packDao;

	private void validateProducts(Pack pack) throws InvalidOperationException, InstanceNotFoundException {
		boolean hasAccommodation = false;
		boolean hasActivity = false;
		boolean hasTransport = false;
		boolean hasTravel = false;
		Set<Product> products = new HashSet<Product>();
		for (Product product : pack.getProducts()) {
			if (product instanceof Accommodation) {
				hasAccommodation = true;
			} else if (product instanceof Activity) {
				hasActivity = true;
			} else if (product instanceof Transport) {
				hasTransport = true;
			} else if (product instanceof Travel) {
				hasTravel = true;
			}
			products.add(productDao.findById(product.getId())
					.orElseThrow(() -> new InstanceNotFoundException(Product.class.getSimpleName(), product.getId())));
		}

		if (!hasAccommodation || !hasActivity || !hasTransport || !hasTravel) {
			throw new InvalidOperationException("EmptyPack");
		}
		pack.getProducts().clear();
		pack.setProducts(products);
	}

	@Override
	@Transactional
	public Pack createPack(Pack pack) throws InstanceNotFoundException, InvalidOperationException {
		validateProducts(pack);

		pack.setOutstanding(false);
		pack.setHidden(false);
		pack.setCreatedAt(new Date());

		packDao.save(pack);

		return pack;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Pack> findPacks(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Direction.DESC, "outstanding", "createdAt");

		return packDao.findByHiddenFalse(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Pack> findAllPacks(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Direction.DESC, "outstanding", "createdAt");

		return packDao.findAll(pageable);
	}

	@Override
	@Transactional
	public Pack updatePack(Pack pack) throws InstanceNotFoundException, InvalidOperationException {
		Pack oldPack = packDao.findById(pack.getId())
				.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), pack.getId()));

		validateProducts(pack);

		oldPack.setTitle(pack.getTitle());
		oldPack.setDescription(pack.getDescription());
		oldPack.setImage(pack.getImage());
		oldPack.setPrice(pack.getPrice());
		oldPack.setDuration(pack.getDuration());
		oldPack.setPersons(pack.getPersons());
		oldPack.setOutstanding(pack.getOutstanding());
		oldPack.setHidden(pack.getHidden());
		oldPack.setProducts(pack.getProducts());

		return oldPack;
	}

	@Override
	public void toggleHighlightPack(Long packId) throws InstanceNotFoundException {
		Pack pack = packDao.findById(packId)
				.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), packId));
		pack.setOutstanding(!pack.getOutstanding());
		packDao.save(pack);
	}

	@Override
	public void toggleHidePack(Long packId) throws InstanceNotFoundException {
		Pack pack = packDao.findById(packId)
				.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), packId));
		pack.setHidden(!pack.getHidden());
		packDao.save(pack);
	}

	@Override
	@Transactional
	public void removePack(Long id) throws InstanceNotFoundException {
		Pack pack = packDao.findById(id)
				.orElseThrow(() -> new InstanceNotFoundException(Pack.class.getSimpleName(), id));

		packDao.delete(pack);
	}
}
