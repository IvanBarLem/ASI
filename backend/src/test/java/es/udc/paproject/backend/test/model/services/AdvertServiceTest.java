package es.udc.paproject.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import es.udc.paproject.backend.model.common.exceptions.DuplicateInstanceException;
import es.udc.paproject.backend.model.common.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.entities.Bid;
import es.udc.paproject.backend.model.entities.BidDao;
import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.CategoryDao;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.services.AdvertService;
import es.udc.paproject.backend.model.services.AmountLessOrEqualThanActualPriceException;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.CatalogService;
import es.udc.paproject.backend.model.services.InvalidUserException;
import es.udc.paproject.backend.model.services.OutOfTimeException;
import es.udc.paproject.backend.model.services.PermissionException;
import es.udc.paproject.backend.model.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AdvertServiceTest {

	private final long NON_EXISTENT_ID = new Long(-1);

	@Autowired
	private UserService userService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private AdvertService advertService;

	@Autowired
	private BidDao bidDao;

	private User signUpUser(String userName) {

		User user = new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com");

		try {
			userService.signUp(user);
		} catch (DuplicateInstanceException e) {
			throw new RuntimeException(e);
		}

		return user;

	}

	private Product findProductById(long id) throws InstanceNotFoundException {
		return catalogService.findProductById(id);
	}

	private Bid addBid(User user, Product product, BigDecimal amount) {

		Bid bid = new Bid(user, amount, product);
		bidDao.save(bid);

		return bid;

	}

	@Test
	public void testAddToExistingUser() throws InstanceNotFoundException {
		User user = signUpUser("user");
		Category category = new Category("category");

		categoryDao.save(category);

		Product product = advertService.addProduct(user.getId(), "Reloj", "Reloj digital", (long) 200,
				new BigDecimal(5.0), "Correo estandar", category.getId());
		assertEquals(product, findProductById(product.getId()));
		// validations pending
	}

	@Test(expected = InstanceNotFoundException.class)
	public void testAddToNonExistingUser() throws InstanceNotFoundException, PermissionException {

		Category category = new Category("category");

		categoryDao.save(category);

		advertService.addProduct(NON_EXISTENT_ID, "product 1", "product number 1", (long) 100, new BigDecimal(11.0),
				"Correo estandar", category.getId());

	}

	@Test
	public void testFindNoBids() throws InstanceNotFoundException {

		User user = signUpUser("Pepe");
		Block<Bid> expectedBids = new Block<>(new ArrayList<>(), false);

		assertEquals(expectedBids, advertService.findBids(user.getId(), 0, 1));

	}

	@Test
	public void testFindBids() throws InstanceNotFoundException {

		User user1 = signUpUser("Pepe");
		User user2 = signUpUser("Juan");
		Category category = new Category("category");

		categoryDao.save(category);
		Product product = advertService.addProduct(user1.getId(), "Reloj", "Reloj digital", (long) 200,
				new BigDecimal(5.0), "Correo estandar", category.getId());
		Bid bid1 = addBid(user1, product, new BigDecimal(6.0));
		Bid bid2 = addBid(user2, product, new BigDecimal(10.0));
		Bid bid3 = addBid(user2, product, new BigDecimal(13.0));

		Block<Bid> expectedBlock = new Block<>(Arrays.asList(bid2, bid3), false);
		assertEquals(expectedBlock, advertService.findBids(user2.getId(), 0, 2));

		expectedBlock = new Block<>(Arrays.asList(bid1), false);
		assertEquals(expectedBlock, advertService.findBids(user1.getId(), 0, 2));

	}

	@Test
	public void testFindNoAdvertedProducts() throws InstanceNotFoundException {

		User user = signUpUser("Pepe");
		Block<Bid> expectedBids = new Block<>(new ArrayList<>(), false);

		assertEquals(expectedBids, advertService.findAdvertisedProducts(user.getId(), 0, 1));

	}

	@Test
	public void testFindAdvertedProducts() throws InstanceNotFoundException {

		User user1 = signUpUser("Pepe");
		User user2 = signUpUser("Jose");
		Category category = new Category("category");

		categoryDao.save(category);
		Product product1 = advertService.addProduct(user1.getId(), "Reloj", "Reloj digital", (long) 200,
				new BigDecimal(5.0), "Correo estandar", category.getId());
		Product product2 = advertService.addProduct(user2.getId(), "IPhone X", "Telefono móvil de gama alta", (long) 60,
				new BigDecimal(500.0), "Correo estandar", category.getId());
		Product product3 = advertService.addProduct(user1.getId(), "PS4", "Videoconsola", (long) 20,
				new BigDecimal(200.0), "Correo estandar", category.getId());

		// Falta comprobarlo cuando implementemos la la reducción de la duración
		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1, product3), false);
		assertEquals(expectedBlock, advertService.findAdvertisedProducts(user1.getId(), 0, 3));

		expectedBlock = new Block<>(Arrays.asList(product2), false);
		assertEquals(expectedBlock, advertService.findAdvertisedProducts(user2.getId(), 0, 3));

	}

	@Test
	public void testBidUp() throws InstanceNotFoundException, AmountLessOrEqualThanActualPriceException, OutOfTimeException, InvalidUserException {

		User user1 = signUpUser("Pepe");
		User user2 = signUpUser("Jose");
		User user3 = signUpUser("Carlos");
		Category category = new Category("category");

		categoryDao.save(category);
		Product product1 = advertService.addProduct(user1.getId(), "Reloj", "Reloj digital", (long) 200,
				new BigDecimal(5.0), "Correo estandar", category.getId());
		advertService.bidUp(user3.getId(), product1.getId(), new BigDecimal(6.0));
		Bid bid1 = advertService.bidUp(user2.getId(), product1.getId(), new BigDecimal(7.0));
		assertEquals(advertService.findAdvertisedProducts(user1.getId(), 0, 1).getItems().get(0).getMaxBid(),
				bid1);
		assertEquals(advertService.findAdvertisedProducts(user1.getId(), 0, 1).getItems().get(0).getActualPrice(),
				new BigDecimal(6.5).setScale(2, RoundingMode.HALF_UP));
	}
	
	@Test(expected = AmountLessOrEqualThanActualPriceException.class)
	public void testBidUpLessActualPrice() throws InstanceNotFoundException, AmountLessOrEqualThanActualPriceException, OutOfTimeException, InvalidUserException {

		User user1 = signUpUser("Pepe");
		User user2 = signUpUser("Jose");
		Category category = new Category("category");

		categoryDao.save(category);
		Product product1 = advertService.addProduct(user1.getId(), "Reloj", "Reloj digital", (long) 200,
				new BigDecimal(5.0), "Correo estandar", category.getId());
		advertService.bidUp(user2.getId(), product1.getId(), new BigDecimal(4.0));
	}
	
	@Test(expected = AmountLessOrEqualThanActualPriceException.class)
	public void testBidUpEqualActualPrice() throws InstanceNotFoundException, AmountLessOrEqualThanActualPriceException, OutOfTimeException, InvalidUserException {

		User user1 = signUpUser("Pepe");
		User user2 = signUpUser("Jose");
		Category category = new Category("category");

		categoryDao.save(category);
		Product product1 = advertService.addProduct(user1.getId(), "Reloj", "Reloj digital", (long) 200,
				new BigDecimal(5.0), "Correo estandar", category.getId());
		advertService.bidUp(user2.getId(), product1.getId(), new BigDecimal(7.0));
		advertService.bidUp(user2.getId(), product1.getId(), new BigDecimal(5.0));
	}
	
	@Test(expected = OutOfTimeException.class)
	public void testBidUpOutOfTime() throws InstanceNotFoundException, AmountLessOrEqualThanActualPriceException, OutOfTimeException, InvalidUserException {

		User user1 = signUpUser("Pepe");
		User user2 = signUpUser("Jose");
		Category category = new Category("category");

		categoryDao.save(category);
		Product product1 = advertService.addProduct(user1.getId(), "Reloj", "Reloj digital", (long) -200,
				new BigDecimal(5.0), "Correo estandar", category.getId());
		advertService.bidUp(user2.getId(), product1.getId(), new BigDecimal(4.0));
	}
	
	@Test
	public void testBidExample1() throws InstanceNotFoundException, AmountLessOrEqualThanActualPriceException, OutOfTimeException, InvalidUserException {
		User vendedor = signUpUser("Pepe");
		User comprador = signUpUser("Jose");
		Bid bid;
		Category category = new Category("category");
		
		categoryDao.save(category);
		Product product1 = advertService.addProduct(vendedor.getId(), "Reloj", "Reloj digital", (long) 200,
				new BigDecimal(10.0), "Correo estandar", category.getId());
		bid = advertService.bidUp(comprador.getId(), product1.getId(), new BigDecimal(12.0));
		
		assertEquals(advertService.findAdvertisedProducts(vendedor.getId(), 0, 1).getItems().get(0).getMaxBid(),
				bid);
		assertEquals(advertService.findAdvertisedProducts(vendedor.getId(), 0, 1).getItems().get(0).getActualPrice(), 
				new BigDecimal(10.0).setScale(2, RoundingMode.HALF_UP));
		}
	
	@Test
	public void testBidExample2() throws InstanceNotFoundException, AmountLessOrEqualThanActualPriceException, OutOfTimeException, InvalidUserException {
		User vendedor = signUpUser("Pepe");
		User compradorA = signUpUser("Jose");
		User compradorB = signUpUser("Pedro");
		Bid bid;
		Category category = new Category("category");
		
		categoryDao.save(category);
		Product product1 = advertService.addProduct(vendedor.getId(), "Reloj", "Reloj digital", (long) 200,
				new BigDecimal(10.0), "Correo estandar", category.getId());
		
		bid = advertService.bidUp(compradorA.getId(), product1.getId(), new BigDecimal(12.0));
		
		advertService.bidUp(compradorB.getId(), product1.getId(), new BigDecimal(11.0));

		assertEquals(advertService.findAdvertisedProducts(vendedor.getId(), 0, 1).getItems().get(0).getMaxBid(),
				bid);
		assertEquals(advertService.findAdvertisedProducts(vendedor.getId(), 0, 1).getItems().get(0).getActualPrice(), 
				new BigDecimal(11.5).setScale(2, RoundingMode.HALF_UP));
		}
	
	@Test
	public void testBidExample3() throws InstanceNotFoundException, AmountLessOrEqualThanActualPriceException, OutOfTimeException, InvalidUserException {
		User vendedor = signUpUser("Pepe");
		User compradorA = signUpUser("Jose");
		User compradorB = signUpUser("Pedro");
		Bid bid;
		Category category = new Category("category");
		
		categoryDao.save(category);
		Product product1 = advertService.addProduct(vendedor.getId(), "Reloj", "Reloj digital", (long) 200,
				new BigDecimal(10.0), "Correo estandar", category.getId());
		
		advertService.bidUp(compradorA.getId(), product1.getId(), new BigDecimal(12.0));
		
		bid = advertService.bidUp(compradorB.getId(), product1.getId(), new BigDecimal(14.0));

		assertEquals(advertService.findAdvertisedProducts(vendedor.getId(), 0, 1).getItems().get(0).getMaxBid(),
				bid);
		assertEquals(advertService.findAdvertisedProducts(vendedor.getId(), 0, 1).getItems().get(0).getActualPrice(), 
				new BigDecimal(12.5).setScale(2, RoundingMode.HALF_UP));
		}
	
}
