package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.backend.daos.TransportDao;
import es.udc.asiproject.backend.daos.entities.Transport;
import es.udc.asiproject.backend.model.services.TransportService;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
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
