package es.udc.asiproject.controller.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class SalesProductDto {
	private Long productId;
	private String name;
	private BigDecimal price;
	private String location;
	private String category;
	private Long currentSales;
	private BigDecimal currentBilling;
	private Long previousSales;
	private BigDecimal previousBilling;

	public SalesProductDto() {
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, currentBilling, currentSales, location, name, previousBilling, previousSales,
				price, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesProductDto other = (SalesProductDto) obj;
		return Objects.equals(category, other.category) && Objects.equals(currentBilling, other.currentBilling)
				&& Objects.equals(currentSales, other.currentSales) && Objects.equals(location, other.location)
				&& Objects.equals(name, other.name) && Objects.equals(previousBilling, other.previousBilling)
				&& Objects.equals(previousSales, other.previousSales) && Objects.equals(price, other.price)
				&& Objects.equals(productId, other.productId);
	}

	@Override
	public String toString() {
		return "SalesProductDto [productId=" + productId + ", name=" + name + ", price=" + price + ", location="
				+ location + ", category=" + category + ", currentSales=" + currentSales + ", currentBilling="
				+ currentBilling + ", previousSales=" + previousSales + ", previousBilling=" + previousBilling + "]";
	}
	
	
}
