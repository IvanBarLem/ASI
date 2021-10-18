package es.udc.asiproject.backend.persistence.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.udc.asiproject.backend.persistence.dao.TravelDao;
import es.udc.asiproject.backend.persistence.model.Travel;

@Component
@Profile("!test")
public class TravelDataLoader implements ApplicationRunner {
	@Autowired
	private TravelDao travelDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (travelDao.count() == 0) {
			travelDao.save(new Travel("Madrid Cultural"));
			travelDao.save(new Travel("Amazonas Salvaje"));
			travelDao.save(new Travel("Caribe Tropical"));
			travelDao.save(new Travel("Tibet Espiritual"));
		}
	}
}
