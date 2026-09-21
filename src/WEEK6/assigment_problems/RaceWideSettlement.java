package WEEK6.assigment_problems;

public class RaceWideSettlement {

    public static void main(String[] args) {

        RaceEntry5 eliteEntry =
                new EliteRunnerEntry5("BIB2001", 100);

        RaceEntry5 relayEntry =
                new RelayTeamEntry5("BIB4001", 300, 4);

        eliteEntry.pay(10, "UPI");

        System.out.println(
                RaceEntry5.isValidDiscountCode("M123A"));

        System.out.println(
                RaceEntry5.isValidDiscountCode("M12A"));

        System.out.println(
                RaceEntry5.isValidDiscountCode("X123A"));

        System.out.println("Paying via UPI");

        RaceEntry5[] entries = {
                eliteEntry,
                null,
                relayEntry
        };

        System.out.println(
                RaceEntry5.settleNight(entries));

        System.out.println(
                RaceEntry5.getBibCounter());
    }
}

class RaceEntry5 {

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    private final int entryCode;

    private static int bibCounter = 0;

    public RaceEntry5(String bibNumber, double entryFee) {

        bibCounter++;

        entryCode = bibCounter;

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid = amountPaid + amount;
    }

    public void pay(double amount, String mode) {

        pay(amount);

        System.out.println("Paying via " + mode);
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public int getEntryCode() {
        return entryCode;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public static boolean isValidDiscountCode(String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
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

    public static String settleNight(RaceEntry5[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry5 entry : entries) {

            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry5) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + relay + " relay | "
                + individual + " individual";
    }
}

class EliteRunnerEntry5 extends RaceEntry5 {

    public EliteRunnerEntry5(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }
}

class RelayTeamEntry5 extends RaceEntry5 {

    private int teamSize;

    public RelayTeamEntry5(
            String bibNumber,
            double entryFee,
            int teamSize) {

        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }
}