package es.udc.asiproject.persistence.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import es.udc.asiproject.persistence.model.key.SaleProductId;

@Entity
public class SaleProduct {
	@EmbeddedId
	private SaleProductId id;
	@Column(nullable = false)
	private Integer quantity;
	@MapsId("sale_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Sale sale;
	@MapsId("product_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;

	public SaleProduct() {
	}

	public SaleProduct(Sale sale, Product product, Integer quantity) {
		this.id = new SaleProductId(sale.getId(), product.getId());
		this.quantity = quantity;
		this.sale = sale;
		this.product = product;
	}

	public SaleProductId getId() {
		return id;
	}

	public void setId(SaleProductId id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleProduct other = (SaleProduct) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "SaleProduct [id=" + id + ", quantity=" + quantity + ", sale=" + sale + ", product=" + product + "]";
	}
}
