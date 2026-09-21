package WEEK6.class_problems;

public class EventTicketss {

    private String attendeeId;
    private double basePrice;
    private double amountPaid;

    public EventTicketss(String attendeeId, double basePrice) {

        if (attendeeId == null ||
                attendeeId.trim().isEmpty() ||
                attendeeId.length() < 4) {

            throw new IllegalArgumentException("Invalid attendee ID");
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid = amountPaid + amount;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    public void printTicket() {
        System.out.println(
                "Standard Event Ticket | Balance Due: "
                        + getBalanceDue()
        );
    }

    static String classifyGeneration(EventTicketss ticket) {

        if (ticket instanceof PremiumWorkshopTicket2) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (ticket instanceof HackathonTicket2) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Standard or Workshop ticket";
    }

    static double getTotalBalanceDue(EventTicketss[] tickets) {

        double total = 0;

        for (int i = 0; i < tickets.length; i++) {
            total = total + tickets[i].getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        EventTicketss standardTicket =
                new EventTicketss("STU1", 500);

        WorkshopTicket2 workshopTicket =
                new WorkshopTicket2(
                        "STU2", 1200, "AI/ML"
                );

        PremiumWorkshopTicket2 premiumTicket =
                new PremiumWorkshopTicket2(
                        "STU3", 2000, "Cloud Native", 300
                );

        HackathonTicket2 hackathonTicket =
                new HackathonTicket2(
                        "STU4", 800, "Byte Force"
                );

        standardTicket.printTicket();
        workshopTicket.printTicket();
        premiumTicket.printTicket();
        hackathonTicket.printTicket();

        System.out.println(
                classifyGeneration(premiumTicket)
        );

        System.out.println(
                classifyGeneration(hackathonTicket)
        );

        EventTicketss[] tickets = {
                standardTicket,
                workshopTicket,
                premiumTicket,
                hackathonTicket
        };

        System.out.println(
                getTotalBalanceDue(tickets)
        );
    }
}

class WorkshopTicket2 extends EventTicketss {

    protected String track;

    public WorkshopTicket2(
            String attendeeId,
            double basePrice,
            String track) {

        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    public void printTicket() {
        System.out.println(
                "Workshop Ticket | Track: "
                        + track
                        + " | Balance Due: "
                        + getBalanceDue()
        );
    }
}

class PremiumWorkshopTicket2 extends WorkshopTicket2 {

    private double kitFee;

    public PremiumWorkshopTicket2(
            String attendeeId,
            double basePrice,
            String track,
            double kitFee) {

        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    public void printTicket() {
        System.out.println(
                "Premium Workshop Ticket | Track: "
                        + track
                        + " | Kit Fee: "
                        + kitFee
                        + " | Balance Due: "
                        + getBalanceDue()
        );
    }
}

class HackathonTicket2 extends EventTicketss {

    private String teamName;

    public HackathonTicket2(
            String attendeeId,
            double basePrice,
            String teamName) {

        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    @Override
    public void printTicket() {
        System.out.println(
                "Hackathon Ticket | Team: "
                        + teamName
                        + " | Balance Due: "
                        + getBalanceDue()
        );
    }
}