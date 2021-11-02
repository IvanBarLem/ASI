package es.udc.asiproject.controller.mapper;

import java.util.function.Function;

import org.springframework.data.domain.Page;

import es.udc.asiproject.controller.dto.PageDto;

public class PageMapper {
	private PageMapper() {
	}

	public static <U, T> PageDto<U> convertToDto(Page<T> page, Function<? super T, ? extends U> converter) {
		PageDto<U> pageDto = new PageDto<U>(page, converter);

		return pageDto;
	}
}
