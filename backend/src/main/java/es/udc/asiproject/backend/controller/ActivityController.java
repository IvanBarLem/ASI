package es.udc.asiproject.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.udc.asiproject.backend.controller.dto.ActivityDto;
import es.udc.asiproject.backend.controller.mapper.ActivityMapper;
import es.udc.asiproject.backend.service.ActivityService;

@RestController
@RequestMapping("/activities")
public class ActivityController {
	@Autowired
	private ActivityService activityService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ActivityDto> findActivities() {
		return ActivityMapper.convertToDto(activityService.findActivities());
	}
}
