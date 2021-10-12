package es.udc.asiproject.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.asiproject.backend.model.entities.Transport;
import es.udc.asiproject.backend.model.entities.TransportDao;
import es.udc.asiproject.backend.model.services.TransportService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class TransportServiceTest {

	@Autowired
	TransportService transportService;

	@Autowired
	TransportDao transportDao;

	@Test
	public void testFindTransports() {

		Transport t = new Transport("Patineta");
		transportDao.save(t);

		assertEquals(1, transportService.findTransports().size());

		Transport t1 = new Transport("Patines");
		transportDao.save(t1);

		assertEquals(2, transportService.findTransports().size());
	}

}
