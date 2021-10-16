package es.udc.asiproject.backend.daos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.udc.asiproject.backend.daos.TransportDao;
import es.udc.asiproject.backend.daos.entities.Transport;

@Component
@Profile("!test")
public class TransportDataLoader implements ApplicationRunner {
	@Autowired
	private TransportDao transportDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (transportDao.count() == 0) {
			transportDao.save(new Transport("Avión"));
			transportDao.save(new Transport("Barco"));
			transportDao.save(new Transport("Bus"));
			transportDao.save(new Transport("Tren"));
		}
	}
}
