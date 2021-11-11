package es.udc.asiproject.controller.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.controller.dto.ActivityDto;
import es.udc.asiproject.persistence.model.Activity;

@Component
public class ActivityMapper {
	private static ModelMapper mapper;

	@Autowired
	private ActivityMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static ActivityDto convertToDto(Activity activity) {
		return mapper.map(activity, ActivityDto.class);
	}

	public static Set<ActivityDto> convertToDto(Set<Activity> activities) {
		return activities.stream().map(item -> convertToDto(item)).collect(Collectors.toSet());
	}

	public static List<ActivityDto> convertToDto(List<Activity> activities) {
		return activities.stream().map(item -> convertToDto(item)).collect(Collectors.toList());
	}

	public static Activity convertToEntity(ActivityDto activityDto) {
		return mapper.map(activityDto, Activity.class);
	}

	public static Set<Activity> convertToEntity(Set<ActivityDto> activitiesDtos) {
		return activitiesDtos.stream().map(item -> convertToEntity(item)).collect(Collectors.toSet());
	}
}
