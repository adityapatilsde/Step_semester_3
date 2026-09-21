package WEEK4.class_problems;

public class BusTicketAccount {

    String bookingId;
    double ticketFare;

    static int totalAccountsProcessed;

    static {
        totalAccountsProcessed = 0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    public final double calculatePenalty(int minutesLate) {

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

        int firstTier = Math.min(minutesLate, 5);
        penalty = penalty + firstTier * ticketFare * 0.005;

        if (minutesLate > 5) {
            int secondTier = Math.min(minutesLate - 5, 10);
            penalty = penalty + secondTier * ticketFare * 0.01;
        }

        if (minutesLate > 15) {
            int thirdTier = minutesLate - 15;
            penalty = penalty + thirdTier * ticketFare * 0.02;
        }

        return penalty;
    }

    void processAccount(BusTicketAccount account,
                        double amount,
                        int minutesLate) {

        if (account == null) {
            return;
        }

        double penalty = account.calculatePenalty(minutesLate);

        totalAccountsProcessed++;

        System.out.println(account.bookingId +
                " | Amount: Rs " + amount +
                " | Penalty: Rs " + penalty);
    }

    static void processBatch(BusTicketAccount[] accounts,
                             double[] amounts,
                             int[] minutesLateArray) {

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double grandTotalPenalty = 0.0;

        int length = Math.min(accounts.length,
                Math.min(amounts.length, minutesLateArray.length));

        for (int i = 0; i < length; i++) {

            BusTicketAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            if (account instanceof SleeperCoachAccount) {
                sleeperCount++;
            } else {
                regularCount++;
            }

            double penalty =
                    account.calculatePenalty(minutesLateArray[i]);

            grandTotalPenalty =
                    grandTotalPenalty + penalty;

            processed++;
            totalAccountsProcessed++;
        }

        nullSkipped =
                nullSkipped + accounts.length - length;

        System.out.println(
                processed + " processed | " +
                        nullSkipped + " null skipped | " +
                        sleeperCount + " sleeper | " +
                        regularCount + " regular | " +
                        "grand total penalties = Rs " +
                        grandTotalPenalty
        );
    }

    public static void main(String[] args) {

        BusTicketAccount sleeper =
                new SleeperCoachAccount("BK001", 2000);

        BusTicketAccount regular =
                new BusTicketAccount("BK002", 1500);

        BusTicketAccount regular2 =
                new BusTicketAccount("BK003", 1200);

        BusTicketAccount[] accounts = {
                sleeper,
                null,
                regular,
                regular2
        };

        double[] amounts = {
                1200, 900, 700, 500
        };

        int[] minutesLateArray = {
                10, 5, 5, 0
        };

        processBatch(accounts, amounts, minutesLateArray);
    }
}

class SleeperCoachAccount extends BusTicketAccount {

    SleeperCoachAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}

