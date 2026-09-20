package WEEK3.assigment_problems;

public class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount > 0) {
            amountPaid = amountPaid + amount;
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }

    public static void main(String[] args) {

        FeeAccount plain = new FeeAccount("RA101", 150000, 0);
        HostelFeeAccount hostel = new HostelFeeAccount("RA102", 200000, 60000);
        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("RA103", 180000, 0, 20);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        plain.pay(150000);
        hostel.payInTwoInstallments(70000);
        scholarship.pay(0);

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " +
                        accounts[i].getDue());

            } else if (accounts[i] instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount s =
                        (ScholarshipFeeAccount) accounts[i];

                System.out.println("Scholarship account effective due: Rs " +
                        s.effectiveDue());

            } else {
                System.out.println("Plain account due: Rs " +
                        accounts[i].getDue());
            }
        }
    }
}


class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        pay(amount);
    }
}


class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double amountPaid, double scholarshipPercent) {

        super(regNo, totalFee, amountPaid);

        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        double due = getDue();

        return due - (due * scholarshipPercent / 100);
    }
}


