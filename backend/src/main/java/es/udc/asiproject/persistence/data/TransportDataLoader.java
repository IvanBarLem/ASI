package es.udc.asiproject.persistence.data;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.udc.asiproject.persistence.dao.TransportDao;
import es.udc.asiproject.persistence.model.Transport;

@Component
@Profile("!test")
public class TransportDataLoader implements ApplicationRunner {
	@Autowired
	private TransportDao transportDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (transportDao.count() == 0) {
			transportDao.save(new Transport("Avión", new BigDecimal(150.00)));
			transportDao.save(new Transport("Barco", new BigDecimal(90.00)));
			transportDao.save(new Transport("Bus", new BigDecimal(8.50)));
			transportDao.save(new Transport("Tren", new BigDecimal(18.00)));
		}
	}
}
