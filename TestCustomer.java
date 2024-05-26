package testersquad;

import java.time.LocalDate;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.time.LocalDate;

public class TestCustomer {
	@Test
	public void testCustomer() {
		Customer customer = new Customer(2020160065, "Krittika", "k2001roy@gmail.com", "Dhamrai");
		assertEquals(2020160065, customer.getId());
		assertEquals("k2001roy@gmail.com", customer.getEmail());
		assertEquals("Dhamrai", customer.getAddress());

	}

	@Test
	public void testCustomer1() {
		Customer customer = new Customer(2020160197, "Nowshin", "1owshin@gmail.com", "Dhaka");
		assertEquals(2020160197, customer.getId());
		assertEquals("1owshin@gmail.com", customer.getEmail());
		assertEquals("Dhaka", customer.getAddress());

	}

	@Test
	public void testCustomer2() {
		Customer customer = new Customer(2020160001, "Roy", "roykrittika@gmail.com", "Manikganj");
		assertEquals(2020160001, customer.getId());
		assertEquals("roykrittika@gmail.com", customer.getEmail());
		assertEquals("Manikganj", customer.getAddress());

	}

	public void testEmptyAddress() {
		boolean exceptionThrown = false;
		try {
			Customer customer = new Customer(2020160004, "krittika2", "k2001roy@gmail.com", "");
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue("Expected IllegalArgumentException for empty Address", exceptionThrown);
	}

	@Test
	public void testIDUpdate() {
		Customer customer = new Customer(2020160004, "Alice", "alice@gmail.com", "Chittagong");
		customer.setId(2020160066);
		assertEquals(2020160066, customer.getId());
	}

	@Test
	public void testEmailUpdate() {
		Customer customer = new Customer(2020160004, "Alice", "alice@gmail.com", "Chittagong");
		customer.setEmail("krittikaroy2020@gmail.com");
		assertEquals("krittikaroy2020@gmail.com", customer.getEmail());
	}

	@Test
	public void testAddressUpdate() {
		Customer customer = new Customer(2020160004, "Alice", "alice@gmail.com", "Chittagong");
		customer.setAddress("Comilla");
		assertEquals("Comilla", customer.getAddress());
	}

	@Test
	public void testIdMismatch() {
		Customer customer = new Customer(2020160005, "John", "john@gmail.com", "Sylhet");
		assertNotSame(2020160197, customer.getId());
	}

	@Test
	public void testEmailMismatch() {
		Customer customer = new Customer(2020160005, "John", "john@gmail.com", "Sylhet");
		assertNotSame("k2001roy@gmail.com", customer.getEmail());
	}

	@Test
	public void testAdressMismatch() {
		Customer customer = new Customer(2020160005, "John", "john@gmail.com", "Sylhet");
		assertNotSame("Dhaka", customer.getAddress());
	}

	@Test
	public void testValidPurchase() {
		Customer customer = new Customer(2020160001, "Alice", "alice@gmail.com", "Chittagong");
		Product product = new Product(1, "Laptop", 1000, 1000, "hp", LocalDate.of(2024, 3, 1));
		customer.purchaseItem(product, 2);
		assertEquals(2, (int) customer.getPurchasedItems().get(product));
	}

	@Test
	public void testExistingProductUpdate() {
		Customer customer = new Customer(2020160002, "Bob", "bob@gmail.com", "Sylhet");
		Product product = new Product(2, "Phone", 400.55, 500, "oppo", LocalDate.of(2024, 3, 2));
		customer.purchaseItem(product, 3);
		customer.purchaseItem(product, 2);
		assertEquals(5, (int) customer.getPurchasedItems().get(product));
	}

	@Test
	public void testNewProductPurchase() {
		Customer customer = new Customer(2020160003, "Charlie", "charlie@gmail.com", "Sylhet");
		Product product = new Product(3, "Tablet", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		customer.purchaseItem(product, 1);
		assertEquals(1, (int) customer.getPurchasedItems().get(product));
	}


	@Test
	public void testNonExistingProductPurchase() {
		Customer customer = new Customer(2020160006, "Eve", "eve@gmail.com", "Sylhet");
		Product product = new Product(3, "tablet", 2000.55, 400, "oppo", LocalDate.of(2024, 1, 1));
		customer.purchaseItem(product, 2);
		assertEquals(2, (int) customer.getPurchasedItems().get(product));
	}

	@Test
	public void testApplyDiscountLessThan10Items() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 4, 1));
		cart.addItem(product, 5);
		cart.applyDiscount(0); // no discount
		double actualPrice = customer.getBalance();
		double expectedPrice = 2000.55 * 5;
		assertNotEquals(expectedPrice, actualPrice, 0.01);
	}

	@Test
	public void testApplyDiscountExactly10Items() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 9, 1));
		cart.addItem(product, 10);
		cart.applyDiscount(10); // 10% discount
		double actualPrice = cart.calculateTotalPrice();
		double expectedPrice = 2000.55 * 10 * 0.9;
		assertNotEquals(expectedPrice, actualPrice, 0.01);
	}

	@Test
	public void testApplyDiscountMoreThan10Items() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 10, 1));
		cart.addItem(product, 15);
		cart.applyDiscount(10); // 10% discount
		double actualPrice = cart.calculateTotalPrice();
		double expectedPrice = 2000.55 * 15 * 0.9;
		assertNotEquals(expectedPrice, actualPrice, 0.01);
	}

	@Test
	public void testAddItemToCart() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 5, 1));
		cart.addItem(product, 5);
		assertNotEquals(Integer.valueOf(5), customer.getPurchasedItems().get(product));
	}

	@Test
	public void testReturnItemWithEnoughQuantityInStock() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		cart.addItem(product, 10);
		customer.returnItem(product, 5);
		assertNotEquals(Integer.valueOf(5), customer.getPurchasedItems().get(product));
	}

	@Test
	public void testReturnItemWithExactQuantityInStock() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		cart.addItem(product, 10);
		customer.returnItem(product, 10);
		assertTrue(customer.getPurchasedItems().isEmpty());
	}

	@Test
	public void testCalculateTotalPriceInCart() {
		ShoppingCart cart = new ShoppingCart();
		Product product = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		cart.addItem(product, 5);
		double price = product.getPrice();
		assertEquals(price * 5, cart.calculateTotalPrice(), 0.01);
	}

	@Test
	public void testGetTotalItemsPurchased() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		Product product2 = new Product(5, "Smartphone", 50000.00, 200, "vivo", LocalDate.of(2026, 5, 1));
		cart.addItem(product1, 2);
		cart.addItem(product2, 1);
		int totalItemsPurchased = customer.getTotalItemsPurchased();
		assertNotEquals(3, totalItemsPurchased);
	}

	@Test
	public void testGetTotalAmountSpent() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product1 = new Product(4, "Headphones", 2000, 4, "oppo", LocalDate.of(2024, 5, 1));
		Product product2 = new Product(5, "Smartphone", 5000, 2, "vivo", LocalDate.of(2026, 5, 1));
		cart.addItem(product1, 2);
		cart.addItem(product2, 1);
		double totalAmountSpent = customer.getTotalAmountSpent();
		assertNotEquals(9000, totalAmountSpent, 0.0);
	}

	@Test
	public void testHasPurchasedItem() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		cart.addItem(product1, 2);
		boolean hasPurchased = customer.hasPurchasedItem(product1);
		assertFalse(hasPurchased);
	}

	@Test
	public void testClearAllPurchasedItems() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		Product product2 = new Product(5, "Smartphone", 50000.00, 200, "vivo", LocalDate.of(2026, 5, 1));
		cart.addItem(product1, 2);
		cart.addItem(product2, 1);
		customer.clearAllPurchasedItems();
		assertTrue(customer.getPurchasedItems().isEmpty());
	}

	@Test
	public void testHasPurchasedMultipleItems() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		Product product2 = new Product(5, "Smartphone", 50000.00, 200, "vivo", LocalDate.of(2026, 5, 1));
		cart.addItem(product1, 2);
		cart.addItem(product2, 1);
		boolean hasPurchasedMultipleItems = customer.hasPurchasedMultipleItems();
		assertFalse(hasPurchasedMultipleItems);
	}

	@Test
	public void testIsFrequentShopper() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		Product product2 = new Product(5, "Smartphone", 50000.00, 200, "oppo", LocalDate.of(2026, 5, 1));
		Product product3 = new Product(1, "Laptop", 100000.00, 100, "hp", LocalDate.of(2025, 5, 4));
		Product product4 = new Product(6, "TV", 200000.00, 50, "waltone", LocalDate.of(2027, 5, 3));
		Product product5 = new Product(7, "Watch", 5000.00, 500, "titan", LocalDate.of(2024, 6, 2));
		cart.addItem(product1, 2);
		cart.addItem(product2, 1);
		cart.addItem(product3, 1);
		cart.addItem(product4, 1);
		cart.addItem(product5, 1);
		boolean isFrequentShopper = customer.isFrequentShopper();
		assertFalse(isFrequentShopper);
	}

	@Test
	public void testCalculateAveragePurchaseQuantity() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		Product product2 = new Product(5, "Smartphone", 50000.00, 200, "vivo", LocalDate.of(2026, 5, 1));
		cart.addItem(product1, 2);
		cart.addItem(product2, 1);
		double averagePurchaseQuantity = customer.calculateAveragePurchaseQuantity();
		assertNotEquals(1.67, averagePurchaseQuantity, 0.01);
	}

	@Test
	public void testHasHighSpending() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		Product product2 = new Product(5, "Smartphone", 50000.00, 200, "vivo", LocalDate.of(2026, 5, 1));
		cart.addItem(product1, 2);
		cart.addItem(product2, 1);
		boolean hasHighSpending = customer.hasHighSpending();
		assertFalse(hasHighSpending);
	}

	@Test
	public void testHasNotHighSpending() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		Product product2 = new Product(5, "Smartphone", 50000.00, 200, "vivo", LocalDate.of(2026, 5, 1));
		cart.addItem(product1, 2);
		cart.addItem(product2, 1);
		boolean hasHighSpending = customer.hasHighSpending();
		assertFalse(hasHighSpending);
	}

	@Test
	public void testGetTotalPurchases() {
		ShoppingCart cart = new ShoppingCart();
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2024, 5, 1));
		Product product2 = new Product(5, "Smartphone", 50000.00, 200, "vivo", LocalDate.of(2026, 5, 1));
		cart.addItem(product1, 2);
		cart.addItem(product2, 1);
		double totalPurchases = customer.getTotalPurchases();
		assertNotEquals(60002.0, totalPurchases, 0.01);
	}

	@Test
	public void testGetStatus() {
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		CustomerStatus status = CustomerStatus.INACTIVE;
		customer.setStatus(status);
		CustomerStatus actualStatus = customer.getStatus();
		assertEquals(status, actualStatus);
	}

	@Test
	public void testAddBalance() {
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		double initialBalance = customer.getBalance();
		double amountToAdd = 100.0;
		customer.addBalance(amountToAdd);
		double finalBalance = customer.getBalance();
		assertEquals(initialBalance + amountToAdd, finalBalance, 0.01);
	}

	@Test
	public void testRemoveBalance() {
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		double initialBalance = customer.getBalance();
		double amountToRemove = 50.0;
		customer.removeBalance(amountToRemove);
		double finalBalance = customer.getBalance();
		double delta = 0.01; // allow for small floating point errors
		assertNotEquals(initialBalance - amountToRemove, finalBalance, delta);
	}

	@Test
	public void testIsActive() {
		Customer customer = new Customer(2020160005, "David", "david@gmail.com", "Sylhet");
		CustomerStatus status = CustomerStatus.ACTIVE;
		customer.setStatus(status);
		boolean isActive = customer.isActive(true);
		assertTrue(isActive);
	}
}
