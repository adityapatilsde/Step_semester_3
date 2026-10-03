package WEEK8.class_problems;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class HotelRoom {
    String name;
    int roomNumber;

    HotelRoom(String name, int roomNumber) {
        this.name = name;
        this.roomNumber = roomNumber;
    }

    abstract double calculatePrice(long days);
}

class StandardHotelRoom extends HotelRoom {

    StandardHotelRoom(String name, int roomNumber) {
        super(name, roomNumber);
    }

    double calculatePrice(long days) {
        return days * 150;
    }
}

class DeluxeHotelRoom extends HotelRoom {

    DeluxeHotelRoom(String name, int roomNumber) {
        super(name, roomNumber);
    }

    double calculatePrice(long days) {
        return days * 200;
    }
}

class SuiteHotelRoom extends HotelRoom {

    SuiteHotelRoom(String name, int roomNumber) {
        super(name, roomNumber);
    }

    double calculatePrice(long days) {
        return days * 300;
    }
}

class HotelCustomer {
    String name;

    HotelCustomer(String name) {
        this.name = name;
    }
}

class HotelReservation {
    HotelCustomer customer;
    HotelRoom room;
    LocalDate startDate;
    LocalDate endDate;
    boolean cancelled;
    double price;

    HotelReservation(HotelCustomer customer, HotelRoom room,
                     LocalDate startDate, LocalDate endDate) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        cancelled = false;

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        price = room.calculatePrice(days);
    }

    boolean overlaps(LocalDate start, LocalDate end) {
        return start.isBefore(endDate) && end.isAfter(startDate);
    }

    void cancel() {
        if (!cancelled) {
            cancelled = true;
            System.out.println("Reservation for " + room.name +
                    " cancelled successfully.");
        }
    }
}

class HotelManager {
    HotelReservation[] reservations = new HotelReservation[10];
    int count = 0;

    HotelReservation bookRoom(HotelCustomer customer,
                              HotelRoom room,
                              LocalDate start,
                              LocalDate end) {

        for (int i = 0; i < count; i++) {
            if (!reservations[i].cancelled &&
                    reservations[i].room == room &&
                    reservations[i].overlaps(start, end)) {

                System.out.println("Booking failed: " + room.name +
                        " is not available for " + start +
                        " to " + end + ".");
                return null;
            }
        }

        HotelReservation reservation =
                new HotelReservation(customer, room, start, end);

        reservations[count] = reservation;
        count++;

        System.out.println(room.name + " booked from " +
                start + " to " + end + ".");

        System.out.printf("Total price: $%.2f%n", reservation.price);

        return reservation;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {

        HotelCustomer customer = new HotelCustomer("Student");

        HotelRoom deluxe =
                new DeluxeHotelRoom("Deluxe Room 101", 101);

        HotelRoom standard =
                new StandardHotelRoom("Standard Room 205", 205);

        HotelManager manager = new HotelManager();

        HotelReservation r1 = manager.bookRoom(
                customer,
                deluxe,
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 5));

        manager.bookRoom(
                customer,
                standard,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7));

        manager.bookRoom(
                customer,
                deluxe,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7));

        if (r1 != null) {
            r1.cancel();
        }
    }
}