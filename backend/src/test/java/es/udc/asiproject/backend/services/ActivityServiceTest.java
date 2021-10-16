package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.backend.daos.ActivityDao;
import es.udc.asiproject.backend.daos.entities.Activity;
import es.udc.asiproject.backend.model.services.ActivityService;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ActivityServiceTest {
	@Autowired
	ActivityService activityService;

	@Autowired
	ActivityDao activityDao;

	@Test
	public void testFindActivities() {
		Activity activity = new Activity("Motos de Agua");
		activityDao.save(activity);

		assertEquals(1, activityService.findActivities().size());
	}
}
