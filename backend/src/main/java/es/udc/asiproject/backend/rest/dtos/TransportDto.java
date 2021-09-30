package es.udc.asiproject.backend.rest.dtos;

public class TransportDto {
    private Long id;
    private Long transportTypeId;
    private String date;
    private String origin;
    private String destination;

    public TransportDto() {
    }

    public TransportDto(Long id, Long transportTypeId, String date, String origin, String destination) {
	super();
	this.id = id;
	this.transportTypeId = transportTypeId;
	this.date = date;
	this.origin = origin;
	this.destination = destination;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getTransportTypeId() {
	return transportTypeId;
    }

    public void setTransportTypeId(Long transportTypeId) {
	this.transportTypeId = transportTypeId;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
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
