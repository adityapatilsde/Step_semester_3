package WEEK8.class_problems;

abstract class Vehicle {
    String name;
    boolean available;

    Vehicle(String name) {
        this.name = name;
        available = true;
    }

    abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {

    StandardCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 50;
    }
}

class LuxuryCar extends Vehicle {

    LuxuryCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 100;
    }
}

class Rental {
    Customer customer;
    Vehicle vehicle;
    int days;
    double charge;
    boolean returned;

    Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        charge = vehicle.calculateCharge(days);
        returned = false;
    }

    void returnVehicle() {
        if (returned) {
            System.out.println(vehicle.name + " is already returned.");
            return;
        }

        returned = true;
        vehicle.available = true;

        System.out.println(vehicle.name + " returned. Now available.");
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class RentalService {

    void rentVehicle(Customer customer, Vehicle vehicle, int days) {

        if (!vehicle.available) {
            System.out.println(vehicle.name + " is not available.");
            return;
        }

        vehicle.available = false;

        Rental rental = new Rental(customer, vehicle, days);

        System.out.println(vehicle.name + " rented for " +
                days + " days.");

        System.out.printf("Total charge: $%.2f%n", rental.charge);
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {

        Customer customer = new Customer("Student");

        Vehicle luxuryCar = new LuxuryCar("Luxury Car A");
        Vehicle standardCar = new StandardCar("Standard Car B");

        RentalService service = new RentalService();

        service.rentVehicle(customer, luxuryCar, 3);
        service.rentVehicle(customer, standardCar, 5);

        luxuryCar.available = true;

        System.out.println(luxuryCar.name + " returned. Now available.");
    }
}