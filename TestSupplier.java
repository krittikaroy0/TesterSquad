package testersquad;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

public class TestSupplier {
	@Test
	public void testConstructor_ValidName() {
		String name = "books";
		Supplier supplier = new Supplier(name);
		assertTrue(supplier.getProductsSupplied().isEmpty());
	}

	@Test
	public void testConstructorSpecialCharacters() {
		String nameWithSpecialChars = "books#CSE_430";
		Supplier supplier = new Supplier(nameWithSpecialChars);
		assertTrue(supplier.getProductsSupplied().isEmpty());
	}

//---------------------------------------------------------------------------------1
	@Test
	public void testAddProductValid() {
		Supplier supplier = new Supplier("Gadget Supplier");
		Product product = new Product(1, "Phone Case", 5.99, 20, "Electronics", LocalDate.now().plusMonths(6));
		supplier.addProduct(product);
		assertEquals(1, supplier.getTotalProductsSupplied());
		assertTrue(supplier.getProductsSupplied().contains(product));
	}

	@Test
	public void testAddProductDuplicate() {
		Supplier supplier = new Supplier("Bookstore");
		Product book = new Product(2, "Novel", 12.95, 5, "Fiction", LocalDate.now().plusYears(2));
		supplier.addProduct(book);
		supplier.addProduct(book);
		assertEquals(2, supplier.getTotalProductsSupplied());
		assertTrue(supplier.getProductsSupplied().contains(book));
	}

	@Test
	public void testAddProductDifferentQuantities() {
		Supplier supplier = new Supplier("Clothing Store");
		Product shirt = new Product(3, "T-Shirt", 19.99, 10, "Clothing", LocalDate.now().plusMonths(3));
		Product pants = new Product(4, "Jeans", 34.50, 5, "Clothing", LocalDate.now().plusYears(1));
		supplier.addProduct(shirt);
		supplier.addProduct(pants);
		assertEquals(2, supplier.getTotalProductsSupplied());

	}

	@Test
	public void testAddProductModifications() {
		Supplier supplier = new Supplier("Grocery Store");
		Product apple = new Product(5, "Apple", 1.25, 15, "Produce", LocalDate.now().plusWeeks(2));

		supplier.addProduct(apple);
		supplier.getProductsSupplied().clear();
		supplier.addProduct(new Product(6, "Banana", 0.79, 20, "Produce", LocalDate.now().plusMonths(1)));
	}

//---------------------------------------------------------------------------------2
	@Test
	public void testRemoveProductExisting() {
		Supplier supplier = new Supplier("Furniture Store");
		Product chair = new Product(1, "Armchair", 199.99, 2, "Furniture", LocalDate.now().plusYears(5));
		supplier.addProduct(chair);
		assertTrue(supplier.removeProduct(chair));
		assertEquals(0, supplier.getTotalProductsSupplied());
		assertFalse(supplier.getProductsSupplied().contains(chair));
	}

	@Test
	public void testRemoveProductNonExisting() {
		Supplier supplier = new Supplier("Toy Store");
		Product toyCar = new Product(2, "Remote Control Car", 29.95, 1, "Toys", LocalDate.now().plusMonths(6));
		Product toyTrain = new Product(3, "Electric Train Set", 49.99, 1, "Toys", LocalDate.now().plusYears(2));
		supplier.addProduct(toyCar);
		assertFalse(supplier.removeProduct(toyTrain));
		assertEquals(1, supplier.getTotalProductsSupplied());
	}

	@Test
	public void testRemoveProductDifferentObject() {
		Supplier supplier = new Supplier("Music Store");
		Product guitar1 = new Product(4, "Electric Guitar", 399.00, 1, "Instruments", LocalDate.now().plusYears(10));
		supplier.addProduct(guitar1);

		Product guitar2 = new Product(5, "Electric Guitar", 399.00, 1, "Instruments", LocalDate.now().plusYears(10));
		assertFalse(supplier.removeProduct(guitar2));
		assertEquals(1, supplier.getTotalProductsSupplied());
	}

	@Test
	public void testRemoveProduct_AllAndCheckEmpty() {
		Supplier supplier = new Supplier("Sporting Goods Store");
		Product basketball = new Product(8, "Basketball", 14.99, 10, "Sports", LocalDate.now().plusMonths(4));
		Product soccerBall = new Product(9, "Soccer Ball", 19.99, 5, "Sports", LocalDate.now().plusYears(1));

		supplier.addProduct(basketball);
		supplier.addProduct(soccerBall);

		supplier.removeProduct(basketball);
		supplier.removeProduct(soccerBall);

		assertEquals(0, supplier.getTotalProductsSupplied());
		assertTrue(supplier.getProductsSupplied().isEmpty());
	}

//---------------------------------------------------------------------------------3
	@Test
	public void testUpdateProductPriceIncrease() {
		Supplier supplier = new Supplier("Electronics Store");
		Product phone = new Product(1, "Smartphone", 499.99, 2, "Electronics", LocalDate.now().plusYears(2));
		supplier.addProduct(phone);
		double newPrice = 549.99;
		supplier.updateProductPrice(phone, newPrice);
		assertEquals(newPrice, phone.getPrice(), 0.001);
		assertTrue(supplier.getProductsSupplied().contains(phone));
	}

	@Test
	public void testUpdateProductPriceDecrease() {
		Supplier supplier = new Supplier("Clothing Store");
		Product shirt = new Product(2, "T-Shirt", 19.99, 5, "Clothing", LocalDate.now().plusMonths(6));
		supplier.addProduct(shirt);
		double newPrice = 14.99;
		supplier.updateProductPrice(shirt, newPrice);
		assertEquals(newPrice, shirt.getPrice(), 0.001);
		assertTrue(supplier.getProductsSupplied().contains(shirt));
	}

	@Test
	public void testUpdateProductPriceNoChange() {
		Supplier supplier = new Supplier("Bookstore");
		Product novel = new Product(3, "Adventure Novel", 12.50, 3, "Fiction", LocalDate.now().plusYears(5)); // Assuming
																												// price
																												// setter
																												// works
		supplier.addProduct(novel);
		double currentPrice = novel.getPrice();
		supplier.updateProductPrice(novel, currentPrice);
		assertEquals(currentPrice, novel.getPrice(), 0.001);
	}

	@Test
	public void testUpdateProductPriceMultiple() {
		Supplier supplier = new Supplier("Grocery Store");
		Product apple = new Product(5, "Apple", 1.25, 10, "Produce", LocalDate.now().plusWeeks(3));
		Product banana = new Product(6, "Banana", 0.79, 15, "Produce", LocalDate.now().plusMonths(2));
		supplier.addProduct(apple);
		supplier.addProduct(banana);
		double newApplePrice = 1.49;
		double newBananaPrice = 0.69;
		supplier.updateProductPrice(apple, newApplePrice);
		supplier.updateProductPrice(banana, newBananaPrice);
		assertEquals(newApplePrice, apple.getPrice(), 0.001);
	}

//---------------------------------------------------------------------------------4

//---------------------------------------------------------------------------------5
	@Test
	public void testGetProductsSupplied() {
		Supplier supplier = new Supplier("Appliance Store");
		Product fridge = new Product(1, "Refrigerator", 599.99, 1, "Appliances", LocalDate.now().plusYears(2));
		Product oven = new Product(2, "Electric Oven", 249.99, 2, "Appliances", LocalDate.now().plusMonths(6));
		supplier.addProduct(fridge);
		supplier.addProduct(oven);
		List<Product> returnedList = supplier.getProductsSupplied();
		assertEquals(2, returnedList.size());
		returnedList.add(new Product(3, "Microwave", 99.99, 1, "Appliances", LocalDate.now().plusWeeks(1)));
		assertEquals(3, supplier.getProductsSupplied().size());
	}

	@Test
	public void testGetProductsSupplied2() {
		Supplier supplier = new Supplier("Clothing Store");
		Product shirt = new Product(3, "T-Shirt", 19.99, 5, "Clothing", LocalDate.now().plusMonths(6));
		Product pants = new Product(4, "Jeans", 34.50, 1, "Clothing", LocalDate.now().plusYears(1));
		supplier.addProduct(shirt);
		supplier.addProduct(pants);
		List<Product> returnedList = supplier.getProductsSupplied();
		assertTrue(returnedList.contains(shirt));
		assertTrue(returnedList.contains(pants));
	}

	@Test
	public void testGetProductsSuppliedEmpty() {
		Supplier supplier = new Supplier("Office Supplies Store");
		List<Product> returnedList = supplier.getProductsSupplied();
		assertEquals(0, returnedList.size());
	}

	@Test
	public void testGetProductsSuppliedModifications() {
		Supplier supplier = new Supplier("Music Store");
		Product guitar = new Product(5, "Electric Guitar", 399.00, 1, "Instruments", LocalDate.now().plusYears(10));
		supplier.addProduct(guitar);
		List<Product> returnedList = supplier.getProductsSupplied();
		supplier.addProduct(new Product(6, "Acoustic Guitar", 299.00, 1, "Instruments", LocalDate.now().plusYears(8)));
		assertEquals(2, returnedList.size());
	}

	@Test
	public void testGetProductsSuppliedMultipleCalls() {
		Supplier supplier = new Supplier("Grocery Store");
		Product apple = new Product(6, "Apple", 1.25, 10, "Produce", LocalDate.now().plusWeeks(2));
		supplier.addProduct(apple);
		List<Product> firstList = supplier.getProductsSupplied();
		List<Product> secondList = supplier.getProductsSupplied();
		assertSame(firstList, secondList);
	}

//---------------------------------------------------------------------------------6
	@Test
	public void testGetTotalProductsSupplied() {
		Supplier supplier = new Supplier("Bookstore");
		Product novel = new Product(1, "Adventure Novel", 12.50, 3, "Fiction", LocalDate.now().plusYears(5));
		Product textbook = new Product(2, "Science Textbook", 49.99, 2, "Education", LocalDate.now().plusMonths(2));
		supplier.addProduct(novel);
		supplier.addProduct(textbook);
		int expectedTotalProducts = 2;
		assertEquals(expectedTotalProducts, supplier.getTotalProductsSupplied());
	}

	@Test
	public void testGetTotalProductsSuppliedEmpty() {
		Supplier supplier = new Supplier("Toy Store");
		assertEquals(0, supplier.getTotalProductsSupplied());
	}

	@Test
	public void testGetTotalProductsSuppliedAddRemove() {
		Supplier supplier = new Supplier("Clothing Store");
		Product shirt = new Product(3, "T-Shirt", 19.99, 5, "Clothing", LocalDate.now().plusMonths(6));
		supplier.addProduct(shirt);
		int expectedTotalAfterAdd = 1;
		assertEquals(expectedTotalAfterAdd, supplier.getTotalProductsSupplied());
		supplier.removeProduct(shirt);
		int expectedTotalAfterRemove = 0;
		assertEquals(expectedTotalAfterRemove, supplier.getTotalProductsSupplied());
	}

	@Test
	public void testGetTotalProductsSuppliedMultipleCalls() {
		Supplier supplier = new Supplier("Grocery Store");
		Product apple = new Product(4, "Apple", 1.25, 10, "Produce", LocalDate.now().plusWeeks(2));
		supplier.addProduct(apple);
		int expectedTotal = 1;
		assertEquals(expectedTotal, supplier.getTotalProductsSupplied());
		assertEquals(expectedTotal, supplier.getTotalProductsSupplied());
	}

	@Test
	public void testGetTotalProductsSupplied_AddingAfterGet() {
		Supplier supplier = new Supplier("Furniture Store");
		Product chair = new Product(5, "Armchair", 199.99, 2, "Furniture", LocalDate.now().plusYears(5));
		supplier.addProduct(chair);
		assertEquals(1, supplier.getTotalProductsSupplied());
	}

//---------------------------------------------------------------------------------7
	@Test
	public void testClearAllProducts() {
		Supplier supplier = new Supplier("Appliance Store");
		Product fridge = new Product(1, "Refrigerator", 599.99, 1, "Appliances", LocalDate.now().plusYears(2));
		Product oven = new Product(2, "Electric Oven", 249.99, 2, "Appliances", LocalDate.now().plusMonths(6));
		supplier.addProduct(fridge);
		supplier.addProduct(oven);
		supplier.clearAllProducts();
		assertEquals(0, supplier.getProductsSupplied().size());
		assertFalse(supplier.getProductsSupplied().contains(fridge));
		assertFalse(supplier.getProductsSupplied().contains(oven));
	}

	@Test
	public void testClearAllProductsEmpty() {
		Supplier supplier = new Supplier("Software Company");
		supplier.clearAllProducts();
		assertEquals(0, supplier.getProductsSupplied().size());
	}

	@Test
	public void testClearAllProductsMultipleCalls() {
		Supplier supplier = new Supplier("Clothing Store");
		Product shirt = new Product(3, "T-Shirt", 19.99, 5, "Clothing", LocalDate.now().plusMonths(6));
		supplier.addProduct(shirt);
		supplier.clearAllProducts();
		supplier.clearAllProducts();
		assertEquals(0, supplier.getProductsSupplied().size());
	}

	@Test
	public void testClearAllProductsAddingAfterClearing() {
		Supplier supplier = new Supplier("Office Supplies Store");
		Product pen = new Product(4, "Ballpoint Pen", 0.99, 20, "Office", LocalDate.now().plusYears(1));
		supplier.addProduct(pen);
		supplier.clearAllProducts();
		Product pencil = new Product(5, "HB Pencil", 0.75, 50, "Office", LocalDate.now().plusMonths(9));
		supplier.addProduct(pencil);
		assertEquals(1, supplier.getProductsSupplied().size());
		assertTrue(supplier.getProductsSupplied().contains(pencil));
	}

	@Test
	public void testClearAllProductsImpactOnOtherMethods() {
		Supplier supplier = new Supplier("Music Store");
		Product guitar = new Product(6, "Electric Guitar", 399.00, 1, "Instruments", LocalDate.now().plusYears(10));
		supplier.addProduct(guitar);
		supplier.clearAllProducts();
		assertFalse(supplier.hasProduct(guitar));
		assertEquals(0.0, supplier.getTotalInventoryValue(), 0.001);
	}

//---------------------------------------------------------------------------------8
	@Test
	public void testGetTotalInventoryValueMultipleProducts() {
		Supplier supplier = new Supplier("Grocery Store");
		Product apple = new Product(1, "Apple", 1.25, 10, "Produce", LocalDate.now().plusWeeks(2));
		Product banana = new Product(2, "Banana", 0.79, 15, "Produce", LocalDate.now().plusMonths(1));
		supplier.addProduct(apple);
		supplier.addProduct(banana);
		double expectedTotal = (apple.getPrice() * apple.getQuantity()) + (banana.getPrice() * banana.getQuantity());
		assertEquals(expectedTotal, supplier.getTotalInventoryValue(), 0.001);
	}

	@Test
	public void testGetTotalInventoryValueSingleProduct() {
		Supplier supplier = new Supplier("Clothing Store");
		Product shirt = new Product(3, "T-Shirt", 19.99, 5, "Clothing", LocalDate.now().plusMonths(6));
		supplier.addProduct(shirt);
		double expectedTotal = shirt.getPrice() * shirt.getQuantity();
		assertEquals(expectedTotal, supplier.getTotalInventoryValue(), 0.001);
	}

	@Test
	public void testGetTotalInventoryValueZero() {
		Supplier supplier = new Supplier("Bookstore");
		Product novel = new Product(4, "Mystery Novel", 12.50, 0, "Fiction", LocalDate.now().plusYears(5)); // Quantity
																											// is 0
		Product textbook = new Product(5, "Science Textbook", 49.99, 3, "Education", LocalDate.now().plusMonths(2));
		supplier.addProduct(novel);
		supplier.addProduct(textbook);
		double expectedTotal = textbook.getPrice() * textbook.getQuantity();
		assertEquals(expectedTotal, supplier.getTotalInventoryValue(), 0.001);
	}

	@Test
	public void testGetTotalInventoryValueEmpty() {
		Supplier supplier = new Supplier("Toy Store");
		assertEquals(0.0, supplier.getTotalInventoryValue(), 0.001);
	}

	@Test
	public void testGetTotalInventoryValueModifications() {
		Supplier supplier = new Supplier("Furniture Store");
		Product chair = new Product(6, "Armchair", 199.99, 2, "Furniture", LocalDate.now().plusYears(5));
		Product table = new Product(7, "Coffee Table", 99.99, 1, "Furniture", LocalDate.now().plusMonths(9));
		supplier.addProduct(chair);
		supplier.addProduct(table);
		double initialTotal = supplier.getTotalInventoryValue();
		supplier.removeProduct(table);
		assertEquals(initialTotal - (table.getPrice() * table.getQuantity()), supplier.getTotalInventoryValue(), 0.001);
	}

//---------------------------------------------------------------------------------9
	@Test
	public void testHasProductExisting() {
		Supplier supplier = new Supplier("Electronics Store");
		Product phone = new Product(1, "Smartphone", 499.99, 2, "Electronics", LocalDate.now().plusYears(2));
		supplier.addProduct(phone);
		assertTrue(supplier.hasProduct(phone));
	}

	@Test
	public void testHasProductNonExisting() {
		Supplier supplier = new Supplier("Clothing Store");
		Product shirt = new Product(2, "T-Shirt", 19.99, 5, "Clothing", LocalDate.now().plusMonths(6));
		Product pants = new Product(3, "Jeans", 34.50, 1, "Clothing", LocalDate.now().plusYears(1));
		supplier.addProduct(shirt);
		assertFalse(supplier.hasProduct(pants));
	}

	@Test
	public void testHasProductSameDataDifferentObject() {
		Supplier supplier = new Supplier("Music Store");
		Product guitar1 = new Product(4, "Electric Guitar", 399.00, 1, "Instruments", LocalDate.now().plusYears(10));
		supplier.addProduct(guitar1);
		Product guitar2 = new Product(5, "Electric Guitar", 399.00, 1, "Instruments", LocalDate.now().plusYears(10));
		assertFalse(supplier.hasProduct(guitar2));
	}

	@Test
	public void testHasProductEmpty() {
		Supplier supplier = new Supplier("Sporting Goods Store");
		Product basketball = new Product(8, "Basketball", 14.99, 10, "Sports", LocalDate.now().plusMonths(4));
		assertFalse(supplier.hasProduct(basketball));
	}

//---------------------------------------------------------------------------------10
	@Test
	public void testHasExpiredProductsNo() {
		Supplier supplier = new Supplier("Grocery Store");
		Product apple = new Product(1, "Apple", 1.25, 10, "Produce", LocalDate.now().plusWeeks(2));
		Product banana = new Product(2, "Banana", 0.79, 15, "Produce", LocalDate.now().plusMonths(1));
		supplier.addProduct(apple);
		supplier.addProduct(banana);
		assertFalse(supplier.hasExpiredProducts());
	}

	@Test
	public void testHasExpiredProductsYes() {
		Supplier supplier = new Supplier("Beverage Store");
		Product milk = new Product(3, "Milk", 2.99, 2, "Food", LocalDate.now().minusDays(5));
		Product juice = new Product(4, "Orange Juice", 3.49, 5, "Beverages", LocalDate.now().plusMonths(2));
		supplier.addProduct(milk);
		supplier.addProduct(juice);
		assertTrue(supplier.hasExpiredProducts());
	}

	@Test
	public void testHasExpiredProductsAllExpired() {
		Supplier supplier = new Supplier("Medicine Cabinet");
		Product aspirin = new Product(5, "Aspirin", 5.99, 10, "Medicine", LocalDate.now().minusMonths(2));
		Product bandaid = new Product(6, "Bandages", 3.99, 20, "Medical Supplies", LocalDate.now().minusWeeks(3));
		supplier.addProduct(aspirin);
		supplier.addProduct(bandaid);
		assertTrue(supplier.hasExpiredProducts());
	}

	@Test
	public void testHasExpiredProductsAddRemove() {
		Supplier supplier = new Supplier("Bakery");
		Product bread = new Product(7, "Wheat Bread", 2.49, 5, "Baked Goods", LocalDate.now().plusDays(2));
		Product cake = new Product(8, "Chocolate Cake", 19.99, 1, "Pastries", LocalDate.now().minusDays(1));
		supplier.addProduct(bread);
		supplier.addProduct(cake);
		assertTrue(supplier.hasExpiredProducts());
		supplier.removeProduct(cake);
		assertFalse(supplier.hasExpiredProducts());
	}

//---------------------------------------------------------------------------------11
	@Test
	public void testUpdateProductQuantityIncrease() {
		Supplier supplier = new Supplier("Furniture Store");
		Product chair = new Product(1, "Armchair", 199.99, 2, "Furniture", LocalDate.now().plusYears(5));
		supplier.addProduct(chair);
		int newQuantity = 5;
		supplier.updateProductQuantity(chair, newQuantity);
		assertEquals(newQuantity, chair.getQuantity());
	}

	@Test
	public void testUpdateProductQuantityDecrease() {
		Supplier supplier = new Supplier("Clothing Store");
		Product pants = new Product(2, "Jeans", 34.50, 10, "Clothing", LocalDate.now().plusYears(1));
		supplier.addProduct(pants);
		int newQuantity = 3;
		supplier.updateProductQuantity(pants, newQuantity);
		assertEquals(newQuantity, pants.getQuantity());
	}

	@Test
	public void testUpdateProductQuantityNoChange() {
		Supplier supplier = new Supplier("Bookstore");
		Product novel = new Product(3, "Adventure Novel", 12.50, 3, "Fiction", LocalDate.now().plusYears(5));
		supplier.addProduct(novel);
		int currentQuantity = novel.getQuantity();
		supplier.updateProductQuantity(novel, currentQuantity);
		assertEquals(currentQuantity, novel.getQuantity());
	}

	@Test
	public void testUpdateProductQuantityMultipleProducts() {
		Supplier supplier = new Supplier("Grocery Store");
		Product apple = new Product(5, "Apple", 1.25, 10, "Produce", LocalDate.now().plusWeeks(3));
		Product banana = new Product(6, "Banana", 0.79, 15, "Produce", LocalDate.now().plusMonths(2));
		supplier.addProduct(apple);
		supplier.addProduct(banana);
		int newAppleQuantity = 15;
		int newBananaQuantity = 8;
		supplier.updateProductQuantity(apple, newAppleQuantity);
		supplier.updateProductQuantity(banana, newBananaQuantity);
		assertEquals(newAppleQuantity, apple.getQuantity());
		assertEquals(newBananaQuantity, banana.getQuantity());
	}
}