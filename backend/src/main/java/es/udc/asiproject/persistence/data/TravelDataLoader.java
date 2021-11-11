package es.udc.asiproject.persistence.data;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.udc.asiproject.persistence.dao.TravelDao;
import es.udc.asiproject.persistence.model.Travel;

@Component
@Profile("!test")
public class TravelDataLoader implements ApplicationRunner {
	@Autowired
	private TravelDao travelDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (travelDao.count() == 0) {
			travelDao.save(new Travel("Madrid Cultural", new BigDecimal(20.99)));
			travelDao.save(new Travel("Amazonas Salvaje", new BigDecimal(14.99)));
			travelDao.save(new Travel("Caribe Tropical", new BigDecimal(90.00)));
			travelDao.save(new Travel("Tibet Espiritual", new BigDecimal(290.00)));
		}
	}
}
