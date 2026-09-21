package WEEK6.assigment_problems;

public class RaceEntry {

    protected String bibNumber;
    protected double entryFee;
    private double amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {

        bibNumber = bibNumber.trim();

        if (bibNumber.isEmpty() || bibNumber.length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        if (entryFee <= 0) {
            throw new IllegalArgumentException("Invalid entry fee");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid = amountPaid + amount;
        }
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public static String registerBatch(
            String[] bibNumbers, double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (int i = 0; i < bibNumbers.length; i++) {

            try {
                new RaceEntry(bibNumbers[i], entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered
                + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        try {
            new RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        RunnerEntry r =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);

        System.out.println(r.getBalanceDue());

        String[] bibNumbers = {
                "BIB1", "B1", "BIB2"
        };

        System.out.println(
                RaceEntry.registerBatch(bibNumbers, 80)
        );
    }
}

class RunnerEntry extends RaceEntry {

    private String category;

    public RunnerEntry(
            String bibNumber,
            double entryFee,
            String category) {

        super(bibNumber, entryFee);
        this.category = category;
    }
}
