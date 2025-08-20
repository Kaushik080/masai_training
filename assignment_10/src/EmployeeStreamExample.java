import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return name;
    }
}

@FunctionalInterface
interface BonusCalculator {
    double calculate(double salary);
}

public class EmployeeStreamExample {
    public static void main(String[] args) {
        List<Employee> staff = Arrays.asList(
                new Employee(1, "Alice", "IT", 60000),
                new Employee(2, "Bob", "Finance", 45000),
                new Employee(3, "Charlie", "IT", 70000),
                new Employee(4, "David", "HR", 40000),
                new Employee(5, "Eva", "Finance", 80000)
        );

        List<Employee> topEarners = staff.stream()
                .filter(emp -> emp.getSalary() > 50000)
                .collect(Collectors.toList());
        System.out.println("Employees with salary > 50000 → " + topEarners);

        List<String> upperNames = staff.stream()
                .map(emp -> emp.getName().toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Names in uppercase → " + upperNames);

        Map<String, List<Employee>> deptGroups = staff.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("Grouped by department → " + deptGroups);

        List<Employee> salarySortedDesc = staff.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
        System.out.println("Sorted by salary → " + salarySortedDesc);

        double avgSalary = staff.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("Average salary → " + avgSalary);

        Optional<Employee> firstEmp = staff.stream().findFirst();
        System.out.println("First employee (Optional) → " + firstEmp.orElse(null));

        BonusCalculator bonus = salary -> salary * 1.10;
        System.out.println("Bonus salaries printed →");
        staff.forEach(emp -> {
            double updatedSalary = bonus.calculate(emp.getSalary());
            emp.setSalary(updatedSalary);
            System.out.println(emp.getName() + " → " + updatedSalary);
        });
    }
}
