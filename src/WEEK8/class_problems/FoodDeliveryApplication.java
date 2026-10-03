package WEEK8.class_problems;

interface FoodPayment {
    boolean pay(double amount);
}

class FoodCreditCard implements FoodPayment {

    public boolean pay(double amount) {
        System.out.println("Payment via Credit Card successful.");
        return true;
    }
}

class FoodDigitalWallet implements FoodPayment {

    public boolean pay(double amount) {
        System.out.println("Payment via Digital Wallet failed.");
        return false;
    }
}

class FoodCashPayment implements FoodPayment {

    public boolean pay(double amount) {
        System.out.println("Payment via Cash on Delivery successful.");
        return true;
    }
}

class MenuItem {
    String name;
    double price;

    MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class SelectedItem {
    MenuItem item;
    int quantity;

    SelectedItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    double getTotal() {
        return item.price * quantity;
    }
}

class FoodCustomer {
    String name;

    FoodCustomer(String name) {
        this.name = name;
    }
}

class CustomerOrder {
    int orderNumber;
    FoodCustomer customer;
    SelectedItem item1;
    SelectedItem item2;
    String status;

    CustomerOrder(int orderNumber, FoodCustomer customer) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        status = "Pending Payment";

        System.out.println("Order created.");
    }

    void addItem(MenuItem item, int quantity) {

        SelectedItem newItem = new SelectedItem(item, quantity);

        if (item1 == null) {
            item1 = newItem;
        } else {
            item2 = newItem;
        }

        System.out.println("Added " + item.name +
                " (Qty " + quantity + ")");
    }

    boolean hasItems() {
        return item1 != null || item2 != null;
    }

    double getTotal() {
        double total = 0;

        if (item1 != null) {
            total = total + item1.getTotal();
        }

        if (item2 != null) {
            total = total + item2.getTotal();
        }

        return total;
    }

    void placeOrder(FoodPayment payment) {

        if (!hasItems()) {
            System.out.println(
                    "Cannot place order: Order must contain at least one item.");
            return;
        }

        System.out.println("Order placed successfully.");

        boolean result = payment.pay(getTotal());

        if (result) {
            status = "Paid";

            System.out.println("Order status: Paid.");
            System.out.println("Notification: Order #" +
                    orderNumber + " placed and paid.");
        } else {
            status = "Pending Payment";

            System.out.println("Order status: Pending Payment.");
            System.out.println("Notification: Order #" +
                    orderNumber + " placed, awaiting payment.");
        }
    }
}

public class FoodDeliveryApplication {
    public static void main(String[] args) {

        FoodCustomer customer = new FoodCustomer("Student");

        MenuItem pizza = new MenuItem("Pizza", 200);
        MenuItem soda = new MenuItem("Soda", 50);
        MenuItem burger = new MenuItem("Burger", 150);

        CustomerOrder order1 =
                new CustomerOrder(123, customer);

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        CustomerOrder emptyOrder =
                new CustomerOrder(125, customer);

        emptyOrder.placeOrder(new FoodCreditCard());

        order1.placeOrder(new FoodCreditCard());

        CustomerOrder order2 =
                new CustomerOrder(124, customer);

        order2.addItem(burger, 1);

        order2.placeOrder(new FoodDigitalWallet());
    }
}