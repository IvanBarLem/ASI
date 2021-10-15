package es.udc.asiproject.backend.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.backend.model.services.ActivityService;
import es.udc.asiproject.backend.rest.dtos.ActivityConversor;
import es.udc.asiproject.backend.rest.dtos.ActivityDto;

@RestController
@RequestMapping("/activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@GetMapping("/findActivities")
	public List<ActivityDto> findActivities() {

		return ActivityConversor.toActivityDtos(activityService.findActivities());
	}

}
