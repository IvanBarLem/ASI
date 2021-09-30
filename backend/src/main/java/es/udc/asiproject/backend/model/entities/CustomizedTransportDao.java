package es.udc.asiproject.backend.model.entities;

import java.time.LocalDateTime;

import org.springframework.data.domain.Slice;

public interface CustomizedTransportDao {
    Slice<Transport> find(Long type, LocalDateTime startDate, LocalDateTime endDate, String origin, String destination,
	    int page, int size);
}
