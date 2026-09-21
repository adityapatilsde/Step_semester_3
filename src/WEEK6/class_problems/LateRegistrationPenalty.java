package WEEK6.class_problems;

public class LateRegistrationPenalty {

    public static void main(String[] args) {

        WorkshopTicket3 w =
                new WorkshopTicket3(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();

        System.out.println(history[0]);

        history[0] = 999;

        double[] history2 = w.getLateFeeHistory();

        System.out.println(history2[0]);
    }
}

class EventTicket3 {

    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory;
    private int feeCount;

    public EventTicket3(double basePrice) {

        this.basePrice = basePrice;
        this.amountPaid = 0;

        lateFeeHistory = new double[10];
        feeCount = 0;
    }

    public void pay(double amount) {
        amountPaid = amountPaid + amount;
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
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

class WorkshopTicket3 extends EventTicket3 {

    public WorkshopTicket3(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}