package es.udc.asiproject.backend.controller.dto;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;

import es.udc.asiproject.backend.controller.dto.validation.GetValidation;

public class PageDto<T> {
	@NotNull(groups = { GetValidation.class })
	@Min(value = 0, groups = { GetValidation.class })
	private Integer pageNumber;
	@NotNull(groups = { GetValidation.class })
	@Positive(groups = { GetValidation.class })
	private Integer pageSize;
	private Integer totalPages;
	private List<T> content;
	private Boolean hasNext;
	private Boolean hasPrevious;

	public PageDto() {
	}

	public <U> PageDto(Page<U> page, Function<? super U, ? extends T> converter) {
		Page<T> mappedPage = page.map(converter);
		this.pageNumber = mappedPage.getNumber();
		this.pageSize = mappedPage.getSize();
		this.totalPages = mappedPage.getTotalPages();
		this.content = mappedPage.getContent();
		this.hasNext = mappedPage.hasNext();
		this.hasPrevious = mappedPage.hasPrevious();
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}

	public Boolean getHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(Boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, hasNext, hasPrevious, pageNumber, pageSize, totalPages);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageDto<?> other = (PageDto<?>) obj;
		return Objects.equals(content, other.content) && Objects.equals(hasNext, other.hasNext)
				&& Objects.equals(hasPrevious, other.hasPrevious) && Objects.equals(pageNumber, other.pageNumber)
				&& Objects.equals(pageSize, other.pageSize) && Objects.equals(totalPages, other.totalPages);
	}
}
