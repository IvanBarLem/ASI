package es.udc.paproject.backend.test.model.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import es.udc.paproject.backend.model.entities.Bid;
import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.entities.User;

public class ProductTest {

	private Product createProduct(String name, Long duration) {
		return new Product(new User(), name, "description", duration, new BigDecimal(100), "shipmentInfo",
				new Category());
	}

	private Bid createBid(BigDecimal amount, Product product) {
		return new Bid(new User(), amount, product);
	}

	@Test
	public void testgetRemainingMinutes() {

		Product product1 = createProduct("product1", new Long(30));

		// Comparamos con 29 y no 30, porque al ser 30, al pasar una milésima, se cambia
		// a 29
		assertEquals(new Long(29), product1.getRemainingMinutes());

	}

	@Test
	public void testCalculateMaxBidAndActualPriceAndMinPrice() {

		BigDecimal price1 = new BigDecimal(200);
		Product product1 = createProduct("product1", new Long(30));
		Bid bid1 = createBid(price1, product1);
		product1.calculateMaxBidAndActualPriceAndMinPrice(product1, bid1);
		assertEquals(bid1, product1.getMaxBid());
		assertEquals(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP), product1.getActualPrice());
		assertEquals(new BigDecimal(100.5).setScale(2, RoundingMode.HALF_UP), product1.getMinPrice());
		
		BigDecimal price2 = new BigDecimal(250);
		Bid bid2 = createBid(price2, product1);
		product1.calculateMaxBidAndActualPriceAndMinPrice(product1, bid2);
		assertEquals(bid2, product1.getMaxBid());
		assertEquals(new BigDecimal(200.50).setScale(2, RoundingMode.HALF_UP), product1.getActualPrice());
		assertEquals(new BigDecimal(201).setScale(2, RoundingMode.HALF_UP), product1.getMinPrice());	

		BigDecimal price3 = new BigDecimal(225);
		Bid bid3 = createBid(price3, product1);
		product1.calculateMaxBidAndActualPriceAndMinPrice(product1, bid3);
		assertEquals(bid2, product1.getMaxBid());
		assertEquals(new BigDecimal(225.50).setScale(2, RoundingMode.HALF_UP), product1.getActualPrice());
		assertEquals(new BigDecimal(226).setScale(2, RoundingMode.HALF_UP), product1.getMinPrice());
		
		Bid bid4 = createBid(price2, product1);
		product1.calculateMaxBidAndActualPriceAndMinPrice(product1, bid4);
		assertEquals(bid2, product1.getMaxBid());
		assertEquals(new BigDecimal(250).setScale(2, RoundingMode.HALF_UP), product1.getActualPrice());
		assertEquals(new BigDecimal(250.5).setScale(2, RoundingMode.HALF_UP), product1.getMinPrice());		
		
	}

}
