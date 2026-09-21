package WEEK6.class_problems;

public class NightlyTicketAnnouncer {

    public static void main(String[] args) {

        EventTicket4 standard =
                new EventTicket4(500);

        WorkshopTicket4 workshop =
                new WorkshopTicket4(1200, "AI/ML");

        EventTicket4[] tickets = {
                standard,
                workshop
        };

        System.out.println(batchPrint(tickets));
    }

    static String batchPrint(EventTicket4[] tickets) {

        StringBuilder report = new StringBuilder();

        for (EventTicket4 ticket : tickets) {

            report.append(ticket.printTicket());

            if (ticket instanceof WorkshopTicket4) {

                WorkshopTicket4 workshop =
                        (WorkshopTicket4) ticket;

                report.append(
                        " [Track via downcast: "
                                + workshop.getTrack()
                                + "] | "
                );
            }
        }

        return report.toString();
    }
}

class EventTicket4 {

    protected double basePrice;
    protected double amountPaid;

    public EventTicket4(double basePrice) {
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    public String printTicket() {

        return "Standard | Balance: "
                + getBalanceDue()
                + " | ";
    }
}

class WorkshopTicket4 extends EventTicket4 {

    private String track;

    public WorkshopTicket4(
            double basePrice,
            String track) {

        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String printTicket() {

        return "Workshop | Track: "
                + track
                + " | Balance: "
                + getBalanceDue();
    }
}