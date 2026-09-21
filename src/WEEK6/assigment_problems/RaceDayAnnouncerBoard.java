package WEEK6.assigment_problems;

public class RaceDayAnnouncerBoard {

    public static void main(String[] args) {

        RaceEntry runnerEntry =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        runnerEntry.pay(30);
        runnerEntry.applyLateFee(20);

        RaceEntry relayEntry =
                new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] fleet = {runnerEntry, relayEntry};

        System.out.println(announceAll(fleet));

        RaceEntry plain = new RaceEntry("BIB5001", 50);

        // This would cause ClassCastException:
        // RelayTeamEntry bad = (RelayTeamEntry) plain;
    }

    static String announceAll(RaceEntry[] entries) {

        StringBuilder report = new StringBuilder();

        for (RaceEntry entry : entries) {

            report.append(entry.announce());

            if (entry instanceof RelayTeamEntry) {

                RelayTeamEntry relay = (RelayTeamEntry) entry;

                report.append(" [Team size via downcast: ");
                report.append(relay.getTeamSize());
                report.append("] | ");
            }
        }

        return report.toString();
    }

    static class RaceEntry {

        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0;
        }

        public void pay(double amount) {
            amountPaid = amountPaid + amount;
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        protected void applyLateFee(double amount) {
            amountPaid = amountPaid - amount;
        }

        public String announce() {
            return "Race Entry | Bib: " + bibNumber
                    + " | Balance: " + getBalanceDue() + " | ";
        }
    }

    static class RunnerEntry extends RaceEntry {

        private String category;

        public RunnerEntry(String bibNumber, double entryFee,
                           String category) {

            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        public String announce() {

            return "Runner Entry | Bib: " + bibNumber
                    + " | Category: " + category
                    + " | Balance: " + getBalanceDue() + " | ";
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    static class RelayTeamEntry extends RaceEntry {

        private int teamSize;

        public RelayTeamEntry(String bibNumber, double entryFee,
                              int teamSize) {

            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }

        @Override
        public String announce() {

            return "Relay Team | Bib: " + bibNumber
                    + " | Team Size: " + teamSize
                    + " | Balance: " + getBalanceDue();
        }
    }
}