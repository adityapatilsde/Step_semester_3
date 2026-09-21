package WEEK4.class_problems;

import java.util.Scanner;

public class BusTicket {

    String passengerName;
    String destination;
    boolean checkedIn;

    public BusTicket(String passengerName, String destination) {

        if (passengerName == null || passengerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid destination");
        }

        for (int i = 0; i < passengerName.length(); i++) {
            char ch = passengerName.charAt(i);

            if (!Character.isLetter(ch)) {
                throw new IllegalArgumentException("Invalid passenger name");
            }
        }

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }

    void markCheckedIn() {

        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in successfully");
        } else {
            System.out.println("Already checked in");
        }
    }

    static void processBatch(String[][] rawBookings) {

        BusTicket[] acceptedTickets = new BusTicket[rawBookings.length];
        int acceptedCount = 0;
        int rejectedCount = 0;
        int duplicateCount = 0;

        for (int i = 0; i < rawBookings.length; i++) {

            String passengerName = rawBookings[i][0];
            String destination = rawBookings[i][1];

            try {

                BusTicket ticket =
                        new BusTicket(passengerName, destination);

                boolean duplicate = false;

                for (int j = 0; j < acceptedCount; j++) {

                    if (acceptedTickets[j].passengerName.equals(ticket.passengerName)
                            && acceptedTickets[j].destination.equals(ticket.destination)) {

                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    duplicateCount++;
                } else {
                    acceptedTickets[acceptedCount] = ticket;
                    acceptedCount++;
                }

            } catch (IllegalArgumentException e) {
                rejectedCount++;
            }
        }

        System.out.println("Valid: " + acceptedCount +
                " | Rejected: " + rejectedCount +
                " | Duplicates skipped: " + duplicateCount);
    }

    public static void main(String[] args) {

        String[][] rawBookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        processBatch(rawBookings);
    }
}

