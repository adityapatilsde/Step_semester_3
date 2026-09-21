package WEEK4.assigment_problems;

class DeliveryAccount {

    protected String studentId;
    protected double orderValue;

    static int totalProcessed;

    static {
        totalProcessed = 0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException();
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double percent;

        if (delayMinutes <= 5) {
            percent = delayMinutes * 0.5;
        } else if (delayMinutes <= 15) {
            percent = 5 * 0.5 + (delayMinutes - 5) * 1.0;
        } else {
            percent = 5 * 0.5 + 10 * 1.0
                    + (delayMinutes - 15) * 2.0;
        }

        return orderValue * percent / 100;
    }
}

class PremiumDeliveryAccount extends DeliveryAccount {

    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public PremiumDeliveryAccount(String studentId) {
        super(studentId);
    }

    public double calculatePremiumSurgeFee(int delayMinutes) {
        return calculateSurgeFee(delayMinutes) * 0.5;
    }
}

public class NightlyMultiKitchenReconciliation {

    static int processed = 0;
    static int nullSkipped = 0;
    static int premium = 0;
    static int regular = 0;
    static double grandTotal = 0;

    void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account == null) {
            return;
        }

        account.orderValue = amount;

        if (account instanceof PremiumDeliveryAccount) {

            PremiumDeliveryAccount p =
                    (PremiumDeliveryAccount) account;

            grandTotal += p.calculatePremiumSurgeFee(delayMinutes);
            premium++;

        } else {

            grandTotal += account.calculateSurgeFee(delayMinutes);
            regular++;
        }

        processed++;
        DeliveryAccount.totalProcessed++;
    }

    static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        NightlyMultiKitchenReconciliation processor =
                new NightlyMultiKitchenReconciliation();

        int length = Math.min(
                accounts.length,
                Math.min(amounts.length, delayMinutesArray.length)
        );

        for (int i = 0; i < length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            try {
                processor.processAccount(
                        accounts[i],
                        amounts[i],
                        delayMinutesArray[i]
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid account skipped.");
            }
        }

        System.out.println(
                processed + " processed | "
                        + nullSkipped + " null skipped | "
                        + premium + " premium | "
                        + regular + " regular | "
                        + "grand total surge fees = "
                        + grandTotal
        );
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
                new PremiumDeliveryAccount("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};

        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}