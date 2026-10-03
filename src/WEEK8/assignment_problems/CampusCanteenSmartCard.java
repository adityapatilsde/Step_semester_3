package WEEK8.assignment_problems;

import java.util.ArrayList;

interface PricingPlan {
    double price(double amount);
}

class DayScholarPlan implements PricingPlan {
    public double price(double amount) {
        return amount;
    }
}

class HostellerPlan implements PricingPlan {
    public double price(double amount) {
        return amount * 0.90;
    }
}

class StaffPlan implements PricingPlan {
    public double price(double amount) {
        return amount * 0.80;
    }
}

class Transaction {
    double amount;
    String type;

    Transaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }
}

class Purchase {
    String item;
    double amount;
    boolean refunded;

    Purchase(String item, double amount) {
        this.item = item;
        this.amount = amount;
        refunded = false;
    }
}

class SmartCard {
    private String id;
    private double balance;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<Purchase> purchases = new ArrayList<>();
    private PricingPlan plan;
    private boolean blocked;

    SmartCard(String id, PricingPlan plan) {
        this.id = id;
        this.plan = plan;
        balance = 0;
        blocked = false;
    }

    void topUp(double amount) {

        if (blocked) {
            System.out.println("Top-up failed: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println("Top-up failed: Minimum top-up is ₹100.");
            return;
        }

        if (balance + amount > 5000) {
            System.out.println("Top-up failed: Maximum balance is ₹5000.");
            return;
        }

        balance += amount;
        transactions.add(new Transaction(amount, "TOPUP"));

        System.out.printf("%s topped up with ₹%.2f. Balance: ₹%.2f.%n",
                id, amount, balance);
    }

    void purchase(String item, double price) {

        if (blocked) {
            System.out.println("Purchase failed: Card is blocked.");
            return;
        }

        double charged = plan.price(price);

        if (charged > balance) {
            System.out.printf(
                    "Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                    charged, balance);
            return;
        }

        balance -= charged;
        transactions.add(new Transaction(-charged, "PURCHASE"));
        purchases.add(new Purchase(item, charged));

        System.out.printf("%s purchased for ₹%.2f. Balance: ₹%.2f.%n",
                item, charged, balance);
    }

    void refund(String item) {

        for (Purchase p : purchases) {

            if (p.item.equals(item)) {

                if (p.refunded) {
                    System.out.println("Refund rejected: " + item
                            + " has already been refunded.");
                    return;
                }

                balance += p.amount;
                transactions.add(new Transaction(p.amount, "REFUND"));
                p.refunded = true;

                System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                        p.amount, item, balance);
                return;
            }
        }

        System.out.println("Refund rejected: Purchase not found.");
    }

    void block() {
        blocked = true;
    }

    void unblock() {
        blocked = false;
    }

    void statement() {

        System.out.print("Mini-statement for " + id + ": ");

        double total = 0;

        for (int i = 0; i < transactions.size(); i++) {

            double amount = transactions.get(i).amount;
            total += amount;

            if (amount >= 0)
                System.out.printf("+%.2f", amount);
            else
                System.out.printf("%.2f", amount);

            if (i < transactions.size() - 1)
                System.out.print(", ");
        }

        System.out.printf(" = ₹%.2f.%n", total);
    }
}

public class CampusCanteenSmartCard {

    public static void main(String[] args) {

        SmartCard card =
                new SmartCard("C-2045", new HostellerPlan());

        card.topUp(500);

        card.purchase("Veg Thali", 120);

        card.purchase("Cold Coffee", 60);

        card.purchase("Items", 400);

        card.refund("Veg Thali");

        card.refund("Veg Thali");

        card.statement();
    }
}