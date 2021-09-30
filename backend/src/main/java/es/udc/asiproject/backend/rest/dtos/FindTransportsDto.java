package es.udc.asiproject.backend.rest.dtos;

public class FindTransportsDto {
    private Long transportTypeId;
    private String startDate;
    private String endDate;
    private String origin;
    private String destination;

    public FindTransportsDto() {
    }

    public Long getTransportTypeId() {
	return transportTypeId;
    }

    public void setTransportTypeId(Long transportTypeId) {
	this.transportTypeId = transportTypeId;
    }

    public String getStartDate() {
	return startDate;
    }

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
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
