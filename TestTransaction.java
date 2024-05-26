package testersquad;

import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class TestTransaction {
	public void testTransactionValid() {
		String type = "Deposit";
		double amount = 100.0;
		Transaction transaction = new Transaction(type, amount);
		assertNotNull(transaction);
		assertEquals(type, transaction.getType());
		assertEquals(amount, transaction.getAmount(), 0.001);
	}

	@Test
	public void testTransactionEmpty() {
		String type = "";
		double amount = 50.0;
		Transaction transaction = new Transaction(type, amount);
		assertNotNull(transaction);
		assertNotEquals(type, transaction.getType(), 0.0);
	}

	@Test
	public void testTransactionLargeAmount() {
		String type = "Investment";
		double amount = 1000000.0;
		Transaction transaction = new Transaction(type, amount);
		assertNotNull(transaction);
		assertEquals(amount, transaction.getAmount(), 0.001);
	}

	@Test
	public void testTransactionMax() {
		String type = "Investment";
		double amount = Integer.MAX_VALUE;
		Transaction transaction = new Transaction(type, amount);
		assertNotNull(transaction);
		assertEquals(amount, transaction.getAmount(), 0.001);
	}

//------------------------------------------------------------
	@Test
	public void testGetDateTimeValidTransaction() {
		Transaction transaction = new Transaction("Deposit", 100.0);
		LocalDateTime dateTime = transaction.getDateTime();
		assertNotNull(dateTime);
	}

//--------------------------------------------------------------
	@Test
	public void testGetTypeValid() {
		String type = "Payment";
		Transaction transaction = new Transaction(type, 5000.0);
		assertNotEquals(type, transaction.getType(), 0.0);
	}

	@Test
	public void testGetTypeEmpty() {
		String type = "";
		Transaction transaction = new Transaction(type, 100.0);
		assertNotEquals(type, transaction.getType(), 0.0);
	}

	@Test
	public void testGetTypeCaseSensitive() {
		String type = "wIthDrAwAl";
		Transaction transaction = new Transaction(type, 300.0);
		assertNotEquals(type.toLowerCase(), transaction.getType().toLowerCase(), 0.0);
	}

//----------------------------------------------------------
	@Test
	public void testGetAmountValid() {
		double amount = 75.50;
		Transaction transaction = new Transaction("Purchase", amount);
		assertEquals(amount, transaction.getAmount(), 0.001);
	}

	@Test
	public void testGetAmountZero() {
		Transaction transaction = new Transaction("Fee", 0.0);
		assertEquals(0.0, transaction.getAmount(), 0.001);
	}

	@Test
	public void testGetAmountNegative() {
		double amount = -25.0;
		Transaction transaction = new Transaction("Chargeback", amount);
		if (transaction.getAmount() > 0) {
			fail("Negative amount should not be allowed.");
		} else {
			assertEquals(amount, transaction.getAmount(), 0.00);
		}
	}

	@Test
	public void testGetAmountLarge() {
		double amount = 10000000.0;
		Transaction transaction = new Transaction("Investment", amount);
		assertEquals(amount, transaction.getAmount(), 0.001);
	}

	@Test
	public void testGetAmountMax() {
		double amount = Integer.MAX_VALUE;
		Transaction transaction = new Transaction("Investment", amount);
		assertEquals(amount, transaction.getAmount(), 0.001);
	}

//----------------------------------------
	@Test
	public void testSetAmountValidPositive() {
		double amount1 = 10.0;
		double amount2 = 50.0;
		Transaction transaction = new Transaction("Payment", amount1);
		transaction.setAmount(amount2);
		assertEquals(amount2, transaction.getAmount(), 0.001);
	}

	@Test
	public void testSetAmountZero() {
		Transaction transaction = new Transaction("Fee", 10.0);
		transaction.setAmount(0.0);
		assertEquals(0.0, transaction.getAmount(), 0.001);
	}

	@Test
	public void testSetAmountLarge() {
		double amount = 10000000.0;
		Transaction transaction = new Transaction("Investment", 1000.0);
		transaction.setAmount(amount);
		assertEquals(amount, transaction.getAmount(), 0.001);
	}

	@Test
	public void testSetAmountMax() {
		double amount = Integer.MAX_VALUE;
		Transaction transaction = new Transaction("Investment", 1000.0);
		transaction.setAmount(amount);
		assertEquals(amount, transaction.getAmount(), 0.001);
	}

//--------------------------------------------------------
	@Test
	public void testIsPositiveAmount() {
		Transaction transaction = new Transaction("Deposit", 100.0);
		assertTrue(transaction.isPositiveAmount());
	}

	@Test
	public void testIsPositiveAmountZero() {
		Transaction transaction = new Transaction("Fee", 0.0);
		assertFalse(transaction.isPositiveAmount());
	}

	@Test
	public void testIsPositiveAmountNegative() {
		Transaction transaction = new Transaction("Chargeback", -25.0);
		assertFalse(transaction.isPositiveAmount());
	}

//----------------------------
	@Test
	public void testIsNegativeAmount() {
		Transaction transaction = new Transaction("Chargeback", -25.0);
		assertTrue(transaction.isNegativeAmount());
	}

	@Test
	public void testIsNegativeAmountZero() {
		Transaction transaction = new Transaction("Fee", 0.0);
		assertFalse(transaction.isPositiveAmount());
	}

	@Test
	public void testIsNegativeAmountPositive() {
		Transaction transaction = new Transaction("Deposit", 100.0);
		assertFalse(transaction.isNegativeAmount());
	}

//-------------------------------------------
	@Test
	public void testIsOfTypeMatching() {
		String transactionType = "DEPOSIT";
		Transaction transaction = new Transaction("deposit", 100.0);
		assertTrue(transaction.isOfType(transactionType));
	}

	@Test
	public void testIsOfTypeNonMatching() {
		String transactionType = "withdrawal";
		Transaction transaction = new Transaction("Transfer", 50.0);
		assertFalse(transaction.isOfType(transactionType));
	}

//----------------------------------------------------
	@Test
	public void testIsRecentTransaction() {
		LocalDateTime date1 = LocalDateTime.now();
		LocalDateTime date2 = date1.minusDays(2);
		Transaction transaction = new Transaction("Deposit", 100.0);
		assertTrue(transaction.isRecentTransaction());
	}

	@Test
	public void testIsRecentTransactionFalse() {
		LocalDateTime date1 = LocalDateTime.now();
		LocalDateTime date21 = date1.minusDays(21);
		Transaction transaction = new Transaction("Payment", 50.0);
		assertTrue(transaction.isRecentTransaction());
	}

//------------------------------------------------
	@Test
	public void testIsPastTransaction() {
		LocalDateTime date1 = LocalDateTime.now();
		LocalDateTime date21 = date1.minusDays(21);
		Transaction transaction = new Transaction("Payment", 50.0);
		assertFalse(transaction.isPastTransaction());
	}

	@Test
	public void testIsPastTransactionFalse() {
		LocalDateTime date1 = LocalDateTime.now();
		LocalDateTime date2 = date1.minusDays(2);
		Transaction transaction = new Transaction("Deposit", 100.0);
		assertFalse(transaction.isPastTransaction());
	}

//--------------------------------------------------------
	@Test
	public void testExceedsThreshold() {
		double amount = 100.0;
		double threshold = 75.0;
		Transaction transaction = new Transaction("Payment", amount);
		assertTrue(transaction.exceedsThreshold(threshold));
	}

	@Test
	public void testExceedsThresholdFalse() {
		double amount = 100.0;
		double threshold = 200.0;
		Transaction transaction = new Transaction("Payment", amount);
		assertFalse(transaction.exceedsThreshold(threshold));
	}

	@Test
	public void testExceedsThreshold_ZeroThreshold() {
		double amount = 25.0;
		double threshold = 0.0;
		Transaction transaction = new Transaction("Fee", amount);
		if (!transaction.exceedsThreshold(threshold)) {
			fail("Transaction amount might be considered exceeding a zero threshold.");
		}
	}

//---------------------------------------
	@Test
	public void testIsFutureTransaction() {
		LocalDateTime date1 = LocalDateTime.now();
		LocalDateTime dateFuture = date1.plusHours(1);
		Transaction transaction = new Transaction("Investment", 1000.0);
		assertFalse(transaction.isFutureTransaction());
	}

	@Test
	public void testIsFutureTransactionFalse() {
		LocalDateTime date1 = LocalDateTime.now();
		Transaction transaction = new Transaction("Deposit", 50.0);
		assertFalse(transaction.isFutureTransaction());
	}

//-------------------------------------------
	@Test
	public void testIsRefundTransactionMatching() {
		String type = "reFund";
		Transaction transaction = new Transaction(type, 100.0);
		assertTrue(transaction.isRefundTransaction());
	}

	@Test
	public void testIsRefundTransactionNonMatching() {
		String type = "withdrawal";
		Transaction transaction = new Transaction(type, 50.0);
		assertFalse(transaction.isRefundTransaction());
	}

//---------------------------------
	@Test
	public void testIsSpecificTransactionMatchingTypeAndAmount() {
		String type = "DEPOSIT";
		double transactionAmount = 100.0;
		Transaction transaction = new Transaction(type, transactionAmount);
		assertTrue(transaction.isSpecificTransaction(type, transactionAmount));
	}

	@Test
	public void testIsSpecificTransactionNonMatchingType() {
		String type = "TRANSFER";
		double amount = 50.0;
		Transaction transaction = new Transaction("Deposit", amount);
		assertFalse(transaction.isSpecificTransaction(type, amount));
	}

}