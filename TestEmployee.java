package testersquad;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestEmployee {

	@Test
	public void testEmployeeConstructor() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		assertEquals(2020160197, employee.getId());
		assertEquals("Nowshin", employee.getName());
		assertEquals("CSE", employee.getDepartment());
		assertEquals("Developer", employee.getJobTitle());
		assertEquals(50000.0, employee.getSalary(), 0.0);
	}

	@Test
	public void testEmployeeConstructorWithEmptyName() {
		Employee employee = new Employee(2020160197, "", "CSE", "Developer", 50000.0);
		assertEquals(2020160197, employee.getId());
		assertEquals("", employee.getName());
		assertEquals("CSE", employee.getDepartment());
		assertEquals("Developer", employee.getJobTitle());
		assertEquals(50000.0, employee.getSalary(), 0.0);
	}

	@Test
	public void testEmployeeConstructorWithNegSalary() {
		Employee employee = new Employee(2020160197, "", "CSE", "Developer", -50000.0);
		assertEquals(2020160197, employee.getId());
		assertEquals("", employee.getName());
		assertEquals("CSE", employee.getDepartment());
		assertEquals("Developer", employee.getJobTitle());
		assertEquals(-50000.0, employee.getSalary(), 0.0);
	}

	@Test
	public void testSetId() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setId(2024160197);
		assertEquals(2024160197, employee.getId());
	}

	@Test
	public void testSetIdNeg() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setId(-1);
		assertEquals(-1, employee.getId());
	}

	@Test
	public void testSetIdZero() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setId(0);
		assertEquals(0, employee.getId());
	}

	@Test
	public void testSetIdMultiple() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setId(2020160111);
		employee.setId(2020160222);
		assertEquals(2020160222, employee.getId());
	}

	@Test
	public void testSetIdMax() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setId(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, employee.getId());
	}

	@Test
	public void testSetName() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setName("Tasnia");
		assertEquals("Tasnia", employee.getName());
	}

	@Test
	public void testSetNameWithSpace() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setName("Nowshin Tasnia");
		assertEquals("Nowshin Tasnia", employee.getName());
	}

	@Test
	public void testSetNameMultiple() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setName("Nowshin");
		employee.setName("Tasnia");
		assertEquals("Tasnia", employee.getName());
	}

	@Test
	public void testSetNameCheckCase() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setName("Boishakhy");
		assertNotEquals("boishakhy", employee.getName());
	}

	@Test
	public void testSetNameCheckNull() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setName(null);
		assertNull(employee.getName());
	}

	@Test
	public void testSetDepartment() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setDepartment("EEE");
		assertEquals("EEE", employee.getDepartment());
	}

	@Test
	public void testSetDepartmentMultiple() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setDepartment("EEE");
		employee.setDepartment("CSE");
		assertEquals("CSE", employee.getDepartment());
	}

	@Test
	public void testSetDepartmentCheckCase() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setDepartment("EEE");
		assertNotEquals("eee", employee.getDepartment());
	}

	@Test
	public void testSetDepartmentNull() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setDepartment(null);
		assertNull(employee.getDepartment());
	}

	@Test
	public void testSetJobTitle() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setJobTitle("Tester");
		assertEquals("Tester", employee.getJobTitle());
	}

	@Test
	public void testSetJobTitleMultiple() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setJobTitle("Tester");
		employee.setJobTitle("Designer");
		assertEquals("Designer", employee.getJobTitle());
	}

	@Test
	public void testSetJobTitleCheckCase() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setJobTitle("Designer");
		assertNotEquals("designer", employee.getJobTitle());
	}

	@Test
	public void testSetJobTitleNull() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setJobTitle(null);
		assertNull(employee.getJobTitle());
	}

	@Test
	public void testSetSalary() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setSalary(6000.0);
		assertEquals(6000.0, employee.getSalary(), 0.0);
	}

	@Test
	public void testSetSalaryMultiple() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setSalary(6000);
		employee.setSalary(7000);
		assertEquals(7000, employee.getSalary(), 0.0);
	}

	@Test
	public void testSetSalaryNegative() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setSalary(-7000.0);
		assertEquals(-7000.0, employee.getSalary(), 0.0);
	}

	@Test
	public void testSetSalaryMax() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setSalary(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, employee.getSalary(), 0.0);
	}

	@Test
	public void testSetSalaryFloat() {
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		employee.setSalary(70000.550);
		assertEquals(70000.550, employee.getSalary(), 0.0);
	}
}