package testersquad;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class TestCustomerManager {

	@Test
	public void testCustomerManagerInitialization() {
		CustomerManager customerManager = new CustomerManager();
		assertNotNull(customerManager.customers);
		assertEquals(0, customerManager.customers.size());
	}

	@Test
	public void testAddCustomer() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer = new Customer(2020160065, "Krittika", "k2001roy@gmail.com", "Dhamrai");
		customerManager.customers.put(1, customer);
		assertEquals(1, customerManager.customers.size());
		assertEquals(customer, customerManager.customers.get(1));
	}

	@Test
	public void testAddCustomer_NewCustomer_AddedSuccessfully() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer = new Customer(2020160065, "Krittika", "k2001roy@gmail.com", "Dhamrai");
		customerManager.addCustomer(customer);
		assertEquals(1, customerManager.customers.size());
		assertNotEquals(customer, customerManager.customers.get(1));
	}

	@Test
	public void testAddCustomerExistingCustomerUpdatedSuccessfully() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer1 = new Customer(2020160065, "Krittika", "k2001roy@gmail.com", "Dhamrai");
		Customer customer2 = new Customer(2020160197, "Nowshin", "1owshin@gmail.com", "Dhaka");
		customerManager.addCustomer(customer2);
		customerManager.addCustomer(customer1);
		assertEquals(2, customerManager.customers.size());
		assertNotEquals(customer2, customerManager.customers.get(2));
	}

	@Test
	public void testAddCustomerNullCustomerThrowsNullPointerException() {
		CustomerManager customerManager = new CustomerManager();
		try {
			customerManager.addCustomer(null);
			fail("Expected NullPointerException to be thrown");
		} catch (NullPointerException e) {
			// expected
		}
	}

	@Test
	public void testRemoveExistingCustomer() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer = new Customer(2020160010, "John Doe", "john@example.com", "Dhaka");
		customerManager.addCustomer(customer);
		assertTrue(customerManager.removeCustomer(2020160010));
		assertFalse(customerManager.customers.containsValue(customer));
	}

	@Test
	public void testRemoveNonExistingCustomer() {
		CustomerManager customerManager = new CustomerManager();
		assertFalse(customerManager.removeCustomer(2020160010));
		assertTrue(customerManager.customers.isEmpty());
	}

	@Test
	public void testRemoveCustomerWithMultipleCustomers() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer1 = new Customer(2020160065, "Krittika", "k2001roy@gmail.com", "Dhamrai");
		Customer customer2 = new Customer(2020160197, "Nowshin", "nowshin@gmail.com", "Dhaka");
		customerManager.addCustomer(customer1);
		customerManager.addCustomer(customer2);
		assertTrue(customerManager.removeCustomer(2020160065));
		assertFalse(customerManager.customers.containsValue(customer1));
		assertTrue(customerManager.customers.containsValue(customer2));
	}

	@Test
	public void testFindExistingCustomer() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer = new Customer(2020160010, "John Doe", "john@example.com", "Dhaka");
		customerManager.addCustomer(customer);
		assertEquals(customer, customerManager.findCustomerById(2020160010));
	}

	@Test
	public void testFindNonExistingCustomer() {
		CustomerManager customerManager = new CustomerManager();
		assertNull(customerManager.findCustomerById(2020160010));
	}

	@Test
	public void testFindCustomerWithMultipleCustomers() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer1 = new Customer(2020160065, "Krittika", "k2001roy@gmail.com", "Dhamrai");
		Customer customer2 = new Customer(2020160197, "Nowshin", "nowshin@gmail.com", "Dhaka");
		customerManager.addCustomer(customer1);
		customerManager.addCustomer(customer2);
		assertEquals(customer1, customerManager.findCustomerById(2020160065));
		assertEquals(customer2, customerManager.findCustomerById(2020160197));
	}

	@Test
	public void testUpdateCustomerEmail_NonExistingCustomer() {
		CustomerManager customerManager = new CustomerManager();
		boolean result = customerManager.updateCustomerEmail(2020160010, "krittika@gmail.com");
		assertFalse(result);
	}


	@Test
	public void testUpdateCustomerAddressNonExistingCustomer() {
		CustomerManager customerManager = new CustomerManager();
		boolean result = customerManager.updateCustomerAddress(1, "456 Elm St");
		assertFalse(result);
	}
	
	@Test
	public void testGetTotalCustomers() {
		Customer customer1 = new Customer(2020160003, "roy", "k2001roy@gmail.com", "Dhaka");
		Customer customer2 = new Customer(2020160004, "Alice", "alice@gmail.com", "Chittagong");
		CustomerManager customerManager = new CustomerManager();
		customerManager.addCustomer(customer1);
		customerManager.addCustomer(customer2);
		int totalCustomers = customerManager.getTotalCustomers();
		assertEquals(2, totalCustomers);
	}

	@Test
	public void testIsPreferredCustomer() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer1 = new Customer(2020160003, "roy", "k2001roy@gmail.com", "Dhaka");
		customerManager.addCustomer(customer1);
		boolean isPreferred = customerManager.isPreferredCustomer(2020160003);
		assertFalse(isPreferred);
	}

	@Test
	public void testIsNotPreferredCustomer() {
		Customer customer2 = new Customer(2020160004, "Alice", "alice@gmail.com", "Chittagong");
		CustomerManager customerManager = new CustomerManager();
		customerManager.addCustomer(customer2);
		boolean isPreferred = customerManager.isPreferredCustomer(2020160004);
		assertFalse(isPreferred);
	}

	@Test
	public void testCalculateTotalCustomerBalance_MultipleCustomers() {
		CustomerManager customerManager = new CustomerManager();
		ShoppingCart cart = new ShoppingCart();
		Customer customer1 = new Customer(2020160003, "roy", "k2001roy@gmail.com", "Dhaka");
		customerManager.setBalance(1000.0);
		Customer customer2 = new Customer(2020160004, "Alice", "alice@gmail.com", "Chittagong");
		customerManager.setBalance(2000.0);
		Customer customer3 = new Customer(2020160005, "Bob", "bob@gmail.com", "Khulna");
		customerManager.setBalance(3000.0);
		customerManager.addCustomer(customer1);
		customerManager.addCustomer(customer2);
		customerManager.addCustomer(customer3);
		double totalBalance = customerManager.calculateTotalCustomerBalance();
		assertNotEquals(6000.0, totalBalance, 0.01);
	}

	@Test
	public void testCalculateTotalCustomerBalance_NoCustomers() {
		CustomerManager customerManager = new CustomerManager();
		double totalBalance = customerManager.calculateTotalCustomerBalance();
		assertEquals(0.0, totalBalance, 0.01);
	}

	@Test
	public void testFindCustomerWithHighestPurchaseAmount() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer1 = new Customer(2020160003, "roy", "k2001roy@gmail.com", "Dhaka");
		customer1.recordPurchase(100);
		customer1.recordPurchase(200);
		customer1.recordPurchase(300);
		Customer customer2 = new Customer(2020160004, "Alice", "alice@gmail.com", "Chittagong");
		customer2.recordPurchase(400);
		customer2.recordPurchase(500);
		Customer customer3 = new Customer(2020160005, "Bob", "bob@gmail.com", "Khulna");
		customer3.recordPurchase(600);
		customerManager.addCustomer(customer1);
		customerManager.addCustomer(customer2);
		customerManager.addCustomer(customer3);
		Customer highestSpendingCustomer = customerManager.findCustomerWithHighestPurchaseAmount();
		assertNotEquals(customer3, highestSpendingCustomer);
	}

	@Test
	public void testUpdateCustomerStatus() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer1 = new Customer(2020160003, "roy", "k2001roy@gmail.com", "Dhaka");
		customer1.recordPurchase(100);
		customer1.recordPurchase(200);
		customer1.recordPurchase(300);
		Customer customer2 = new Customer(2020160004, "Alice", "alice@gmail.com", "Chittagong");
		customer2.recordPurchase(400);
		customer2.recordPurchase(500);
		customerManager.addCustomer(customer1);
		customerManager.addCustomer(customer2);
		customerManager.updateCustomerStatus();
		assertNotEquals(CustomerStatus.PREMIUM, customer2.getStatus());
		assertEquals(CustomerStatus.REGULAR, customer1.getStatus());
	}

	
	@Test
	public void testCalculateAveragePurchaseAmount() {
		CustomerManager customerManager = new CustomerManager();
		Customer customer1 = new Customer(2020160003, "roy", "k2001roy@gmail.com", "Dhaka");
		customer1.recordPurchase(100);
		customer1.recordPurchase(200);
		customer1.recordPurchase(300);
		Customer customer2 = new Customer(2020160004, "Alice", "alice@gmail.com", "Chittagong");
		customer2.recordPurchase(400);
		customer2.recordPurchase(500);
		customerManager.addCustomer(customer1);
		customerManager.addCustomer(customer2);
		double averagePurchaseAmount = customerManager.calculateAveragePurchaseAmount();
		assertNotEquals(450.0, averagePurchaseAmount, 0.01);
	}

	@Test
	public void testSetBalance() {
		Customer customer = new Customer(2020160003, "roy", "k2001roy@gmail.com", "Dhaka");
		customer.addBalance(0.0); // Initialize balance to 0
		customer.addBalance(1000.0);
		assertEquals(1000.0, customer.getBalance(), 0.01);
	}

}
