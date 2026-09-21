package WEEK4.assigment_problems;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid order value or delay");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double surgePercent = 0.0;

        if (delayMinutes <= 5) {
            surgePercent = delayMinutes * 0.5;
        }
        else if (delayMinutes <= 15) {
            surgePercent = (5 * 0.5) + ((delayMinutes - 5) * 1.0);
        }
        else {
            surgePercent = (5 * 0.5)
                    + (10 * 1.0)
                    + ((delayMinutes - 15) * 2.0);
        }

        double tieredFee =
                orderValue * surgePercent / 100;

        double minimumFee =
                orderValue * minimumSurgePercent / 100;

        return Math.max(tieredFee, minimumFee);
    }

    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 0));

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 1));

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 16));
    }
}