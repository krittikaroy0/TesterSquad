package testersquad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class TestInventoryFileManager {

	@Test
	public void testFileManagerConstructor() {
		InventoryFileManager manager = new InventoryFileManager("testFile.txt");
		assertNotNull(manager);
		assertEquals("testFile.txt", manager.getFileName());
	}

	@Test
	public void testGetFileName() {
		InventoryFileManager manager = new InventoryFileManager("testFile.txt");
		assertEquals("testFile.txt", manager.getFileName());
	}

	@Test
	public void testGetFileNameWithNullValue() {
		InventoryFileManager manager = new InventoryFileManager(null);
		assertNull(manager.getFileName());
	}

	@Test
	public void testGetFileNameWithEmptyValue() {
		InventoryFileManager manager = new InventoryFileManager("");
		assertEquals("", manager.getFileName());
	}

	@Test
	public void testSetFileName() {
		InventoryFileManager manager = new InventoryFileManager("testFile.txt");
		manager.setFileName("krittika.txt");
		assertEquals("krittika.txt", manager.getFileName());
	}

	@Test
	public void testBackupInventory() throws IOException {
		InventoryFileManager manager = new InventoryFileManager("testFile.txt");
		String backupFileName = "testFileBackup.txt";
		manager.backupInventory(backupFileName);
		assertTrue(new File(backupFileName).exists());
		assertTrue(new File(backupFileName).isFile());
		assertEquals(new File("testFile.txt").length(), new File(backupFileName).length());
	}
	 


}
