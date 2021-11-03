package es.udc.asiproject.persistence.data;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.udc.asiproject.persistence.dao.AccommodationDao;
import es.udc.asiproject.persistence.model.Accommodation;

@Component
@Profile("!test")
public class AccommodationDataLoader implements ApplicationRunner {
	@Autowired
	private AccommodationDao accommodationDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (accommodationDao.count() == 0) {
			accommodationDao.save(new Accommodation("Hesperia", new BigDecimal(50.0)));
			accommodationDao.save(new Accommodation("AC Hoteles", new BigDecimal(49.99)));
			accommodationDao.save(new Accommodation("Casa Pepa", new BigDecimal(70.99)));
			accommodationDao.save(new Accommodation("Pension Luis", new BigDecimal(120.00)));
		}
	}
}
