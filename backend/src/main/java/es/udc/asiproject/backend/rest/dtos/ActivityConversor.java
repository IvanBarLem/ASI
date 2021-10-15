package es.udc.asiproject.backend.rest.dtos;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.asiproject.backend.model.entities.Activity;

public class ActivityConversor {

	private ActivityConversor() {
	}

	public static ActivityDto toActivityDto(Activity activity) {

		return new ActivityDto(activity.getId(), activity.getName());
	}

	public static List<ActivityDto> toActivityDtos(List<Activity> activities) {
		return activities.stream().map(ActivityConversor::toActivityDto).collect(Collectors.toList());
	}

}
