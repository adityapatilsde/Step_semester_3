package WEEK3.class_problems;

class Employee {

    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {

    private double stipendCap;

    InternEmployee(int empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        if (getSalary() < stipendCap) {
            return getSalary();
        } else {
            return stipendCap;
        }
    }
}

public class ExtendingEmployees {

    public static void main(String[] args) {

        Employee employee = new Employee(101, "Aditya", 30000);

        ManagerEmployee manager = new ManagerEmployee(
                102, "Rahul", 50000, 10000);

        InternEmployee intern = new InternEmployee(
                103, "Rishit", 15000, 12000);

        Employee[] employees = {employee, manager, intern};

        for (int i = 0; i < employees.length; i++) {

            if (employees[i] instanceof ManagerEmployee) {
                ManagerEmployee m = (ManagerEmployee) employees[i];
                System.out.println("Manager salary: Rs " + m.effectiveSalary());

            } else if (employees[i] instanceof InternEmployee) {
                InternEmployee in = (InternEmployee) employees[i];
                System.out.println("Intern salary: Rs " + in.effectiveSalary());

            } else {
                System.out.println("Employee salary: Rs "
                        + employees[i].getSalary());
            }
        }
    }
}