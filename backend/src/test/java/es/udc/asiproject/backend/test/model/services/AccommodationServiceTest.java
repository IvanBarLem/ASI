package es.udc.asiproject.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.asiproject.backend.model.entities.Accommodation;
import es.udc.asiproject.backend.model.entities.AccommodationDao;
import es.udc.asiproject.backend.model.services.AccommodationService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AccommodationServiceTest {

	@Autowired
	AccommodationService accommodationService;

	@Autowired
	AccommodationDao accommodationDao;

	@Test
	public void testFindAccommodations() {

		Accommodation accommodation1 = new Accommodation("Hesperia marineda");
		accommodationDao.save(accommodation1);

		assertEquals(1, accommodationService.findAccommodations().size());

	}

}
