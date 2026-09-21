package WEEK6.class_problems;

public class FestTicketSettlement {

    public static void main(String[] args) {

        EventTicket5 t1 =
                new EventTicket5(500);

        System.out.println(t1.getTicketId());
        System.out.println(EventTicket5.getTicketsIssued());

        System.out.println(
                EventTicket5.isValidPromoCode("F123A")
        );

        System.out.println(
                EventTicket5.isValidPromoCode("F12A")
        );

        System.out.println(
                EventTicket5.isValidPromoCode("X123A")
        );

        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println(t1.getBalanceDue());

        EventTicket5 group =
                new GroupTicket5(1000, 5);

        EventTicket5[] tickets = {
                t1,
                null,
                group
        };

        System.out.println(
                EventTicket5.processNightlySettlement(tickets)
        );
    }
}

class EventTicket5 {

    private double basePrice;
    private double amountPaid;

    private final String ticketId;

    private static int ticketsIssued = 0;

    public EventTicket5(double basePrice) {

        ticketsIssued++;

        ticketId = "TCK-" + (1000 + ticketsIssued);

        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid = amountPaid + amount;
    }

    public void pay(double amount, String mode) {

        System.out.println("Paying via " + mode);

        pay(amount);
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    public String getTicketId() {
        return ticketId;
    }

    public static int getTicketsIssued() {
        return ticketsIssued;
    }

    public static boolean isValidPromoCode(String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'F') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(3))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }

        return true;
    }

    public static String processNightlySettlement(
            EventTicket5[] tickets) {

        int processed = 0;
        int nullSkipped = 0;
        int groupTickets = 0;
        int regularTickets = 0;

        for (EventTicket5 ticket : tickets) {

            if (ticket == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket5) {
                groupTickets++;
            } else {
                regularTickets++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + groupTickets + " group | "
                + regularTickets + " regular";
    }
}

class GroupTicket5 extends EventTicket5 {

    private int groupSize;

    public GroupTicket5(
            double basePrice,
            int groupSize) {

        super(basePrice);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}