package WEEK6.assigment_problems;

public class RaceFamily {

    static String classifyGeneration(RaceEntry2 entry) {

        if (entry instanceof EliteRunnerEntry2) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayTeamEntry2) {
            return "Hierarchical sibling (independent branch)";
        }

        if (entry instanceof RunnerEntry2) {
            return "Runner descendant";
        }

        return "Base entry";
    }

    static double getTotalBalanceDue(RaceEntry2[] entries) {

        double total = 0;

        for (int i = 0; i < entries.length; i++) {
            total = total + entries[i].getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        RunnerEntry2 runnerEntry =
                new RunnerEntry2("BIB2001", 80, "Open 10K");

        EliteRunnerEntry2 eliteEntry =
                new EliteRunnerEntry2(
                        "BIB3001", 150,
                        "Elite Full Marathon", 500
                );

        RelayTeamEntry2 relayEntry =
                new RelayTeamEntry2("BIB4001", 300, 4);

        System.out.println(runnerEntry.announce());
        System.out.println(eliteEntry.announce());
        System.out.println(relayEntry.announce());

        System.out.println(classifyGeneration(eliteEntry));
        System.out.println(classifyGeneration(relayEntry));

        RaceEntry2[] entries = {
                runnerEntry, eliteEntry, relayEntry
        };

        System.out.println(getTotalBalanceDue(entries));
    }
}

class RaceEntry2 {

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    public RaceEntry2(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber
                + " | Balance: " + getBalanceDue();
    }
}

class RunnerEntry2 extends RaceEntry2 {

    protected String category;

    public RunnerEntry2(
            String bibNumber,
            double entryFee,
            String category) {

        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + bibNumber
                + " | Category: " + category
                + " | Balance: " + getBalanceDue();
    }
}

class EliteRunnerEntry2 extends RunnerEntry2 {

    private double sponsorBonus;

    public EliteRunnerEntry2(
            String bibNumber,
            double entryFee,
            String category,
            double sponsorBonus) {

        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public String announce() {
        return "Elite Runner | Bib: " + bibNumber
                + " | Category: " + category
                + " | Sponsor Bonus: " + sponsorBonus
                + " | Balance: " + getBalanceDue();
    }
}

class RelayTeamEntry2 extends RaceEntry2 {

    private int teamSize;

    public RelayTeamEntry2(
            String bibNumber,
            double entryFee,
            int teamSize) {

        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + bibNumber
                + " | Team Size: " + teamSize
                + " | Balance: " + getBalanceDue();
    }
}