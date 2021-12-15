package es.udc.asiproject.persistence.model.unrelated;

import java.math.BigDecimal;
import java.util.Objects;

import es.udc.asiproject.persistence.model.User;

public class SalesAgent {
	private Long agentId;
	private String firstName;
	private String lastName;
	private Long currentSales;
	private BigDecimal currentBilling;
	private Long previousSales;
	private BigDecimal previousBilling;

	public SalesAgent(User agent, Long currentSales, BigDecimal currentBilling, Long previousSales,
			BigDecimal previousBilling) {
		this.agentId = agent.getId();
		this.firstName = agent.getFirstName();
		this.lastName = agent.getLastName();
		this.currentSales = currentSales;
		this.currentBilling = currentBilling;
		this.previousSales = previousSales;
		this.previousBilling = previousBilling;
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
		SalesAgent other = (SalesAgent) obj;
		return Objects.equals(agentId, other.agentId) && Objects.equals(currentBilling, other.currentBilling)
				&& Objects.equals(currentSales, other.currentSales) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(previousBilling, other.previousBilling)
				&& Objects.equals(previousSales, other.previousSales);
	}

	@Override
	public String toString() {
		return "SalesAgent [agentId=" + agentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", currentSales=" + currentSales + ", currentBilling=" + currentBilling + ", previousSales="
				+ previousSales + ", previousBilling=" + previousBilling + "]";
	}

}
