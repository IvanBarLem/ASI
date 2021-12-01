package es.udc.asiproject.controller.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class SalesCompanyDto {
	private Long currentSales;
	private BigDecimal currentBilling;
	private Long previousSales;
	private BigDecimal previousBilling;

	public SalesCompanyDto() {
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
		return Objects.hash(currentBilling, currentSales, previousBilling, previousSales);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesCompanyDto other = (SalesCompanyDto) obj;
		return Objects.equals(currentBilling, other.currentBilling) && Objects.equals(currentSales, other.currentSales)
				&& Objects.equals(previousBilling, other.previousBilling)
				&& Objects.equals(previousSales, other.previousSales);
	}

	@Override
	public String toString() {
		return "SalesCompanyDto [currentSales=" + currentSales + ", currentBilling=" + currentBilling
				+ ", previousSales=" + previousSales + ", previousBilling=" + previousBilling + "]";
	}
}
