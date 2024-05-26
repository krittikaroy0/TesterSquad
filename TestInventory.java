package testersquad;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
public class TestInventory {
	@Test
	public void testInventoryConstructor() {
	    Inventory inventory = new Inventory();
	    assertNotNull(inventory.getProducts());
	    assertTrue(inventory.getProducts().isEmpty());
	}

	@Test
	public void testInventoryConstructorInitialCapacity() {
	    Inventory inventory = new Inventory();
	    assertNotNull(inventory.getProducts());
	    assertEquals(0, inventory.getProducts().size(), 0.0);
	}

	@Test
	public void testInventoryConstructorNoNull() {
	    Inventory inventory = new Inventory();
	    assertNotNull(inventory.getProducts());
	}
	@Test
	public void testAddProduct() {
	    Inventory inventory = new Inventory();
	    Product product =  new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 12, 31));;
	    inventory.addProduct(product);
	    assertEquals(1, inventory.getProducts().size());
	    assertTrue(inventory.getProducts().contains(product));
	}

	@Test
	public void testAddMultipleProducts() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 12, 31));;
	    Product product2 = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    assertEquals(2, inventory.getProducts().size());
	    assertTrue(inventory.getProducts().contains(product1));
	    assertTrue(inventory.getProducts().contains(product2));
	}

	@Test
	public void testRemoveProduct() {
	    Inventory inventory = new Inventory();
	    Product product = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product);
	    assertTrue(inventory.removeProduct(product.getId()));
	    assertEquals(0, inventory.getProducts().size());
	}

	@Test
	public void testRemoveNonExistentProduct() {
	    Inventory inventory = new Inventory();
	    Product product = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product);
	    assertFalse(inventory.removeProduct(product.getId() + 1));
	    assertEquals(1, inventory.getProducts().size());
	}

	@Test
	public void testRemoveMultipleProducts() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 12, 31));;
	    Product product2 = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    assertTrue(inventory.removeProduct(product1.getId()));
	    assertEquals(1, inventory.getProducts().size());
	    assertTrue(inventory.removeProduct(product2.getId()));
	    assertEquals(0, inventory.getProducts().size());
	}
	
	@Test
	public void testGetProducts() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 12, 31));;
	    Product product2 = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    List<Product> products = inventory.getProducts();
	    assertEquals(2, products.size());
	    assertTrue(products.contains(product1));
	    assertTrue(products.contains(product2));
	}

	@Test
	public void testGetProductsEmpty() {
	    Inventory inventory = new Inventory();
	    List<Product> products = inventory.getProducts();
	    assertTrue(products.isEmpty());
	}

	@Test
	public void testGetProductsClone() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 12, 31));;
	    Product product2 = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    List<Product> products = inventory.getProducts();
	    assertSame(inventory.getProducts(), products);
	    products.remove(product1);
	    assertEquals(1, inventory.getProducts().size());
	}
	
	@Test
	public void testFindProductById() {
	    Inventory inventory = new Inventory();
	    Product product = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product);
	    assertSame(product, inventory.findProductById(product.getId()));
	}

	@Test
	public void testFindNonExistentProductById() {
	    Inventory inventory = new Inventory();
	    Product product = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product);
	    assertNull(inventory.findProductById(product.getId() + 1));
	}

	@Test
	public void testFindProductByIdEmpty() {
	    Inventory inventory = new Inventory();
	    assertNull(inventory.findProductById(1));
	}
	
	@Test
	public void testFindProductsByName() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 12, 31));;
	    Product product2 = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    List<Product> foundProducts = inventory.findProductsByName("Headphones");
	    assertEquals(1, foundProducts.size());
	    assertTrue(foundProducts.contains(product1));
	}

	@Test
	public void testFindNoProductsByName() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 12, 31));;
	    Product product2 = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    List<Product> foundProducts = inventory.findProductsByName("Product2");
	    assertTrue(foundProducts.isEmpty());
	}

	@Test
	public void testFindProductsByNameEmpty() {
	    Inventory inventory = new Inventory();
	    List<Product> foundProducts = inventory.findProductsByName("Product1");
	    assertTrue(foundProducts.isEmpty());
	}
	@Test
	public void testUpdateProduct() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 12, 31));
	    Product updatedProduct = new Product(4, "Headphones", 2500.55, 400, "oppo", LocalDate.of(2023, 12, 31));
	    inventory.addProduct(product1);
	    assertTrue(inventory.updateProduct(4, updatedProduct));
	    assertEquals(updatedProduct, inventory.findProductById(4));
	}
	@Test
	public void testUpdateProductNotFound() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 12, 31));
	    Product updatedProduct = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    inventory.addProduct(product1);
	    assertFalse(inventory.updateProduct(5, updatedProduct));
	}
	@Test
	public void testUpdateProductMultipleProducts() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(4, "Headphones", 2000.55, 400, "oppo", LocalDate.of(2023, 12, 31));
	    Product product2 = new Product(5, "Keyboard", 500.00, 100, "logitech", LocalDate.of(2024, 06, 15));
	    Product updatedProduct = new Product(4, "Headphones", 2500.55, 400, "oppo", LocalDate.of(2023, 12, 31));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    assertTrue(inventory.updateProduct(4, updatedProduct));
	    assertEquals(updatedProduct, inventory.findProductById(4));
	}
	@Test
	public void testGetProductsByPriceRange() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    List<Product> products = inventory.getProductsByPriceRange(12.0, 25.0);
	    assertEquals(1, products.size());
	    assertTrue(products.contains(product2));
	}
	
	@Test
	public void testGetProductsByPriceRangeNoProducts() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    List<Product> products = inventory.getProductsByPriceRange(50.0, 60.0);
	    assertTrue(products.isEmpty());
	}
	@Test
	public void testGetProductsByPriceRangeMinGreaterThanMax() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    List<Product> products = inventory.getProductsByPriceRange(30.0, 40.0);
	    assertEquals(1, products.size());
	    assertTrue(products.contains(product3));
	}
	
	@Test
	public void testGetTotalInventoryValueSingleProduct() {
	    Inventory inventory = new Inventory();
	    Product product = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    inventory.addProduct(product);
	    assertEquals(50.0, inventory.getTotalInventoryValue(), 0.01);
	}
	@Test
	public void testGetTotalInventoryValueMultipleProducts() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 10, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    assertEquals(550.0, inventory.getTotalInventoryValue(), 0.01);
	}
	@Test
	public void testGetTotalInventoryValueEmptyInventory() {
	    Inventory inventory = new Inventory();
	    assertEquals(0.0, inventory.getTotalInventoryValue(), 0.01);
	}
	@Test
	public void testCheckProductAvailabilityAvailable() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    assertTrue(inventory.checkProductAvailability(1, 3));
	}
	@Test
	public void testCheckProductAvailabilityNotAvailable() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    assertFalse(inventory.checkProductAvailability(1, 6));
	}
	@Test
	public void testCheckProductAvailabilityNotInInventory() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    assertFalse(inventory.checkProductAvailability(4, 1));
	}
	@Test
	public void testCalculateAverageProductPriceWithProducts() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    assertEquals(20.0, inventory.calculateAverageProductPrice(), 0.01);
	}
	@Test
	public void testCalculateAverageProductPriceWithoutProducts() {
	    Inventory inventory = new Inventory();
	    assertEquals(0.0, inventory.calculateAverageProductPrice(), 0.01);
	}
	@Test
	public void testCalculateAverageProductPriceWithOneProduct() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    inventory.addProduct(product1);
	    assertEquals(10.0, inventory.calculateAverageProductPrice(), 0.01);
	}
	@Test
	public void testHasProductTypeTrue() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "PC", 10.0, 5, "HP", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    assertFalse(inventory.hasProductType("PC"));
	}
	@Test
	public void testHasProductTypeFalse() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "Phone", 10.0, 5, "iPhone", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    assertFalse(inventory.hasProductType("Phone"));
	}
	@Test
	public void testHasProductTypeEmptyInventory() {
	    Inventory inventory = new Inventory();
	    assertFalse(inventory.hasProductType("PC"));
	}
	@Test
	public void testUpdateProductQuantityTrue() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "Phone", 10.0, 5, "iPhone", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    assertTrue(inventory.updateProductQuantity(1, 5));
	    assertEquals(5, product1.getQuantity());
	}
	@Test
	public void testUpdateProductQuantityFalse() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "Phone", 10.0, 5, "iPhone", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    assertFalse(inventory.updateProductQuantity(4, 5));
	}
	@Test
	public void testUpdateProductQuantityNegativeProductId() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "Phone", 10.0, 5, "iPhone", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    assertFalse(inventory.updateProductQuantity(-1, 5));
	}
	@Test
	public void testRemoveExpiredProducts() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "Phone", 10.0, 5, "iPhone", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    product1.setExpirationDate(LocalDate.of(2020, 1, 1));
	    product2.setExpirationDate(LocalDate.of(2020, 1, 1));
	    inventory.removeExpiredProducts();
	    assertEquals(0, inventory.getProducts().size());
	    assertFalse(inventory.getProducts().contains(product3));
	}
	@Test
	public void testRemoveExpiredProductsNonExpired() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "Phone", 10.0, 5, "iPhone", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    inventory.removeExpiredProducts();
	    assertEquals(0, inventory.getProducts().size());
	    assertFalse(inventory.getProducts().contains(product1));
	    assertFalse(inventory.getProducts().contains(product2));
	    assertFalse(inventory.getProducts().contains(product3));
	}
	@Test
	public void testRemoveExpiredProductsEmptyInventory() {
	    Inventory inventory = new Inventory();
	    inventory.removeExpiredProducts();
	    assertEquals(0, inventory.getProducts().size());
	}
	@Test
	public void testGetProductsBelowThreshold() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "Phone", 10.0, 5, "iPhone", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 1, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    List<Product> belowThresholdProducts = inventory.getProductsBelowThreshold(8);
	    assertEquals(2, belowThresholdProducts.size());
	    assertTrue(belowThresholdProducts.contains(product1));
	    assertTrue(belowThresholdProducts.contains(product3));
	}
	@Test
	public void testGetProductsBelowThresholdNoProducts() {
	    Inventory inventory = new Inventory();
	    Product product1 = new Product(1, "Phone", 10.0, 5, "iPhone", LocalDate.of(2023, 1, 1));
	    Product product2 = new Product(2, "AC", 20.0, 10, "Vision", LocalDate.of(2023, 2, 1));
	    Product product3 = new Product(3, "TV", 30.0, 15, "LG", LocalDate.of(2023, 3, 1));
	    inventory.addProduct(product1);
	    inventory.addProduct(product2);
	    inventory.addProduct(product3);
	    List<Product> belowThresholdProducts = inventory.getProductsBelowThreshold(5);
	    assertEquals(0, belowThresholdProducts.size());
	}
	@Test
	public void testGetProductsBelowThresholdEmptyInventory() {
	    Inventory inventory = new Inventory();
	    List<Product> belowThresholdProducts = inventory.getProductsBelowThreshold(10);
	    assertEquals(0, belowThresholdProducts.size());
	}
}

