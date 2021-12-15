package es.udc.asiproject.persistence.model.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("serial")
public class SaleProductId implements Serializable {
	@Column(name = "sale_id")
	private Long saleId;
	@Column(name = "product_id")
	private Long productId;

	public SaleProductId() {
	}

	public SaleProductId(Long saleId, Long productId) {
		this.saleId = saleId;
		this.productId = productId;
	}

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, saleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleProductId other = (SaleProductId) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(saleId, other.saleId);
	}

	@Override
	public String toString() {
		return "SaleProductId [saleId=" + saleId + ", productId=" + productId + "]";
	}
}
