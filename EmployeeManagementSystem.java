import java.util.*;
import java.util.stream.*;

public class EmployeeManagementSystem {
	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(
			new Employee("Nawab", "CSE", 100000, 27),
			new Employee("Razique", "CA", 70000, 32),
			new Employee("Aarif", "BMLT", 50000, 22)
		);
	
	// 1. Filter IT department employees
        System.out.println("IT Department Employees:");
        employees.stream()
                .filter(e -> "IT".equals(e.getDepartment()))
                .forEach(System.out::println);

        // 2. Average salary by department
        System.out.println("\nAverage Salary by Department:");
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.averagingDouble(Employee::getSalary)
                ));
        avgSalaryByDept.forEach((dept, avg) -> 
            System.out.println(dept + ": " + avg));

        // 3. Highest paid employee
        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        highestPaid.ifPresent(emp -> 
            System.out.println("\nHighest Paid: " + emp));

        // 4. Sort by age
        System.out.println("\nEmployees sorted by age:");
        employees.stream()
                .sorted(Comparator.comparingInt(Employee::getAge))
                .forEach(System.out::println);

        // 5. Total salary expenditure
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("\nTotal Salary Expenditure: " + totalSalary);
    }
}

class Employee {
    private String name;
    private String department;
    private double salary;
    private int age;

    public Employee(String name, String department, double salary, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    // Getters
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f, %d years", 
                name, department, salary, age);
    }
}
