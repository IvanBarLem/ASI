package es.udc.paproject.backend.test.model.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import es.udc.paproject.backend.model.entities.Bid;
import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.entities.User;

public class BidTest {

	private Product createProduct(String name, Long duration) {
		return new Product(new User(), name, "description", duration, new BigDecimal(100), "shipmentInfo",
				new Category());
	}

	private Bid createBid(BigDecimal amount, Product product) {
		return new Bid(new User(), amount, product);
	}

	@Test
	public void testgetState() {
		
		BigDecimal price1 = new BigDecimal(200);
		Product product1 = createProduct("product1", new Long(30));
		Bid bid1 = createBid(price1, product1);
		product1.setMaxBid(bid1);
		
		BigDecimal price2 = new BigDecimal(250);
		Bid bid2 = createBid(price2, product1);
		product1.setMaxBid(bid2); //price2 > price1
		
		assertEquals("WINNING", bid2.getState());
		assertEquals("LOSE", bid1.getState());
		
		Product product2 = createProduct("product2", new Long(-20));
		Bid bid3 = createBid(price1, product2);
		product2.setMaxBid(bid3);
		assertEquals("WINNER", bid3.getState());
		

	}

}
