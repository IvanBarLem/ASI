package es.udc.asiproject.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.asiproject.backend.model.entities.Travel;
import es.udc.asiproject.backend.model.entities.TravelDao;
import es.udc.asiproject.backend.model.services.TravelService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
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
