package es.udc.asiproject.persistence.model.unrelated;

import java.math.BigDecimal;
import java.util.Objects;

public class SalesCompany {
	private Long currentSales;
	private BigDecimal currentBilling;
	private Long previousSales;
	private BigDecimal previousBilling;
	private Long numberOfProductsSold;

	public SalesCompany(Long currentSales, BigDecimal currentBilling, Long previousSales, BigDecimal previousBilling, Long numberOfProductsSold) {
		this.currentSales = currentSales;
		this.currentBilling = currentBilling;
		this.previousSales = previousSales;
		this.previousBilling = previousBilling;
		this.numberOfProductsSold = numberOfProductsSold;
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

	public Long getNumberOfProductsSold() {
		return numberOfProductsSold;
	}

	public void setNumberOfProductsSold(Long numberOfProductsSold) {
		this.numberOfProductsSold = numberOfProductsSold;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentBilling, currentSales, numberOfProductsSold, previousBilling, previousSales);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesCompany other = (SalesCompany) obj;
		return Objects.equals(currentBilling, other.currentBilling) && Objects.equals(currentSales, other.currentSales)
				&& Objects.equals(numberOfProductsSold, other.numberOfProductsSold)
				&& Objects.equals(previousBilling, other.previousBilling)
				&& Objects.equals(previousSales, other.previousSales);
	}

	@Override
	public String toString() {
		return "SalesCompany [currentSales=" + currentSales + ", currentBilling=" + currentBilling + ", previousSales="
				+ previousSales + ", previousBilling=" + previousBilling + ", numberOfProductsSold="
				+ numberOfProductsSold + "]";
	}
	

}
