package WEEK8.assignment_problems;

import java.util.ArrayList;

interface ShippingType {
    double charge(double kg);
}

class Standard implements ShippingType {
    public double charge(double kg) {
        return 40 + 10 * kg;
    }
}

class Express implements ShippingType {
    public double charge(double kg) {
        return 80 + 15 * kg;
    }
}

class Fragile implements ShippingType {
    public double charge(double kg) {
        return 40 + 10 * kg + 50;
    }
}

interface NotificationChannel {
    void notify(String id, String status);
}

class SmsChannel implements NotificationChannel {
    public void notify(String id, String status) {
        System.out.println("[SMS] " + id + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    public void notify(String id, String status) {
        System.out.println("[Email] " + id + " is now " + status + ".");
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Parcel {
    String id;
    double weight;
    String status;
    ShippingType type;
    ArrayList<NotificationChannel> channels = new ArrayList<>();

    Parcel(String id, double weight, ShippingType type) {
        this.id = id;
        this.weight = weight;
        this.type = type;
        status = "BOOKED";
    }

    void addChannel(NotificationChannel c) {
        channels.add(c);
    }

    void notifyChannels() {
        for (NotificationChannel c : channels)
            c.notify(id, status);
    }

    void changeStatus(String newStatus) {

        boolean valid = false;

        if (status.equals("BOOKED") && newStatus.equals("PICKED_UP"))
            valid = true;
        else if (status.equals("PICKED_UP") && newStatus.equals("IN_TRANSIT"))
            valid = true;
        else if (status.equals("IN_TRANSIT") && newStatus.equals("OUT_FOR_DELIVERY"))
            valid = true;
        else if (status.equals("OUT_FOR_DELIVERY") && newStatus.equals("DELIVERED"))
            valid = true;

        if (!valid) {
            System.out.println("Invalid transition: " + status
                    + " → " + newStatus + " is not allowed.");
            return;
        }

        status = newStatus;
        notifyChannels();
    }

    void cancel() {
        if (!status.equals("BOOKED")) {
            System.out.println("Cancellation failed: " + id
                    + " can be cancelled only while BOOKED.");
            return;
        }

        status = "CANCELLED";
        System.out.println("Parcel " + id + " cancelled.");
    }
}

class ParcelService {

    void book(Parcel p) {
        System.out.println("Parcel " + p.id + " booked ("
                + getType(p) + ", " + p.weight + " kg).");

        System.out.printf("Charge: ₹%.2f%n", p.type.charge(p.weight));

        p.notifyChannels();
    }

    String getType(Parcel p) {
        if (p.type instanceof Express)
            return "Express";
        if (p.type instanceof Fragile)
            return "Fragile";
        return "Standard";
    }
}

public class SwiftShipParcelTracker {

    public static void main(String[] args) {

        Customer c = new Customer("Aditya");

        Parcel p = new Parcel(
                "P101",
                2,
                new Express()
        );

        p.addChannel(new SmsChannel());
        p.addChannel(new EmailChannel());

        ParcelService service = new ParcelService();

        service.book(p);

        p.changeStatus("PICKED_UP");

        p.cancel();

        p.changeStatus("IN_TRANSIT");

        p.changeStatus("DELIVERED");
    }
}