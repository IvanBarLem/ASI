package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.backend.persistence.dao.AccommodationDao;
import es.udc.asiproject.backend.persistence.model.Accommodation;
import es.udc.asiproject.backend.service.AccommodationService;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AccommodationServiceTest {
	@Autowired
	AccommodationService accommodationService;
	@Autowired
	AccommodationDao accommodationDao;

	@Test
	public void testFindAccommodations() {
		Accommodation accommodation = new Accommodation("Hesperia marineda");
		accommodationDao.save(accommodation);

		assertEquals(1, accommodationService.findAccommodations().size());
	}
}
