package es.udc.asiproject.backend.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.asiproject.backend.controller.dto.ActivityDto;
import es.udc.asiproject.backend.persistence.model.Activity;

@Component
public class ActivityMapper {
	private static ModelMapper mapper;

	@Autowired
	private ActivityMapper(ModelMapper modelMapper) {
		mapper = modelMapper;
	}

	public static ActivityDto convertToDto(Activity activity) {
		ActivityDto activityDto = mapper.map(activity, ActivityDto.class);

		return activityDto;
	}

	public static Activity convertToEntity(ActivityDto activityDto) {
		Activity activity = mapper.map(activityDto, Activity.class);

		return activity;
	}
}
