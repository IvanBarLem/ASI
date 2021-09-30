package es.udc.asiproject.backend.model.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

public class CustomizedTransportDaoImpl implements CustomizedTransportDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public Slice<Transport> find(Long type, LocalDateTime startDate, LocalDateTime endDate, String origin,
	    String destination, int page, int size) {
	QueryConstructor queryConstructor = new QueryConstructor("SELECT t FROM Transport t");

	if (type != null) {
	    queryConstructor.addCondition("t.type.id = :type");
	}
	if (startDate != null) {
	    queryConstructor.addCondition("t.date >= :startDate");
	}
	if (endDate != null) {
	    queryConstructor.addCondition("t.date <= :endDate");
	}
	if (!origin.equals("")) {
	    queryConstructor.addCondition("LOWER(t.origin) LIKE LOWER(:origin)");
	}
	if (!destination.equals("")) {
	    queryConstructor.addCondition("LOWER(t.destination) LIKE LOWER(:destination)");
	}

	Query query = entityManager.createQuery(queryConstructor.getQuery()).setFirstResult(page * size)
		.setMaxResults(size + 1);

	if (type != null) {
	    query.setParameter("type", type);
	}
	if (startDate != null) {
	    query.setParameter("startDate", startDate);
	}
	if (endDate != null) {
	    query.setParameter("endDate", endDate);
	}
	if (!origin.equals("")) {
	    query.setParameter("origin", "%" + origin + "%");
	}
	if (!destination.equals("")) {
	    query.setParameter("destination", "%" + destination + "%");
	}

	List<Transport> transports = query.getResultList();
	boolean hasNext = transports.size() == (size + 1);
	if (hasNext) {
	    transports.remove(transports.size() - 1);
	}
	return new SliceImpl<>(transports, PageRequest.of(page, size), hasNext);
    }

}
