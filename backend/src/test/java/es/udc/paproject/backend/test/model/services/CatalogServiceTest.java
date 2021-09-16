package es.udc.paproject.backend.test.model.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
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
import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.CategoryDao;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.entities.ProductDao;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.CatalogService;
import es.udc.paproject.backend.model.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CatalogServiceTest {

	private final Long NON_EXISTENT_ID = new Long(-1);

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private UserService userService;

	private User signUpUser(String userName) {

		User user = new User(userName, "password", "firstName", "lastName", userName + "@" + userName + ".com");

		try {
			userService.signUp(user);
		} catch (DuplicateInstanceException e) {
			throw new RuntimeException(e);
		}

		return user;

	}

	private Product createProduct(User user, String name, String description, Long duration, BigDecimal price,
			String shipmentInfo, Category category) {
		return new Product(user, name, description, duration, price, shipmentInfo, category);
	}

	@Test
	public void testFindAllCategories() {

		Category category1 = new Category("category1");
		Category category2 = new Category("category2");

		categoryDao.save(category2);
		categoryDao.save(category1);

		assertEquals(Arrays.asList(category1, category2), catalogService.findAllCategories());

	}

	@Test
	public void testFindProductById() throws InstanceNotFoundException {

		Category category = new Category("category");
		User user = signUpUser("Pepe");
		Product product = createProduct(user, "Reloj", "Reloj digital", (long) 200, new BigDecimal(5.0),
				"Correo estandar", category);

		categoryDao.save(category);
		productDao.save(product);

		assertEquals(product, catalogService.findProductById(product.getId()));

	}

	@Test(expected = InstanceNotFoundException.class)
	public void testFindProductByNonExistentId() throws InstanceNotFoundException {
		catalogService.findProductById(NON_EXISTENT_ID);
	}

	@Test
	public void testFindProductsByKeywords() {

		Category category = new Category("category");
		User user1 = signUpUser("Pepe");
		Product product1 = createProduct(user1, "product 1", "product number 1", (long) 100, new BigDecimal(11.0),
				"Correo estandar", category);
		User user2 = signUpUser("Jose");
		Product product2 = createProduct(user2, "X product", "x", (long) 150, new BigDecimal(7.0), "Correo estandar",
				category);
		User user3 = signUpUser("Maria");
		Product product3 = createProduct(user3, "another", "another product", (long) 200, new BigDecimal(5.0),
				"Correo estandar", category);

		categoryDao.save(category);
		productDao.save(product1);
		productDao.save(product2);
		productDao.save(product3);

		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1, product2), false);

		assertEquals(expectedBlock, catalogService.findProducts(null, "PrOd", 0, 2));

	}

	@Test
	public void testFindProductsByCategory() {

		Category category1 = new Category("category1");
		Category category2 = new Category("category2");

		User user1 = signUpUser("Pepe");
		Product product1 = createProduct(user1, "product 1", "product number 1", (long) 100, new BigDecimal(11.0),
				"Correo estandar", category1);
		User user2 = signUpUser("Jose");
		Product product2 = createProduct(user2, "product 2", "x", (long) 150, new BigDecimal(7.0), "Correo estandar",
				category2);

		categoryDao.save(category1);
		categoryDao.save(category2);
		productDao.save(product1);
		productDao.save(product2);

		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1), false);

		assertEquals(expectedBlock, catalogService.findProducts(category1.getId(), null, 0, 1));

	}

	@Test
	public void testFindProductsByAllCriteria() {

		Category category1 = new Category("category 1");
		User user1 = signUpUser("Pepe");
		Product product1 = createProduct(user1, "product 1", "product number 1", (long) 100, new BigDecimal(11.0),
				"Correo estandar", category1);
		User user2 = signUpUser("Jose");
		Product product2 = createProduct(user2, "another", "x", (long) 150, new BigDecimal(7.0), "Correo estandar",
				category1);
		Category category2 = new Category("category 2");
		Product product3 = createProduct(user2, "product 3", "product number 3", (long) 200, new BigDecimal(5.0),
				"Correo estandar", category2);
		categoryDao.save(category1);
		productDao.save(product1);
		productDao.save(product2);
		categoryDao.save(category2);
		productDao.save(product3);

		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1), false);

		assertEquals(expectedBlock, catalogService.findProducts(category1.getId(), "Prod 1", 0, 2));

	}

	@Test
	public void testFindAllProducts() {

		Category category1 = new Category("category 1");
		User user1 = signUpUser("Pepe");
		Product product1 = createProduct(user1, "product 1", "product number 1", (long) 100, new BigDecimal(11.0),
				"Correo estandar", category1);
		Category category2 = new Category("category 2");
		User user2 = signUpUser("Jose");
		Product product2 = createProduct(user2, "product 2", "product number 2", (long) 150, new BigDecimal(7.0),
				"Correo estandar", category2);

		categoryDao.save(category1);
		productDao.save(product1);
		categoryDao.save(category2);
		productDao.save(product2);

		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1, product2), false);

		assertEquals(expectedBlock, catalogService.findProducts(null, "", 0, 2));
		assertEquals(expectedBlock, catalogService.findProducts(null, null, 0, 2));

	}

	@Test
	public void testFindNoProducts() {

		Category category = new Category("category");
		User user1 = signUpUser("Pepe");
		Product product = createProduct(user1, "product", "a product", (long) 100, new BigDecimal(11.0),
				"Correo estandar", category);

		categoryDao.save(category);
		productDao.save(product);

		Block<Product> expectedBlock = new Block<>(new ArrayList<>(), false);

		assertEquals(expectedBlock, catalogService.findProducts(null, "non-existent", 0, 1));

	}

	@Test
	public void testFindProductsByPages() {

		Category category = new Category("category");
		User user1 = signUpUser("Pepe");
		Product product1 = createProduct(user1, "product 1", "product number 1", (long) 100, new BigDecimal(11.0),
				"Correo estandar", category);
		User user2 = signUpUser("Jose");
		Product product2 = createProduct(user2, "product 2", "product number 2", (long) 150, new BigDecimal(7.0),
				"Correo estandar", category);
		User user3 = signUpUser("Maria");
		Product product3 = createProduct(user3, "product 3", "product number 3", (long) 200, new BigDecimal(5.0),
				"Correo estandar", category);

		categoryDao.save(category);
		productDao.save(product1);
		productDao.save(product2);
		productDao.save(product3);

		Block<Product> expectedBlock = new Block<>(Arrays.asList(product1, product2), true);
		assertEquals(expectedBlock, catalogService.findProducts(null, null, 0, 2));

		expectedBlock = new Block<>(Arrays.asList(product3), false);
		assertEquals(expectedBlock, catalogService.findProducts(null, null, 1, 2));

		expectedBlock = new Block<>(new ArrayList<>(), false);
		assertEquals(expectedBlock, catalogService.findProducts(null, null, 2, 2));

	}

}
