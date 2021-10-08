package es.udc.asiproject.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.asiproject.backend.model.entities.Activity;
import es.udc.asiproject.backend.model.entities.ActivityDao;
import es.udc.asiproject.backend.model.services.ActivityService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ActivityServiceTest {

	@Autowired
	ActivityService activityService;

	@Autowired
	ActivityDao activityDao;

	@Test
	public void testFindActivities() {

		Activity activity1 = new Activity("Motos de Agua");
		activityDao.save(activity1);

		assertEquals(1, activityService.findActivities().size());

	}

}
