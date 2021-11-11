package es.udc.asiproject.persistence.data;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.udc.asiproject.persistence.dao.ActivityDao;
import es.udc.asiproject.persistence.model.Activity;

@Component
@Profile("!test")
public class ActivityDataLoader implements ApplicationRunner {
	@Autowired
	private ActivityDao activityDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (activityDao.count() == 0) {
			activityDao.save(new Activity("Paseo en Bicicleta", new BigDecimal(10.00)));
			activityDao.save(new Activity("Motos de Agua", new BigDecimal(25.00)));
			activityDao.save(new Activity("Karts", new BigDecimal(15.00)));
			activityDao.save(new Activity("Surf", new BigDecimal(30.00)));
		}
	}
}
