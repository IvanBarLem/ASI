package es.udc.asiproject.controller.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class SalesAgentDto {
	private Long agentId;
	private String firstName;
	private String lastName;
	private Long currentSales;
	private BigDecimal currentBilling;
	private Long previousSales;
	private BigDecimal previousBilling;

	public SalesAgentDto() {
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getCurrentSales() {
		return currentSales;
	}

	public void setCurrentSales(Long currentSales) {
		this.currentSales = currentSales;
	}

	public BigDecimal getCurrentBilling() {
		return currentBilling;
	}

	public void setCurrentBilling(BigDecimal currentBilling) {
		this.currentBilling = currentBilling;
	}

	public Long getPreviousSales() {
		return previousSales;
	}

	public void setPreviousSales(Long previousSales) {
		this.previousSales = previousSales;
	}

	public BigDecimal getPreviousBilling() {
		return previousBilling;
	}

	public void setPreviousBilling(BigDecimal previousBilling) {
		this.previousBilling = previousBilling;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agentId, currentBilling, currentSales, firstName, lastName, previousBilling, previousSales);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesAgentDto other = (SalesAgentDto) obj;
		return Objects.equals(agentId, other.agentId) && Objects.equals(currentBilling, other.currentBilling)
				&& Objects.equals(currentSales, other.currentSales) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(previousBilling, other.previousBilling)
				&& Objects.equals(previousSales, other.previousSales);
	}
}
