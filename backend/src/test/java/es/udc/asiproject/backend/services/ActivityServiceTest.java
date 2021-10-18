package es.udc.asiproject.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import es.udc.asiproject.backend.persistence.dao.ActivityDao;
import es.udc.asiproject.backend.persistence.model.Activity;
import es.udc.asiproject.backend.service.ActivityService;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
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
