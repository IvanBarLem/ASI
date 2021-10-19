package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.backend.persistence.dao.TravelDao;
import es.udc.asiproject.backend.persistence.model.Travel;
import es.udc.asiproject.backend.service.TravelService;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class TravelServiceTest {
	@Autowired
	TravelService travelService;
	@Autowired
	TravelDao travelDao;

	@Test
	public void testFindTransports() {
		Travel t = new Travel("Egipto Antiguo");
		travelDao.save(t);

		assertEquals(1, travelService.findTravels().size());
	}
}
