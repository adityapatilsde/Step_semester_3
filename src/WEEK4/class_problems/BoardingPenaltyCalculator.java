package WEEK4.class_problems;

public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException("Ticket fare cannot be negative");
        }

        if (minutesLate < 0) {
            throw new IllegalArgumentException("Minutes late cannot be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penalty = 0.0;

        int firstTierMinutes = Math.min(minutesLate, 5);
        penalty = penalty + firstTierMinutes * ticketFare * 0.005;

        if (minutesLate > 5) {
            int secondTierMinutes = Math.min(minutesLate - 5, 10);
            penalty = penalty + secondTierMinutes * ticketFare * 0.01;
        }

        if (minutesLate > 15) {
            int thirdTierMinutes = minutesLate - 15;
            penalty = penalty + thirdTierMinutes * ticketFare * 0.02;
        }

        double minimumPenalty = ticketFare * minimumPenaltyPercent / 100;

        if (penalty < minimumPenalty) {
            penalty = minimumPenalty;
        }

        return penalty;
    }

    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1.0);

        System.out.println("0 min: Rs " +
                calculator.calculatePenalty(1000, 0));

        System.out.println("1 min: Rs " +
                calculator.calculatePenalty(1000, 1));

        System.out.println("16 min: Rs " +
                calculator.calculatePenalty(1000, 16));
    }
}

