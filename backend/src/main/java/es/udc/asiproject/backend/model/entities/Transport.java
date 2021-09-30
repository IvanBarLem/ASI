package es.udc.asiproject.backend.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transport {

    private Long id;
    private TransportType type;
    private LocalDateTime date;
    private String origin;
    private String destination;

    public Transport() {
    }

    public Transport(TransportType type, LocalDateTime date, String origin, String destination) {
	super();
	this.type = type;
	this.date = date;
	this.origin = origin;
	this.destination = destination;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "transportTypeId")
    public TransportType getType() {
	return type;
    }

    public void setType(TransportType type) {
	this.type = type;
    }

    public LocalDateTime getDate() {
	return date;
    }

    public void setDate(LocalDateTime date) {
	this.date = date;
    }

    public String getOrigin() {
	return origin;
    }

    public void setOrigin(String origin) {
	this.origin = origin;
    }

    public String getDestination() {
	return destination;
    }

    public void setDestination(String destination) {
	this.destination = destination;
    }
}
