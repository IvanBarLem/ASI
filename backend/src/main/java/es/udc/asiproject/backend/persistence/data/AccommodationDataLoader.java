package es.udc.asiproject.backend.persistence.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.udc.asiproject.backend.persistence.dao.AccommodationDao;
import es.udc.asiproject.backend.persistence.model.Accommodation;

@Component
@Profile("!test")
public class AccommodationDataLoader implements ApplicationRunner {
	@Autowired
	private AccommodationDao accommodationDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (accommodationDao.count() == 0) {
			accommodationDao.save(new Accommodation("Hesperia"));
			accommodationDao.save(new Accommodation("AC Hoteles"));
			accommodationDao.save(new Accommodation("Casa Pepa"));
			accommodationDao.save(new Accommodation("Pension Luis"));
		}
	}
}
