package WEEK7.class_problems;

abstract class CheckoutPaymentMethod {

    private static int transactionCounter = 1000;
    private final String transactionId;

    public CheckoutPaymentMethod() {
        transactionCounter++;
        transactionId = "TXN-" + transactionCounter;
    }

    public abstract String processPayment(double amount);

    public String processPayment(double amount, String note) {
        return processPayment(amount) + " - " + note;
    }

    public String getTransactionId() {
        return transactionId;
    }
}

class CheckoutCreditCardPayment extends CheckoutPaymentMethod {

    private String cardNumberLastFour;

    public CheckoutCreditCardPayment(String cardNumberLastFour) {
        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount + " to card ending "
                + cardNumberLastFour + " - Txn " + getTransactionId();
    }
}

class CheckoutCashPayment extends CheckoutPaymentMethod {

    public CheckoutCashPayment() {
    }

    @Override
    public String processPayment(double amount) {
        return "Received $" + amount + " in cash - Txn "
                + getTransactionId();
    }
}

public class CheckoutPaymentHandler {

    static void printConfirmation(CheckoutPaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {

        CheckoutCreditCardPayment cc =
                new CheckoutCreditCardPayment("4471");

        // Upcasting
        CheckoutPaymentMethod payment = cc;

        System.out.println(cc.processPayment(250.0));
        System.out.println(cc.processPayment(250.0, "Online order"));

        CheckoutCashPayment cash = new CheckoutCashPayment();

        System.out.println(cash.processPayment(40.0));

        printConfirmation(payment, 100.0);
    }
}