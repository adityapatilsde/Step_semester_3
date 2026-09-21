package WEEK6.assigment_problems;

public class LateWithdrawalAudit {

    public static void main(String[] args) {

        LateRunnerEntry r =
                new LateRunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);
        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();

        history[0] = 999;

        double[] history2 = r.getLateFeeHistory();

        System.out.println(history2[0]);
    }
}

class LateRaceEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    private double[] lateFeeHistory;
    private int feeCount;

    public LateRaceEntry(String bibNumber, double entryFee) {

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;

        lateFeeHistory = new double[10];
        feeCount = 0;
    }

    public void pay(double amount) {
        amountPaid = amountPaid + amount;
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    protected void applyLateFee(double amount) {

        amountPaid = amountPaid - amount;

        if (feeCount < 10) {
            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }
    }

    public double[] getLateFeeHistory() {

        double[] copy = new double[feeCount];

        for (int i = 0; i < feeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }
}

class LateRunnerEntry extends LateRaceEntry {

    private String category;

    public LateRunnerEntry(
            String bibNumber,
            double entryFee,
            String category) {

        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}