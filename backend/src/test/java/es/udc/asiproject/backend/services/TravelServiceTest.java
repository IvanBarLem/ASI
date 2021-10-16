package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.backend.daos.TravelDao;
import es.udc.asiproject.backend.daos.entities.Travel;
import es.udc.asiproject.backend.model.services.TravelService;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
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
