package testersquad;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class TestEmployeeManager {
	@Test
	public void testEmployeeManager() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
		manager.addEmployee(employee);
		List<Employee> employeeList = manager.getEmployees();
		employeeList.add(new Employee(2020160198, "Krittika", "CSE", "Tester", 500000000000.0));
		assertEquals(1, manager.getEmployees().size());
	}
	@Test
	public void testAddEmployeeValid() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee = new Employee(2020160198, "Krittika", "CSE", "Tester", 500000000000.0);	  
	  manager.addEmployee(employee);	  
	  assertEquals(1, manager.getEmployees().size());
	  assertTrue(manager.getEmployees().contains(employee));
	}
	@Test
	public void testAddEmployeeMultiple() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee1 = new Employee(2020160198, "Krittika", "CSE", "Tester", 500000000000.0);
	  Employee employee2 = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);	  
	  manager.addEmployee(employee1);
	  manager.addEmployee(employee2);	  
	  assertEquals(2, manager.getEmployees().size());
	  assertTrue(manager.getEmployees().contains(employee1));
	  assertTrue(manager.getEmployees().contains(employee2));
	}
	@Test
	public void testAddEmployeeOrder() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee1 = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
	  Employee employee2 = new Employee(2020160198, "Krittika", "CSE", "Tester", 500000000000.0);	  
	  manager.addEmployee(employee1);
	  manager.addEmployee(employee2);	  
	  List<Employee> employeeList = manager.getEmployees();	  
	  assertEquals(employee1, employeeList.get(0));
	  assertEquals(employee2, employeeList.get(1));
	}

	@Test
	public void testEmployeeManagerEmpty() {
		EmployeeManager manager = new EmployeeManager();
		assertEquals(0, manager.getEmployees().size());
	}

	@Test
	public void testRemoveEmployeeExisting() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee = new Employee(2020160198, "Krittika", "CSE", "Tester", 500000000000.0);
	  manager.addEmployee(employee);	  
	  int employeeIdToRemove = employee.getId();
	  boolean removed = manager.removeEmployee(employeeIdToRemove);
	  assertTrue(removed);
	  assertEquals(0, manager.getEmployees().size());
	}
	@Test
	public void testRemoveEmployeeNonExisting() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee = new Employee(2020160166, "Hia", "Medical", "Surgery", 547000);
	  manager.addEmployee(employee);	  
	  int nonExistingId = 10;
	  boolean removed = manager.removeEmployee(nonExistingId);	  
	  assertFalse(removed);
	  assertEquals(1, manager.getEmployees().size());
	}
	@Test
	public void testRemoveEmployeeEmpty() {
	  EmployeeManager manager = new EmployeeManager();	  
	  boolean removed = manager.removeEmployee(1);	  
	  assertFalse(removed);
	}
	@Test
	public void testRemoveEmployeeMultiple() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee1 = new Employee(2020160197, "Nowshin", "CSE", "Developer", 50000.0);
	  Employee employee2 = new Employee(2020160166, "Hia", "Medical", "Surgery", 547000);
	  manager.addEmployee(employee1);
	  manager.addEmployee(employee2);	  
	  int employeeIdToRemove = 2020160197;	  
	  boolean removed1 = manager.removeEmployee(employeeIdToRemove);
	  boolean removed2 = manager.removeEmployee(employeeIdToRemove); 
	  assertTrue(removed1);
	  assertEquals(1, manager.getEmployees().size());
	}
	@Test
	public void testGetEmployees() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee = new Employee(2020160166, "Hia", "Medical", "Surgery", 547000);
	  manager.addEmployee(employee);	  
	  List<Employee> employeeList = manager.getEmployees();
	  employeeList.clear();	  
	  assertEquals(1, manager.getEmployees().size());
	}
	@Test
	public void testGetEmployeesMultipleCalls() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee = new Employee(2020160166, "Hia", "Medical", "Surgery", 547000);
	  manager.addEmployee(employee);	  
	  List<Employee> firstList = manager.getEmployees();
	  List<Employee> secondList = manager.getEmployees();	  
	  assertNotSame(firstList, secondList);
	}

	@Test
	public void testFindEmployeeById() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160166, "Hia", "Medical", "Surgery", 547000);
		Employee employee2 = new Employee(2020160177, "Ruhan", "PME", "Developer", 589000);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		Employee foundEmployee = manager.findEmployeeById(employee1.getId());
		assertNotNull(foundEmployee);
		assertEquals(employee1.getId(), foundEmployee.getId());
		assertEquals(employee1.getName(), foundEmployee.getName());
	}
	@Test
	public void testFindEmployeeByIdNonExisting() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee = new Employee(2020160177, "Ruhan", "PME", "Developer", 589000);
	  manager.addEmployee(employee);	  
	  int nonExistingId = 10;
	  Employee foundEmployee = manager.findEmployeeById(nonExistingId);	  
	  assertNull(foundEmployee);
	}

	@Test
	public void testFindEmployeeByIdMultipleSameID() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee1 = new Employee(2020160166, "Hia", "Medical", "Surgery", 547000);
	  Employee employee2 = new Employee(2020160166, "Ruhan", "PME", "Developer", 589000);
	  manager.addEmployee(employee1);
	  manager.addEmployee(employee2);	  
	  int employeeIdToFind = 2020160166;
	  Employee foundEmployee = manager.findEmployeeById(employeeIdToFind);	  
	  assertNotNull(foundEmployee);
	}
	@Test
	public void testFindEmployeeByIdEmpty() {
	  EmployeeManager manager = new EmployeeManager();	  
	  int randomId = 2020160179;
	  Employee foundEmployee = manager.findEmployeeById(randomId);
	  assertNull(foundEmployee);
	}

	@Test
	public void testRemoveEmployee() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee = new Employee(1, "John Doe", "IT", "Developer", 5000.0);
		manager.addEmployee(employee);
		assertTrue(manager.removeEmployee(employee.getId()));
		assertFalse(manager.getEmployees().contains(employee));
	}


	@Test
	public void testFindEmployeesByDepartment() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160197, "Krittika", "IT", "Developer", 5000.0);
		Employee employee2 = new Employee(2020160487, "Mahbuba", "IT", "Developer", 6000.0);
		Employee employee3 = new Employee(2023160197, "Nabiha", "HR", "Manager", 7000.0);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		manager.addEmployee(employee3);
		List<Employee> itEmployees = manager.findEmployeesByDepartment("IT");
		assertEquals(2, itEmployees.size());
		assertTrue(itEmployees.contains(employee1));
		assertTrue(itEmployees.contains(employee2));
	}
	@Test
	public void testFindEmployeesByDepartmentNonExisting() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160197, "Krittika", "IT", "Developer", 5000.0);
		Employee employee2 = new Employee(2020160487, "Mahbuba", "IT", "Developer", 6000.0);
		Employee employee3 = new Employee(2023160197, "Nabiha", "HR", "Manager", 7000.0);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		manager.addEmployee(employee3);
		List<Employee> itEmployees = manager.findEmployeesByDepartment("CSE");
		assertEquals(0, itEmployees.size());
		assertFalse(itEmployees.contains(employee1));
		assertFalse(itEmployees.contains(employee2));
	}
	
	@Test
	public void testFindEmployeesByDepartmentEmpty() {
	  EmployeeManager manager = new EmployeeManager();	  
	  String anyDepartment = "Engineering";
	  List<Employee> foundEmployees = manager.findEmployeesByDepartment(anyDepartment);	  
	  assertTrue(foundEmployees.isEmpty());
	}
	@Test
	public void testFindEmployeesByDepartmentCaseInsensitivity() {
	  EmployeeManager manager = new EmployeeManager();
	  Employee employee = new Employee(2023160197, "Nabiha", "HR", "Manager", 7000.0);
	  manager.addEmployee(employee);
	  String departmentToFind = "hr";
	  List<Employee> foundEmployees = manager.findEmployeesByDepartment(departmentToFind);	  
	  assertEquals(1, foundEmployees.size());
	  assertTrue(foundEmployees.contains(employee));
	}

	@Test
	public void testCalculateTotalSalary() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160487, "Mahbuba", "IT", "Developer", 6000.0);
		Employee employee2 = new Employee(2023160197, "Nabiha", "HR", "Manager", 7000.0);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		double totalSalary = manager.calculateTotalSalary();
		assertEquals(13000.0, totalSalary, 0.0);
	}
	@Test
	public void testCalculateTotalSalaryNegative() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160487, "Mahbuba", "IT", "Developer", 6000.0);
		Employee employee2 = new Employee(2023160197, "Nabiha", "HR", "Manager", -7000.0);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		double totalSalary = manager.calculateTotalSalary();
		assertEquals(-1000.0, totalSalary, 0.0);
	}
	@Test
	public void testCalculateTotalSalaryNoEmployees() {
	  EmployeeManager manager = new EmployeeManager();	  
	  double expectedTotal = 0.0;
	  double actualTotal = manager.calculateTotalSalary();	  
	  assertEquals(expectedTotal, actualTotal, 0.01);
	}

	@Test
	public void testGetEmployeesWithHighestSalary() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160197, "Krittika", "IT", "Developer", 5000.0);
		Employee employee2 = new Employee(2020160487, "Mahbuba", "IT", "Developer", 6000.0);
		Employee employee3 = new Employee(2023160197, "Nabiha", "HR", "Manager", 7000.0);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		manager.addEmployee(employee3);
		List<Employee> highestPaidEmployees = manager.getEmployeesWithHighestSalary();
		assertEquals(1, highestPaidEmployees.size());
		assertFalse(highestPaidEmployees.contains(employee2));
		assertFalse(highestPaidEmployees.contains(employee1));
		assertTrue(highestPaidEmployees.contains(employee3));
	}
	@Test
	public void testGetEmployeesWithHighestSalaryMultiple() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160197, "Krittika", "IT", "Developer", 5000.0);
		Employee employee2 = new Employee(2020160487, "Mahbuba", "IT", "Developer", 7000.0);
		Employee employee3 = new Employee(2023160197, "Nabiha", "HR", "Manager", 7000.0);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		manager.addEmployee(employee3);
		List<Employee> highestPaidEmployees = manager.getEmployeesWithHighestSalary();
		assertEquals(2, highestPaidEmployees.size());
		assertFalse(highestPaidEmployees.contains(employee1));
		assertTrue(highestPaidEmployees.contains(employee3));
		assertTrue(highestPaidEmployees.contains(employee2));
	}
	@Test
	public void testGetEmployeesWithHighestSalaryNegative() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160197, "Krittika", "IT", "Developer", -500000.0);
		Employee employee2 = new Employee(2020160487, "Mahbuba", "IT", "Developer", 7000.0);
		Employee employee3 = new Employee(2023160197, "Nabiha", "HR", "Manager", 7000.0);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		manager.addEmployee(employee3);
		List<Employee> highestPaidEmployees = manager.getEmployeesWithHighestSalary();
		assertEquals(2, highestPaidEmployees.size());
		assertFalse(highestPaidEmployees.contains(employee1));
		assertTrue(highestPaidEmployees.contains(employee3));
		assertTrue(highestPaidEmployees.contains(employee2));
	}
	@Test
	public void testGetEmployeesWithHighestSalaryEmpty() {
		EmployeeManager manager = new EmployeeManager();
		List<Employee> highestPaidEmployees = manager.getEmployeesWithHighestSalary();
		assertEquals(0, highestPaidEmployees.size());
	}
	@Test
	public void testFindEmployeesByJobTitle() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160197, "Krittika", "IT", "Developer", -500000.0);
		Employee employee2 = new Employee(2020160487, "Mahbuba", "IT", "Developer", 7000.0);
		Employee employee3 = new Employee(2023160197, "Nabiha", "HR", "Manager", 7000.0);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		manager.addEmployee(employee3);
		List<Employee> developers = manager.findEmployeesByJobTitle("Developer");
		assertEquals(2, developers.size());
		assertTrue(developers.contains(employee1));
		assertTrue(developers.contains(employee2));
		assertFalse(developers.contains(employee3));
	}
	@Test
	public void testFindEmployeesByJobTitleNonExisting() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160197, "Krittika", "IT", "Developer", -500000.0);
		Employee employee2 = new Employee(2020160487, "Mahbuba", "IT", "Developer", 7000.0);
		Employee employee3 = new Employee(2023160197, "Nabiha", "HR", "Manager", 7000.0);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		manager.addEmployee(employee3);
		List<Employee> developers = manager.findEmployeesByJobTitle("Tester");
		assertEquals(0, developers.size());
		assertFalse(developers.contains(employee1));
		assertFalse(developers.contains(employee2));
		assertFalse(developers.contains(employee3));
	}
	@Test
	public void testFindEmployeesByJobTitleEmpty() {
		EmployeeManager manager = new EmployeeManager();
		List<Employee> developers = manager.findEmployeesByJobTitle("Tester");
		assertEquals(0, developers.size());
	}

	@Test
	public void testFindEmployeesByJobTitleCaseInsensitivity() {
		EmployeeManager manager = new EmployeeManager();
		Employee employee1 = new Employee(2020160197, "Krittika", "IT", "Developer", -500000.0);
		Employee employee2 = new Employee(2020160487, "Mahbuba", "IT", "Developer", 7000.0);
		Employee employee3 = new Employee(2023160197, "Nabiha", "HR", "Manager", 7000.0);
		manager.addEmployee(employee1);
		manager.addEmployee(employee2);
		manager.addEmployee(employee3);
		List<Employee> developers = manager.findEmployeesByJobTitle("deveLoper");
		assertEquals(2, developers.size());
		assertTrue(developers.contains(employee1));
		assertTrue(developers.contains(employee2));
		assertFalse(developers.contains(employee3));
	}
}