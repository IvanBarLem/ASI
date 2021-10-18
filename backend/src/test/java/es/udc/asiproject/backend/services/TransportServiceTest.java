package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.backend.persistence.dao.TransportDao;
import es.udc.asiproject.backend.persistence.model.Transport;
import es.udc.asiproject.backend.service.TransportService;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
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
