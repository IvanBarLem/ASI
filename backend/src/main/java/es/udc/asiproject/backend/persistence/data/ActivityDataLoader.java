package es.udc.asiproject.backend.persistence.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.udc.asiproject.backend.persistence.dao.ActivityDao;
import es.udc.asiproject.backend.persistence.model.Activity;

@Component
@Profile("!test")
public class ActivityDataLoader implements ApplicationRunner {
	@Autowired
	private ActivityDao activityDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (activityDao.count() == 0) {
			activityDao.save(new Activity("Paseo en Bicicleta"));
			activityDao.save(new Activity("Motos de Agua"));
			activityDao.save(new Activity("Karts"));
			activityDao.save(new Activity("Surf"));
		}
	}
}
