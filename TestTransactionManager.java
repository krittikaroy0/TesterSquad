package testersquad;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestTransactionManager {
	@Test
	public void testTransactionManagerEmptyList() {
		TransactionManager manager = new TransactionManager();
		assertTrue(manager.getTransactions().isEmpty());
	}

	@Test
	public void testTransactionManagerListType() {
		TransactionManager manager = new TransactionManager();
		List<Transaction> transactions = manager.getTransactions();
		assertTrue(transactions instanceof ArrayList);
	}

//------------------------------------------------------
	@Test
	public void testAddTransaction() {
		TransactionManager manager = new TransactionManager();
		Transaction transaction = new Transaction("Deposit", 100.0);
		manager.addTransaction(transaction);

		List<Transaction> transactions = manager.getTransactions();
		assertEquals(1, transactions.size());
	}

	@Test
	public void testAddTransactionMultiple() {
		TransactionManager manager = new TransactionManager();
		Transaction transaction1 = new Transaction("Payment", 50.0);
		Transaction transaction2 = new Transaction("Withdrawal", 25.0);
		manager.addTransaction(transaction1);
		manager.addTransaction(transaction2);
		List<Transaction> transactions = manager.getTransactions();
		assertEquals(2, transactions.size());
	}

	@Test
	public void testAddTransactionDuplicate() {
		TransactionManager manager = new TransactionManager();
		Transaction transaction = new Transaction("Deposit", 100.0);
		manager.addTransaction(transaction);
		manager.addTransaction(transaction);
		List<Transaction> transactions = manager.getTransactions();
		assertEquals(2, transactions.size());
	}

//--------------------------------------------------------------
	@Test
	public void testRemoveTransaction() {
		TransactionManager manager = new TransactionManager();
		Transaction transaction = new Transaction("Deposit", 100.0);
		manager.addTransaction(transaction);
		boolean removed = manager.removeTransaction(transaction);
		assertTrue(removed);
		assertEquals(0, manager.getTransactions().size(), 0.0);
	}

	@Test
	public void testRemoveTransactionNonExisting() {
		TransactionManager manager = new TransactionManager();
		Transaction transaction = new Transaction("Deposit", 100.0);
		Transaction transaction1 = new Transaction("Withdrawal", 50.0);
		manager.addTransaction(transaction);
		boolean removed = manager.removeTransaction(transaction1);
		assertFalse(removed);
		assertEquals(1, manager.getTransactions().size(), 0.0);
	}

//----------------------------------------------------------
	@Test
	public void testGetTransactions() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Deposit", 100.0));
		manager.addTransaction(new Transaction("Withdrawal", 50.0));
		List<Transaction> transactions = manager.getTransactions();
		assertEquals(2, transactions.size(), 0.0);
	}

	@Test
	public void testGetTransactionsEmpty() {
		TransactionManager manager = new TransactionManager();
		List<Transaction> transactions = manager.getTransactions();
		assertTrue(transactions.isEmpty());
		assertSame(transactions, manager.getTransactions());
	}

	@Test
	public void testGetTransactions_Modification() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Fee", 10.0));
		List<Transaction> transactions = manager.getTransactions();
		transactions.clear();
		if (!manager.getTransactions().isEmpty()) {
			fail("Modification on returned list might not affect the internal list.");
		}
	}

	@Test
	public void testGetTransactionsCopy() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Payment", 75.0));
		List<Transaction> transactions = manager.getTransactions();
		transactions.add(new Transaction("Refund", 25.0));
		assertEquals(transactions.size(), manager.getTransactions().size());
	}

//--------------------------------------------------------------------
	@Test
	public void testGetTotalRevenueNoTransactions() {
		TransactionManager manager = new TransactionManager();
		assertEquals(0.0, manager.getTotalRevenue(), 0.001);
	}

	@Test
	public void testGetTotalRevenueNegative() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Withdrawal", -100.0));
		manager.addTransaction(new Transaction("Fee", -5.0));
		assertEquals(0.0, manager.getTotalRevenue(), 0.001);
	}

	@Test
	public void testGetTotalRevenueMixed() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Deposit", 100.0));
		manager.addTransaction(new Transaction("Chargeback", -25.0));
		manager.addTransaction(new Transaction("Payment", 50.0));
		assertEquals(150.0, manager.getTotalRevenue(), 0.00);
	}

	@Test
	public void testGetTotalRevenueLarge() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Investment", 1000000.0));
		assertEquals(1000000.0, manager.getTotalRevenue(), 0.001);
	}

//------------------------------------------------------------
	@Test
	public void testGetTotalTransactionsEmpty() {
		TransactionManager manager = new TransactionManager();
		assertEquals(0, manager.getTotalTransactions(), 0.0);
	}

	@Test
	public void testGetTotalTransactionsMultiple() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Deposit", 100.0));
		manager.addTransaction(new Transaction("Withdrawal", 50.0));
		manager.addTransaction(new Transaction("Fee", 2.5));
		assertEquals(3, manager.getTotalTransactions());
	}

	@Test
	public void testGetTransactionsMatchingType() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("deposit", 100.0));
		manager.addTransaction(new Transaction("withdrawal", 50.0));
		manager.addTransaction(new Transaction("Payment", 75.0));
		List<Transaction> transactions = manager.getTransactionsByType("Withdrawal");
		assertEquals(1, transactions.size());
		assertNotEquals("withdrawal", transactions.get(0).getType(), 0.0);
	}

	@Test
	public void testGetTransactionsNonMatchingType() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Deposit", 100.0));
		manager.addTransaction(new Transaction("Withdrawal", 50.0));
		manager.addTransaction(new Transaction("Fee", 2.5));
		List<Transaction> transactions = manager.getTransactionsByType("Refund");
		assertTrue(transactions.isEmpty());
	}

//------------------------------------------------------------
	@Test
	public void testHasTransaction() {
		TransactionManager manager = new TransactionManager();
		Transaction transaction = new Transaction("Deposit", 100.0);
		manager.addTransaction(transaction);
		assertTrue(manager.hasTransaction(transaction));
	}

	@Test
	public void testHasTransactionNonExisting() {
		TransactionManager manager = new TransactionManager();
		Transaction transaction1 = new Transaction("Deposit", 100.0);
		Transaction transaction2 = new Transaction("Withdrawal", 50.0);
		manager.addTransaction(transaction1);
		assertFalse(manager.hasTransaction(transaction2));
	}

	@Test
	public void testHasTransactionByData() {
		TransactionManager manager = new TransactionManager();
		Transaction transaction1 = new Transaction("Fee", 100.0);
		manager.addTransaction(transaction1);
		Transaction transaction2 = new Transaction("Fee", 100.0);
		assertTrue(manager.hasTransaction(transaction1));
	}

//------------------------------------------------------------
	@Test
	public void testClearAllTransactions() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Deposit", 100.0));
		manager.clearAllTransactions();
		assertEquals(0, manager.getTotalTransactions(), 0.0);
	}

	@Test
	public void testClearAllTransactionsEmpty() {
		TransactionManager manager = new TransactionManager();
		manager.clearAllTransactions();
		assertEquals(0, manager.getTotalTransactions(), 0.0);
	}

	@Test
	public void testClearAllTransactionsMultiple() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Fee", 10.0));
		manager.addTransaction(new Transaction("Withdrawal", 50.0));
		manager.clearAllTransactions();
		assertEquals(0, manager.getTotalTransactions(), 0.0);
	}

	@Test
	public void testClearAllTransactionsStateAfterClearing() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Chargeback", -10.0));
		manager.clearAllTransactions();
		assertTrue(manager.getTransactions().isEmpty());
	}

	@Test
	public void testClearAllTransactionsMultipleTimes() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Fee", 10.0));
		manager.clearAllTransactions();
		manager.clearAllTransactions();
		assertEquals(0, manager.getTotalTransactions(), 0.0);
	}

//------------------------------------------------------------
	@Test
	public void testGetTotalExpensesEmpty() {
		TransactionManager manager = new TransactionManager();
		assertEquals(0.0, manager.getTotalExpenses(), 0.001);
	}

	@Test
	public void testGetTotalExpensesAllPositive() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Deposit", 100.0));
		manager.addTransaction(new Transaction("Payment", 50.0));
		assertEquals(0.0, manager.getTotalExpenses(), 0.001);
	}

	@Test
	public void testGetTotalExpensesMixed() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Deposit", 100.0));
		manager.addTransaction(new Transaction("Chargeback", -25.0));
		manager.addTransaction(new Transaction("Fee", -5.0));
		assertEquals(30.0, manager.getTotalExpenses(), 0.001);
	}

	@Test
	public void testGetTotalExpensesLarge() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Refund", -1000000.0));
		assertEquals(1000000.0, manager.getTotalExpenses(), 0.001);
	}

	@Test
	public void testGetTotalExpensesZero() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Transfer", 0.0));
		manager.addTransaction(new Transaction("Withdrawal", -50.0));
		if (manager.getTotalExpenses() != 50.0) {
			fail("Unexpected behavior for zero transaction amount.");
		}
	}

//-------------------------------------------------------------------------------
	@Test
	public void testHasRefundTransactionsEmpty() {
		TransactionManager manager = new TransactionManager();
		assertFalse(manager.hasRefundTransactions());
	}

	@Test
	public void testHasRefundTransactionsNo() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Deposit", 100.0));
		manager.addTransaction(new Transaction("Withdrawal", 50.0));
		manager.addTransaction(new Transaction("Fee", 2.5));
		assertFalse(manager.hasRefundTransactions());
	}

	@Test
	public void testHasRefundTransactionsYes() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Deposit", 100.0));
		manager.addTransaction(new Transaction("Withdrawal", 50.0));
		manager.addTransaction(new Transaction("reFund", 25.0));
		assertTrue(manager.hasRefundTransactions());
	}

	@Test
	public void testHasRefundTransactions_MultipleRefunds() {
		TransactionManager manager = new TransactionManager();
		manager.addTransaction(new Transaction("Fee", 2.5));
		manager.addTransaction(new Transaction("Refund", 50.0));
		manager.addTransaction(new Transaction("refund", 25.0));
		assertTrue(manager.hasRefundTransactions());
	}

}